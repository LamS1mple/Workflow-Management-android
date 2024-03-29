package com.example.workflowmanagementandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.Model.Notification;
import com.example.workflowmanagementandroid.R;

import java.util.List;

public class ListNoticeAdapter extends RecyclerView.Adapter<ListNoticeAdapter.ViewHolder> {


    private List<Notification> list;

    public List<Notification> getList() {
        return list;
    }

    public void setList(List<Notification> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleNotice.setText(list.get(position).getTitle());
        holder.contentNotice.setText(list.get(position).getContent());
        holder.imageNotice.setImageResource(list.get(position).getImgNotice());
        holder.timeNotice.setText(list.get(position).getTime() + "");
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
