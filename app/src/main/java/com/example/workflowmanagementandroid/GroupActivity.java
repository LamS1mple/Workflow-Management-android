package com.example.workflowmanagementandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
            Toast.makeText(this, "zoom", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.work_menu){
            Toast.makeText(this, "work", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.people){
            Toast.makeText(this, "people", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}