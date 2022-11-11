package com.zybooks.todoit;

import java.util.*;

public class TaskList {
    private List<Task> mTasks;


    public TaskList() {  // is going to require context like band database
        mTasks = new ArrayList<>();
    }


    public List<Task> getTasks(){
        return this.mTasks;
    }

    public void addTask(Task task) {
        this.mTasks.add(task);
    }

    public void removeTask(int taskId) {
        for (Task task : this.mTasks){
            if (task.getId() == taskId){
                mTasks.remove(task);
            }
        }
    }

    public Task getTask(int taskId){
        for (Task task : this.mTasks){
            if (task.getId() == taskId){
                return task;
            }
        }
        return null;
    }

    public int getNextId(TaskList curTL) {    // getting the next available ID
        int nextID = curTL.getTasks().size();
        return nextID;
    }
}
