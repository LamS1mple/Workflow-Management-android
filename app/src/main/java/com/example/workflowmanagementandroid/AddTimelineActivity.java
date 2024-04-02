package com.example.workflowmanagementandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddTimelineActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText editNameWork, editTimeFinish, editContentWork;
    private RecyclerView recyclerView;
    private Button btnAddTimeline, btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline);

        findId();
        onClick();
    }

    private void onClick() {
        btnBack.setOnClickListener(v -> {

            finish();
        });

        btnAddTimeline.setOnClickListener(v -> {
            createdDialog();
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

        btnCancel.setOnClickListener(v ->{
            dialogFragment.dismiss();
        });
    }

    private void findId() {
        btnBack = findViewById(R.id.btn_back);
        editNameWork = findViewById(R.id.edit_name_work);
        editTimeFinish = findViewById(R.id.time_finish);
        editContentWork = findViewById(R.id.edit_content_work);
        recyclerView = findViewById(R.id.list_timeline);
        btnAddTimeline = findViewById(R.id.add_timeline);
        btnSave = findViewById(R.id.save);
    }

}