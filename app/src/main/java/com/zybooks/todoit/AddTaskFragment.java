package com.zybooks.todoit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;


public class AddTaskFragment extends Fragment {

    public AddTaskFragment(){

    }

    // Assign the widgets to fields
    private EditText mTask_name_edit_text;
    private EditText mTask_date_edit_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        return rootView;

    }

    //This method is called when the user click the "Done" button.
    public void addTask(View view) {

        //New Task object
        Task new_task = new Task();

        //Set user input to variable that can be passed to the new task object.
        String name_of_task = mTask_name_edit_text.getText().toString();
        LocalDate date_of_task = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date_of_task = LocalDate.parse(mTask_date_edit_text.getText().toString());
        }

        //Pass the user input variables to the task object.
        new_task.setName(name_of_task);
        new_task.setDate(date_of_task);

    }

}


