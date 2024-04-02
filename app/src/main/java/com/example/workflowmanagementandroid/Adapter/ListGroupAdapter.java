package com.example.workflowmanagementandroid.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.R;

import java.util.List;

public class ListGroupAdapter extends RecyclerView.Adapter<ListGroupAdapter.ViewHolder> {

    public interface BehaviorToFragmentGroup {
        void clickItemGroup(int id);
    }
    private List<Group> groupList;

    private BehaviorToFragmentGroup behaviorToFragmentGroup;

    public void setBehaviorToFragmentGroup(BehaviorToFragmentGroup behaviorToFragmentGroup) {
        this.behaviorToFragmentGroup = behaviorToFragmentGroup;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.imgGroup.setImageResource(groupList.get(position).getImgGroup());
//        holder.nameGroup.setText(groupList.get(position).getNameGroup());
//        holder.nameHost.setText(groupList.get(position).getUser().getName());
//
//        holder.viewGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText( v.getContext(), position + "", Toast.LENGTH_SHORT).show();
//                behaviorToFragmentGroup.clickItemGroup(position);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGroup;
        private TextView nameGroup;
        private TextView nameHost;
        private ImageView editGroup;
        private LinearLayout viewGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgGroup = itemView.findViewById(R.id.img_item_group);
            nameGroup = itemView.findViewById(R.id.name_group);
            nameHost = itemView.findViewById(R.id.name_host);
            editGroup = itemView.findViewById(R.id.ic_edit_group);
            viewGroup = itemView.findViewById(R.id.item_group);
        }
    }
}
