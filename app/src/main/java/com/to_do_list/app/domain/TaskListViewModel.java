package com.to_do_list.app.domain;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.data.repo.TaskRepo;
import com.to_do_list.app.data.repo.TaskRepoFactory;

import java.util.List;


public class TaskListViewModel extends ViewModel {
    private TaskRepo repository;
    public TaskListViewModel() {
    }

    public void setContext(Context context) {
        this.repository = TaskRepoFactory.getInstance(context);
        getAllTask();
    }

    public void getAllTask() {
        tasks.setValue (repository.getAllTasks());
    }

    public void filterSearch(String search) {
        tasks.setValue(repository.filterSearch(search));
    }

    public MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    MutableLiveData<List<Task>> fitler = new MutableLiveData<>();

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void deleteTask(Task task) {
        repository.delete(task);
        getAllTask();
    }
    public LiveData<List<Task>> getFilter() {
        return fitler;
    }
}
