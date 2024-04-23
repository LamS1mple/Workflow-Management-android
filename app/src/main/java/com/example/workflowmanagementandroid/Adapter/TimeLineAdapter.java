package com.example.workflowmanagementandroid.Adapter;

import android.content.Context;
import android.util.Log;
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

import com.example.workflowmanagementandroid.Model.DetailTaskMember;
import com.example.workflowmanagementandroid.Model.Group;
import com.example.workflowmanagementandroid.Model.TaskMember;
import com.example.workflowmanagementandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    public interface ChangeDate{
        public void delete(int id);
    }


    private ChangeDate changeDate;
    private List<DetailTaskMember> detailTaskMemberList;
    private SimpleDateFormat simpleDateFormat;
    public void setListWork(List<DetailTaskMember> listWork) {
        this.detailTaskMemberList = listWork;
        notifyDataSetChanged();
    }

    public void setChangeDate(ChangeDate changeDate) {
        this.changeDate = changeDate;
    }

    public TimeLineAdapter(){
        simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    }

    @NonNull
    @Override
    public TimeLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        return new TimeLineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineAdapter.ViewHolder holder, int position) {

        holder.editWork.setOnClickListener((view) ->{
            createdMenu(view, position);
        });
        holder.titleWork.setText(detailTaskMemberList.get(position).getContentTask());

        holder.timeWork.setText(
                simpleDateFormat.format(detailTaskMemberList.get(position).getDateFinish())
        );
        holder.nameGroup.setText(detailTaskMemberList.get(position).getTitleTask());


    }

    private void createdMenu(View view, int po) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_time_line, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener((menuItem) ->{
            Log.d("po", detailTaskMemberList.get(po).getId() + "");
            if (detailTaskMemberList.get(po).getId() == 0){
                detailTaskMemberList.remove(po);
                notifyDataSetChanged();
            }else{
                changeDate.delete(po);
            }
            return true;
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        if (detailTaskMemberList == null) return 0;
        return detailTaskMemberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameGroup;
        private TextView titleWork;
        private TextView timeWork;
        private ImageView editWork;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameGroup = itemView.findViewById(R.id.name_group);
            titleWork = itemView.findViewById(R.id.title_work);
            timeWork = itemView.findViewById(R.id.time_work);
            editWork = itemView.findViewById(R.id.edit_work);
        }


    }
}
