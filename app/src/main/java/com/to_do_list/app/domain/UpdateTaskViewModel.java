package com.to_do_list.app.domain;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.data.repo.TaskRepo;
import com.to_do_list.app.data.repo.TaskRepoFactory;

public class UpdateTaskViewModel extends ViewModel {
    private TaskRepo repo;
    public UpdateTaskViewModel() {
    }

    public void setContext(Context context) {

        this.repo = TaskRepoFactory.getInstance(context);
    }

    public void updateTask(Task task) {
        repo.updateTask(task);
    }

}