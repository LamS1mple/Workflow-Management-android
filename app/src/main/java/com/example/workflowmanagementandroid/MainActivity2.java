package com.example.workflowmanagementandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.workflowmanagementandroid.Fragment.AccountFragment;
import com.example.workflowmanagementandroid.Fragment.GroupFragment;
import com.example.workflowmanagementandroid.Fragment.HomeFragment;
import com.example.workflowmanagementandroid.Fragment.WorkFragment;
import com.example.workflowmanagementandroid.Mapper.JsonToObject;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private FragmentManager fragmentManager;
    private Fragment homeFragment, noteFragment, groupFragment, accountFragment;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findId();
        selectNavi();



    }

    void findId(){
        Bundle bundle = getIntent().getExtras();

        String api =  bundle.getString("user");
        user = JsonToObject.getInstance().jsonToUser(api);
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        Gson gson = new Gson();

        editor.putString("userString", gson.toJson(user));
        editor.apply();
        homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        fragmentManager =  getSupportFragmentManager();

        noteFragment = new WorkFragment();
        noteFragment.setArguments(bundle);

        groupFragment = new GroupFragment();
        groupFragment.setArguments(bundle);

        accountFragment = new AccountFragment();
        replaceFragment(homeFragment);

        navigationView = findViewById(R.id.navigation);

    }

    void replaceFragment(Fragment fragment){

       fragmentManager.beginTransaction()
               .replace(R.id.replace_fragment, fragment)
               .commit();
    }

    void selectNavi(){
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectItem = item.getItemId();
                if (selectItem == R.id.home){
                    replaceFragment(homeFragment);
                }
                if (selectItem == R.id.note){
                    replaceFragment(noteFragment);
                }
                if (selectItem == R.id.group){
                    replaceFragment(groupFragment);

                }
                if (selectItem == R.id.account){
                    replaceFragment(accountFragment);

                }
                return true;
            }
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}