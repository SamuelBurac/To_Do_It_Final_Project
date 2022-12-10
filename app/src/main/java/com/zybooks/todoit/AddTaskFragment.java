package com.zybooks.todoit;

import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


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
        Button button = rootView.findViewById(R.id.add_task_button);
        Button calendarB =  rootView.findViewById(R.id.task_date_edit_text);


        calendarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                LocalDate cal = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    cal = LocalDate.of(year,(monthOfYear + 1),dayOfMonth);
                                }
                                String name_of_task = mTask_name_edit_text.getText().toString();
                                Task Task = new Task(TaskList.getInstance(requireContext()).getNextId(),name_of_task,cal);
                                TaskList.getInstance(requireContext()).addTask(Task);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        //This method is called when the user click the "Done" button.
        button.setOnClickListener(v -> {

            findNavController(v).navigateUp(); //goes back to the Task list fragment
        });

        return rootView;

    }





}


