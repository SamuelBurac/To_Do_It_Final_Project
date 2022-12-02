package com.zybooks.todoit;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.Date;


public class AddTaskFragment extends AppCompatActivity {

    // Assign the widgets to fields
    private EditText task_name_edit_text = findViewById(R.id.task_name_edit_text);
    private EditText task_date_edit_text = findViewById(R.id.task_date_edit_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //This method is called when the user click the "Done" button.
    public void addTask(View view) {

        //New Task object
        Task new_task = new Task();

        //Set user input to variable that can be passed to the new task object.
        String name_of_task = task_name_edit_text.getText().toString();
        Date date_of_task = (Date) task_date_edit_text.getText();

        //Pass the user input variables to the task object.
        new_task.setName(name_of_task);
        new_task.setDate(date_of_task);

    }
}


