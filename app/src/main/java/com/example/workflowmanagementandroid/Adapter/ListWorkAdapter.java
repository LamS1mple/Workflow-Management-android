package com.example.workflowmanagementandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workflowmanagementandroid.Model.Task;
import com.example.workflowmanagementandroid.R;

import java.util.List;

public class ListWorkAdapter extends RecyclerView.Adapter<ListWorkAdapter.ViewHolder> {

    private List<Task> listWork;

    public void setListWork(List<Task> listWork) {
        this.listWork = listWork;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.nameGroup.setText(listWork.get(position).getGroup().getNameGroup());
//        holder.titleWork.setText(listWork.get(position).getNameWork());
//        holder.timeWork.setText(listWork.get(position).getDateTimeWork().toString());
//
//        holder.editWork.setOnClickListener((view) ->{
//            createdMenu(view);
//        });

    }

    private void createdMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_work, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener((menuItem) ->{
            Toast.makeText(view.getContext(), menuItem.getItemId() + "", Toast.LENGTH_SHORT).show();
            return true;
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return listWork.size();
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
