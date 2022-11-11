package com.zybooks.todoit;
import java.util.*;

public class Task {
    private int mId;
    private String mName;
    private Calendar mDate;


    public Task(int id, String name, Calendar date) {
        mId = id;
        mName = name;
        mDate = date;
    }
    public int getId(){
        return mId;
    }

    public void setId(int id){
        this.mId = id;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        this.mName = name;
    }

    public Calendar getDate(){
        return mDate;
    }

    public void setDate(Calendar date){
        this.mDate = date;
    }


}
