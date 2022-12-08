package com.zybooks.todoit;

import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddTaskFragment extends Fragment {


    public AddTaskFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);

        EditText mTask_name_edit_text = rootView.findViewById(R.id.task_name_edit_text);
        EditText mTask_date_edit_text = rootView.findViewById(R.id.task_date_edit_text);
        Button button = rootView.findViewById(R.id.add_task_button);
        //This method is called when the user click the "Done" button.
        button.setOnClickListener(v -> {
            System.out.println("Done button has been pressed!");
            Log.d(TAG, "onCreateView: Button pressed");

            //New Task object
            Task new_task = new Task();

            //Set user input to variable that can be passed to the new task object.
            String name_of_task = mTask_name_edit_text.getText().toString();
            LocalDate date_of_task = null;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                date_of_task = LocalDate.parse(mTask_date_edit_text.getText().toString(),fr);
            }

            //Pass the user input variables to the task object.
            new_task.setName(name_of_task);
            new_task.setDate(date_of_task);

            findNavController(v).navigateUp();
        });

        return rootView;

    }





}


