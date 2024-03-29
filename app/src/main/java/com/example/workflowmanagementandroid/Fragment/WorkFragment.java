package com.example.workflowmanagementandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.workflowmanagementandroid.Adapter.WorkAdapter;
import com.example.workflowmanagementandroid.AddTimelineActivity;
import com.example.workflowmanagementandroid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    private WorkAdapter workAdapter;

    private ImageView btnAddWork;

    private ActivityResultLauncher<Intent> mWork = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {

                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findId(view);

        btnAddWork.setOnClickListener((v)->{
            Intent intent = new Intent(getActivity(), AddTimelineActivity.class);
            mWork.launch(intent);
        });
    }

    private void findId(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.view_page2);
        workAdapter = new WorkAdapter(this);
        viewPager2.setAdapter(workAdapter);
        viewPager2.setSaveEnabled(false);

        btnAddWork = view.findViewById(R.id.add_work);


        new TabLayoutMediator(tabLayout, viewPager2, (tab , postion) ->{
            if (postion == 0){
                tab.setText("Trước kỳ hạn");
            }
            else{
                tab.setText("Sau kỳ hạn");
            }
        }).attach();
    }

    public void hideAddButton(boolean result){
        if (result){
            btnAddWork.setVisibility(View.VISIBLE);
        }else{
            btnAddWork.setVisibility(View.INVISIBLE);
        }
    }

}