package com.to_do_list.app.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.to_do_list.app.data.pojo.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Delete
    void deleteTaskById(Task task);

    @Update
    void updateTask(Task task);
    @Query("SELECT * FROM tasks WHERE title LIKE  :searchQuery|| '%'")
   List<Task> fiterTask(String searchQuery);
}
