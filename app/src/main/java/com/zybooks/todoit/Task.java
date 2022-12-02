package com.zybooks.todoit;
import java.util.Date;

public class Task {
    private int mId;
    private String mName;
    private Date mDate;

    public Task(){}
    public Task(int id, String name, Date date) {
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

    public Date getDate(){
        return mDate;
    }


    public void setDate(Date date){
        this.mDate = date;
    }

    @Override
    public String toString() {
        String task = new String();

        task = mId +"/" + mDate + "/" + mName;
        return task;

    }




}
