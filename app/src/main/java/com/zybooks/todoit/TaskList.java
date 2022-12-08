package com.zybooks.todoit;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskList {

    public static final String FILENAME = "todolist.txt";
    private Context mContext;
    private static TaskList instance;
    private List<Task> mTasks;

    public static TaskList getInstance(Context context){
        if(instance == null){
            instance = new TaskList(context);
        }
        return instance;
    }

    public TaskList(Context context) {  // is going to require context like band database
        mTasks = new ArrayList<>();
        mContext = context;
    }


    public List<Task> getTasks(){
        return this.mTasks;
    }

    public void addTask(Task task) {
        this.mTasks.add(task);
        Collections.sort(mTasks, Comparator.comparing(Task::getDate));
    }

    public void removeTask(int taskId) {
        for (Task task : this.mTasks){
            if (task.getId() == taskId){
                mTasks.remove(task);
            }
        }

        int i = mTasks.size()-1;
        for (Task task : this.mTasks){
            task.setId(i);
            i--;
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

    public void clear() {
        mTasks.clear();
    }

    public int getNextId() {    // getting the next available ID
        int nextID = this.mTasks.size();
        return nextID;
    }

    public void saveToFile() throws IOException {

        // Write list to file in internal storage
        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        writeListToStream(outputStream);
    }

    public void readFromFile() throws IOException {


        // Read in list from file in internal storage
        FileInputStream inputStream = mContext.openFileInput(FILENAME);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            mTasks.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                Task task = toTask(line);
                mTasks.add(task);
            }
        }
        catch (FileNotFoundException ex) {
            // Ignore
        }
    }
    private void writeListToStream(FileOutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        for (Task task : mTasks) {
            writer.println(task.toString());
        }
        writer.close();
    }
    public Task toTask(String s) {
        String[] arrOfS = s.split("/", 15);
        Task task = new Task();
        int ID = Integer.parseInt(arrOfS[0]);
        task.setId(ID);
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.parse(arrOfS[1]);
        }
        task.setDate(date);
        task.setName(arrOfS[2]);
        return task;
    }


}

