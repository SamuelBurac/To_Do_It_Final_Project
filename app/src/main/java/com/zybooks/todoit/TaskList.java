package com.zybooks.todoit;

import android.content.Context;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TaskList {
    public static final String FILENAME = "todolist.txt";
    private Context mContext;
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

    public void clear() {
        mTasks.clear();
    }

    public int getNextId(TaskList curTL) {    // getting the next available ID
        int nextID = curTL.getTasks().size();
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

