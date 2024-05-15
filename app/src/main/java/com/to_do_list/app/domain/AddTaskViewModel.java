package com.to_do_list.app.domain;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.data.repo.TaskRepo;
import com.to_do_list.app.data.repo.TaskRepoFactory;
import com.to_do_list.util.Util;

import java.util.ArrayList;

public class AddTaskViewModel extends ViewModel {
    private TaskRepo repo;

    public void setContext(Context context) {
        this.repo = TaskRepoFactory.getInstance(context);
    }

    public AddTaskViewModel() {
    }
    public ObservableField<String> priority = new ObservableField<>("");
    private final ArrayList<String> priorityList = new ArrayList<>();
    public MutableLiveData<String> success = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();


    public void onClick(String title, String desc) {
        Task task = new Task(title,desc,"pending",priority.get(),Util.getCurrentDate());
        repo.insert(task);
        success.setValue("Task Added");
    }

    public ArrayList<String> getPriorityList() {
        priorityList.add("1");
        priorityList.add("2");
        priorityList.add("3");
        priorityList.add("4");
        priorityList.add("5");
        priorityList.add("6");
        priorityList.add("7");
        priorityList.add("8");
        priorityList.add("9");
        priorityList.add("10");
        return priorityList;
    }

    public MutableLiveData<String> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
    public void setPriority(String priority) {
        this.priority.set(priority);
    }
}

