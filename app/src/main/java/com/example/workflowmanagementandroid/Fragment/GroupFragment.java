package com.example.workflowmanagementandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.workflowmanagementandroid.Adapter.ListGroupAdapter;
import com.example.workflowmanagementandroid.GroupActivity;
import com.example.workflowmanagementandroid.MainActivity2;
import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.User;
import com.example.workflowmanagementandroid.R;
import com.example.workflowmanagementandroid.ResponseApi.ApiResponse;
import com.example.workflowmanagementandroid.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView btnNotice;
    private  User user;
    private  ListGroupAdapter listGroupAdapter;

    private List<Group> listGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findId(view);

        user = ( (MainActivity2) getActivity() ).getUser();

        ApiService.apiService.getGroup(user.getId()).enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                 listGroup = response.body();
                if (listGroup != null){
                    listGroupAdapter.setGroupList(listGroup);

                }
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("eloi", t.toString());
            }
        });


    }

    private void findId(View view) {
        recyclerView = view.findViewById(R.id.list_group_home_fragment);
        btnNotice = view.findViewById(R.id.ic_notification);
        listGroup = new ArrayList<>();

//        listGroup.add(new Group("Lập trình android", "1234",
//                new User(R.drawable.user,"Trương Thanh Tùng"), R.drawable.test_img_group));
//
//        listGroup.add(new Group("Lập trình android", "1234",
//                new User(R.drawable.user,"Lã Ngọc Hiếu"), R.drawable.test_img_group));
        listGroupAdapter = new ListGroupAdapter();
        listGroupAdapter.setGroupList(listGroup);
        listGroupAdapter.setBehaviorToFragmentGroup(new ListGroupAdapter.BehaviorToFragmentGroup() {
            @Override
            public void clickItemGroup(int id) {
               Intent intent = new Intent(getActivity(), GroupActivity.class);
               intent.putExtra("idGroup", listGroup.get(id).getId());
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listGroupAdapter);
    }
}