package com.example.workflowmanagementandroid.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListTaskMemberAdapter extends RecyclerView.Adapter<ListTaskMemberAdapter.ViewHolder>{


    private List<TaskMember> taskMemberList;

    private SimpleDateFormat simpleDateFormat;

    private Context context;

     public ListTaskMemberAdapter(Context context){
         simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        this.context = context;
    }
    public void setTaskMemberList(List<TaskMember> taskMemberList){
        this.taskMemberList = taskMemberList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_work, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.editWork.setVisibility(View.INVISIBLE);
        holder.timeWork.setText(
                simpleDateFormat.format(taskMemberList.get(position).getDateFinish())
        );
        holder.titleWork.setText(taskMemberList.get(position).getContent());
        Group group = taskMemberList.get(position).getTask().getGroup();
        if (group == null){
            holder.nameGroup.setText(context.getString(R.string.task_my_self));
        }else{
            holder.nameGroup.setText(group.getNameGroup() );
        }
        long time = (taskMemberList.get(position).getDateFinish().getTime()
                - Calendar.getInstance().getTime().getTime()) /86400000;
        if (taskMemberList.get(position).isFinish()){
            holder.backGround.setBackgroundColor(context.getColor(R.color.green));
        }else{
            if (  time > 10 ){
                holder.backGround.setBackgroundColor(context.getColor(R.color.white));
            }else if (time > 0){
                holder.backGround.setBackgroundColor(context.getColor(R.color.warning));
            } else{
                holder.backGround.setBackgroundColor(context.getColor(R.color.red));
            }
        }

    }

    @Override
    public int getItemCount() {
        if (taskMemberList == null) return 0;

        return taskMemberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameGroup;
        private TextView titleWork;
        private TextView timeWork;
        private ImageView editWork;
        private ConstraintLayout backGround;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            backGround = itemView.findViewById(R.id.background_item_work);
            nameGroup = itemView.findViewById(R.id.name_group);
            titleWork = itemView.findViewById(R.id.title_work);
            timeWork = itemView.findViewById(R.id.time_work);
            editWork = itemView.findViewById(R.id.edit_work);
        }
    }
}
