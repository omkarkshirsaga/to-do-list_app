package com.to_do_list.app.data.repo;

import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.data.room.TaskDao;

import java.util.List;

public class TaskRepo {
    private final TaskDao dao;
    public TaskRepo(TaskDao dao) {
        this.dao = dao;
    }
    public void insert(Task task) {
        dao.insertTask(task);
    }

    public void updateTask(Task task) {
        dao.updateTask(task);
    }
    public void delete(Task task) {
        dao.deleteTaskById(task);
    }
    public List<Task> getAllTasks() {return dao.getAllTasks();
    }
    public List<Task> filterSearch(String filter) {return dao.fiterTask(filter);}

}
