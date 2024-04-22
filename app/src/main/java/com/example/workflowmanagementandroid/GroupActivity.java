package com.example.workflowmanagementandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        findId();
    }

    private void findId() {
        toolbar = findViewById(R.id.group_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Group");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();

            Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.zoom){
            clickZoom();
        }
        if (id == R.id.work_menu){
            Toast.makeText(this, "work", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.people){
            Toast.makeText(this, "people", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void clickZoom() {
        Log.d("idGroup", getIntent().getLongExtra("idGroup",0) +"");
        ApiService.apiService.getZoom(getIntent().getLongExtra("idGroup",0))
                .enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                Group group = response.body();
                if (group != null){
                    Intent intent = new Intent(GroupActivity.this, ConferenceActivity.class);
                    intent.putExtra("passZoom", group.getPassZoom());
                    startActivity(intent);

                }else{
                    Toast.makeText(GroupActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Log.d("loi", t.toString());
                Toast.makeText(GroupActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}