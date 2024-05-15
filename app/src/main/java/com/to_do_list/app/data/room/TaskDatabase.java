package com.to_do_list.app.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.to_do_list.app.data.pojo.Task;

@Database(entities = {Task.class},exportSchema = false,version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract  TaskDao taskDao();
    private static final String DATATBSE_NAME = "note_database";
    private static volatile TaskDatabase database=null;
    private static final Object lock  = new Object();
    public static TaskDatabase getDatabase(Context context){
        if (database==null){
            synchronized (lock){
                if (database==null){
                    database = Room.databaseBuilder(context, TaskDatabase.class,DATATBSE_NAME)
                            .allowMainThreadQueries()
                            .build();
                    return database;
                }
                else {
                    return database;
                }
            }
        }else {
            return database;
        }
    }
}
