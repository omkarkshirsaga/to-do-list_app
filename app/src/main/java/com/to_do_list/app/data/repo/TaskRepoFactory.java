package com.to_do_list.app.data.repo;

import android.content.Context;

import com.to_do_list.app.data.room.TaskDatabase;

public class TaskRepoFactory {
    private TaskRepoFactory() {
    }
    private static TaskRepo instance;
    public static synchronized TaskRepo getInstance(Context application) {
        if (instance == null) {
            instance = new TaskRepo(TaskDatabase.getDatabase(application).taskDao());
        }
        return instance;
    }
}
