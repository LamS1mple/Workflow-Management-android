package com.example.workflowmanagementandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.workflowmanagementandroid.Adapter.TimeLineAdapter;
import com.example.workflowmanagementandroid.Mapper.JsonToObject;
import com.example.workflowmanagementandroid.Model.DetailTaskMember;
import com.example.workflowmanagementandroid.Model.Post;
import com.example.workflowmanagementandroid.Model.Task;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.example.workflowmanagementandroid.api.ApiService;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTimelineActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText editNameWork, editTimeFinish, editContentWork;
    private RecyclerView recyclerView;
    private TimeLineAdapter timeLineAdapter;
    private Button btnAddTimeline, btnSave;

    private User user;

    private boolean checkSave;

    private boolean check ;

    private List<DetailTaskMember> detailTaskMemberList;

    private TaskMember taskMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSave = false;
        check = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline);
        Bundle bundle = getIntent().getExtras();
        long idTask = getIntent().getLongExtra("idTaskMember", 0);
        findId();
        onClick();
        assert bundle != null;
        Gson gson = new Gson();

        user = new User();
        user.setId(bundle.getLong("user"));
        if (idTask != 0){
            check = true;
            Log.d("idTask", idTask +"");
            ApiService.apiService.getTaskId(idTask).enqueue(new Callback<TaskMember>() {
                @Override
                public void onResponse(Call<TaskMember> call, Response<TaskMember> response) {
                    taskMember = response.body();
                    detailTaskMemberList = taskMember.getListDetalDetailTaskMember();
                    timeLineAdapter.setListWork(detailTaskMemberList);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                    editTimeFinish.setText(simpleDateFormat.format(taskMember.getDateFinish()));
                    editContentWork.setText(taskMember.getContentTask());
                    editNameWork.setText(taskMember.getTask().getTitle());
                }

                @Override
                public void onFailure(Call<TaskMember> call, Throwable t) {
                    Log.d("loi", t.toString());

                }
            });
        }

        timeLineAdapter.setChangeDate(new TimeLineAdapter.ChangeDate() {
            @Override
            public void delete(int id) {
                Log.d("size", detailTaskMemberList.size() + "");
                ApiService.apiService.deteleTask(detailTaskMemberList.get(id).getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(AddTimelineActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        detailTaskMemberList.remove(id);
                        timeLineAdapter.setListWork(detailTaskMemberList);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

        




    }

    private void onClick() {
        btnBack.setOnClickListener(v -> {

            finish();
        });

        btnAddTimeline.setOnClickListener(v -> {
            createdDialog();
        });

        editTimeFinish.setOnClickListener( v->{
            createDatePicker(editTimeFinish);
        });

        btnSave.setOnClickListener( v ->{
            if (check){
                tontai();
            }
            else{
                sur();
            }


        });
    }

    private void tontai() {
        taskMember.setContentTask(editContentWork.getText().toString());
        taskMember.getTask().setTitleTask(editNameWork.getText().toString());
        Date date ;
        try {
            date = convertDate(editTimeFinish.getText().toString());
            taskMember.setDateFinish(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        taskMember.setListDetalDetailTaskMember(detailTaskMemberList);
        ApiService.apiService.updateTaskMember(taskMember).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddTimelineActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void sur() {Task task = new Task();
        task.setGroup(null);
        task.setContent(null);
        task.setTitleTask(editNameWork.getText().toString());


        TaskMember taskMember = new TaskMember();
        taskMember.setUser(user);
        taskMember.setFinish(false);
        taskMember.setContentTask(editContentWork.getText().toString());
        Date date ;
        try {
            date = convertDate(editTimeFinish.getText().toString());
            task.setDateFinish(date);
            taskMember.setDateFinish(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        taskMember.setListDetalDetailTaskMember(detailTaskMemberList);

        taskMember.setTask(task);

        if(checkSave){
            Log.d("timesee", "aa");
            createBackgroundService(date, taskMember);
        }


        ApiService.apiService.saveTaskMember(taskMember).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Toast.makeText(AddTimelineActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(AddTimelineActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createdDialog(){

        View view = LayoutInflater.from(this).inflate(R.layout.add_time_line_dialog, null,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        EditText editNameWorkDialog = view.findViewById(R.id.edit_name_work);
        EditText editTimeFinish = view.findViewById(R.id.time_finish);
        EditText editContentWork = view.findViewById(R.id.edit_content_work);
        Button btnCancel = view.findViewById(R.id.cancel);
        Button btnSave = view.findViewById(R.id.save);



        Dialog dialogFragment = builder.create();
        dialogFragment.show();

        // off dialog
        btnCancel.setOnClickListener(v ->{
            dialogFragment.dismiss();
        });

        editTimeFinish.setOnClickListener(v ->{
            createDatePicker(editTimeFinish);
        });

        // save detail task
        btnSave.setOnClickListener(v ->{
            DetailTaskMember detailTaskMember = new DetailTaskMember();
            detailTaskMember.setFinish(false);
            detailTaskMember.setContentTask(editContentWork.getText().toString());
            detailTaskMember.setTitleTask(editNameWorkDialog.getText().toString());
            Date dateSave ;
            try {
                dateSave =  convertDate(editTimeFinish.getText().toString() );
                detailTaskMember.setDateFinish(dateSave);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            detailTaskMemberList.add(detailTaskMember);
            timeLineAdapter.setListWork(detailTaskMemberList);

            dialogFragment.dismiss();
        });
    }

    private void createBackgroundService(Date date, TaskMember taskMember) {
        Date now = new Date();
        Log.d("timesee", date.getTime() - now.getTime() + 1000 + "");
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Gson gson = new Gson();
        Data inputData = new Data.Builder()
                .putString("taskMember",gson.toJson(taskMember) )
                .putLong("idUser", user.getId())
                .build();
        WorkRequest create = new OneTimeWorkRequest.Builder(NoticeWorker.class)
                .setConstraints(constraints)
                .setInputData(inputData)
                .setInitialDelay(date.getTime() - now.getTime() + 1000 , TimeUnit.MILLISECONDS )
                .build();
        WorkManager.getInstance(this).enqueue(create);
    }

    private Date convertDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        return simpleDateFormat.parse(s);
    }

    private void findId() {
        btnBack = findViewById(R.id.btn_back);
        editNameWork = findViewById(R.id.edit_name_work);
        editTimeFinish = findViewById(R.id.time_finish);
        editContentWork = findViewById(R.id.edit_content_work);
        recyclerView = findViewById(R.id.list_timeline);
        btnAddTimeline = findViewById(R.id.add_timeline);
        btnSave = findViewById(R.id.save);

        detailTaskMemberList = new ArrayList<>();


        // set adapter
        timeLineAdapter = new TimeLineAdapter();

        recyclerView.setAdapter(timeLineAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void createDatePicker(TextView textView){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTimelineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        checkSave = true;
                        textView.setText(hourOfDay + ":" +minute + " "+ dayOfMonth +"-"+ (month + 1) + "-" + year);

                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        },      calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }

}