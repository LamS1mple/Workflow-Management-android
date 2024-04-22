package com.example.workflowmanagementandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

    private List<DetailTaskMember> detailTaskMemberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSave = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline);
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        Gson gson = new Gson();

//        user = gson.fromJson(bundle.getString("user"), User.class);
        user = new User();
        user.setId(bundle.getLong("user"));
        
        findId();
        onClick();
        // is fix task ?
//        if (!bundle.getBoolean("who")){
//        }


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
            Task task = new Task();
            task.setGroup(null);
            task.setContent(null);
            task.setTitleTask(editNameWork.getText().toString());


            TaskMember taskMember = new TaskMember();
            taskMember.setUser(user);
            taskMember.setFinish(false);
            taskMember.setContentTask(editContentWork.getText().toString());
            try {
                task.setDateFinish(convertDate(editTimeFinish.getText().toString()));
                taskMember.setDateFinish(convertDate(editTimeFinish.getText().toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            taskMember.setListDetalDetailTaskMember(detailTaskMemberList);

            taskMember.setTask(task);


            Log.d("taskMember", taskMember.toString());
            ApiService.apiService.saveTaskMember(taskMember).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    Toast.makeText(AddTimelineActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(AddTimelineActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            });


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
            try {
                detailTaskMember.setDateFinish(convertDate(editTimeFinish.getText().toString() ));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if(checkSave){
                createBackgroundService();
            }
            detailTaskMemberList.add(detailTaskMember);
            timeLineAdapter.setListWork(detailTaskMemberList);
            dialogFragment.dismiss();
        });
    }

    private void createBackgroundService() {
        WorkRequest create = new OneTimeWorkRequest.Builder(NoticeWorker.class)
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