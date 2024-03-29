package com.example.workflowmanagementandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.workflowmanagementandroid.Fragment.AccountFragment;
import com.example.workflowmanagementandroid.Fragment.GroupFragment;
import com.example.workflowmanagementandroid.Fragment.HomeFragment;
import com.example.workflowmanagementandroid.Fragment.WorkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private FragmentManager fragmentManager;
    private Fragment homeFragment, noteFragment, groupFragment, accountFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findId();
        selectNavi();







    }

    void findId(){
        homeFragment = new HomeFragment();
        noteFragment = new WorkFragment();
        groupFragment = new GroupFragment();
        accountFragment = new AccountFragment();
        replaceFragment(homeFragment);

        navigationView = findViewById(R.id.navigation);

    }

    void replaceFragment(Fragment fragment){
       fragmentManager =  getSupportFragmentManager();

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
}