package com.to_do_list.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.databinding.TaskItemBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Holder> {
    public static String DELETE_TAG = "delete";
    public static String UPDATE_TAG = "update";
    private ArrayList<Task> tasks;
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TaskAdapter() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskItemBinding binding = TaskItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Task task = tasks.get(position);
        holder.binding.setTask(task);
        holder.binding.title.setText("Title : "+task.getTitle());
        holder.binding.description.setText("Description  : "+task.getDescription());
        holder.binding.date.setText(task.getDate());
        holder.binding.deleteButton.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onClick(DELETE_TAG, task);
        });
        holder.binding.editBtn.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onClick(UPDATE_TAG, task);
        });
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TaskItemBinding binding;
        public Holder(TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ClickListener {
        public void onClick(String upadteOrDelete, Task task);
    }
    public void clearTasks() {
        tasks.clear();
        notifyDataSetChanged();
    }
}
