package com.example.workflowmanagementandroid.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.workflowmanagementandroid.Fragment.AfterTheDeadline;
import com.example.workflowmanagementandroid.Fragment.AheadOfScheduleFragment;

public class WorkAdapter extends FragmentStateAdapter {
    public WorkAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public WorkAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public WorkAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new AheadOfScheduleFragment();
        }
        return new AfterTheDeadline();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
