package com.example.workflowmanagementandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.AddTimelineActivity;
import com.example.workflowmanagementandroid.Model.NoticeTask;
import com.example.workflowmanagementandroid.Model.Notification;
import com.example.workflowmanagementandroid.R;

import java.util.List;

public class ListNoticeAdapter extends RecyclerView.Adapter<ListNoticeAdapter.ViewHolder> {


    private Context context;
    private List<NoticeTask> list;


    public void setList(List<NoticeTask> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleNotice.setText(list.get(position).getTitleNotice());
        holder.contentNotice.setText(list.get(position).getContentNotic());
        holder.imageNotice.setImageResource(R.drawable.note);
        holder.imageNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTimelineActivity.class);
                intent.putExtra("idTaskMember", list.get(position).getTaskMember().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageNotice, editNotice;
        private TextView titleNotice, contentNotice, timeNotice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editNotice = itemView.findViewById(R.id.ic_edit_notice);
            imageNotice = itemView.findViewById(R.id.img_item_notice);
            timeNotice = itemView.findViewById(R.id.time_notice);
            contentNotice = itemView.findViewById(R.id.content_notice);
            titleNotice = itemView.findViewById(R.id.title_notice);
        }
    }
}
