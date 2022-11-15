package com.zybooks.todoit;
import java.time.*;

public class Task {
    private int mId;
    private String mName;
    private LocalDate mDate;

    public Task(){}
    public Task(int id, String name, LocalDate date) {
        mId = id;
        mName = name;
        mDate = date;
    }
    public  int getId(){
        return mId;
    }

    public void setId(int id){
        this.mId = id;
    }

    public  String getName(){
        return mName;
    }

    public void setName(String name){
        this.mName = name;
    }

    public LocalDate getDate(){
        return mDate;
    }


    public void setDate(LocalDate date){
        this.mDate = date;
    }

    @Override
    public String toString() {
        String task = new String();

        task = mId +"/" + mDate + "/" + mName;
        return task;

    }




}
