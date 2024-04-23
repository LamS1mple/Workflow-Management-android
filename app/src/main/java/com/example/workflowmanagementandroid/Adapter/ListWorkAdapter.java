package com.example.workflowmanagementandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.AddTimelineActivity;
import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.Task;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ListWorkAdapter extends RecyclerView.Adapter<ListWorkAdapter.ViewHolder> {

    private List<TaskMember> taskMemberList;
    private SimpleDateFormat simpleDateFormat;
    private Context context;
    public void setListWork(List<TaskMember> listWork) {
        this.taskMemberList = listWork;
        notifyDataSetChanged();
    }

    public ListWorkAdapter(Context context){
        simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.editWork.setOnClickListener((view) ->{
            createdMenu(view, position);
        });
        holder.titleWork.setText(taskMemberList.get(position).getContentTask());

        holder.timeWork.setText(
                simpleDateFormat.format(taskMemberList.get(position).getDateFinish())
        );
        Group group = taskMemberList.get(position).getTask().getGroup();
        if (group == null){
            holder.nameGroup.setText(context.getString(R.string.task_my_self));
        }else{
            holder.nameGroup.setText(group.getNameGroup() );
        }


        // setup sign
        long time = (taskMemberList.get(position).getDateFinish().getTime()
                - Calendar.getInstance().getTime().getTime()) / 86400000;
        if (taskMemberList.get(position).isFinish()){
            holder.backGround.setBackgroundColor(context.getColor(R.color.green));
        }else{
            if (  time > 10 ){
                holder.backGround.setBackgroundColor(context.getColor(R.color.white));
            }else if (taskMemberList.get(position).getDateFinish().getTime()
                    - Calendar.getInstance().getTime().getTime() > 0){
                holder.backGround.setBackgroundColor(context.getColor(R.color.warning));
            } else{
                holder.backGround.setBackgroundColor(context.getColor(R.color.red));
                holder.timeWork.setTextColor(context.getColor(R.color.white));
                holder.titleWork.setTextColor(context.getColor(R.color.white));
                holder.nameGroup.setTextColor(context.getColor(R.color.white));
            }
        }


    }

    private void createdMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_work, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener((menuItem) ->{
            int id = menuItem.getItemId();
            if (id == R.id.update_time_signature){
                Intent intent = new Intent(context, AddTimelineActivity.class);
                intent.putExtra("idTaskMember", taskMemberList.get(position).getId() );
                context.startActivity(intent);
            }
            return true;
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        if (taskMemberList == null) return 0;
        return taskMemberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout backGround;
        private TextView nameGroup;
        private TextView titleWork;
        private TextView timeWork;
        private ImageView editWork;

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
