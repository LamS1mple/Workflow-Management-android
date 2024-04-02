package com.example.workflowmanagementandroid.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workflowmanagementandroid.Adapter.ListWorkAdapter;
import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AheadOfScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AheadOfScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AheadOfScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AfterTheDeadline.
     */
    // TODO: Rename and change types and number of parameters
    public static AfterTheDeadline newInstance(String param1, String param2) {
        AfterTheDeadline fragment = new AfterTheDeadline();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ListWorkAdapter listWorkAdapter;
    private RecyclerView recyclerView;

    private List<Work> workList;
    private  WorkFragment workFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ahead_of_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findId(view);
    }

    private void findId(View view) {
        workFragment = (WorkFragment) getParentFragment();

        recyclerView = view.findViewById(R.id.recycler_schedule);
        listWorkAdapter = new ListWorkAdapter();
        workList = new ArrayList<>();

        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));
        workList.add(new Work("Hoan thaành database", LocalDateTime.now() , new Group("Lập trình android", "1234",
                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group)));

        listWorkAdapter.setListWork(workList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listWorkAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    workFragment.hideAddButton(false);
                }else{
                    workFragment.hideAddButton(true);
                }
            }
        });



    }
}