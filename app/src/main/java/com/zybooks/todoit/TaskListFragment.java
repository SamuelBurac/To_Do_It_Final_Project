package com.zybooks.todoit;

import static android.content.ContentValues.TAG;

import static androidx.navigation.Navigation.findNavController;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TaskListFragment extends Fragment {


    private FloatingActionButton fabAddTask;
    private static List<Integer> removables = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);

       fabAddTask = (FloatingActionButton) rootView.findViewById(R.id.fab);

        View.OnClickListener FABonClickListener = itemViewFAB -> {
            findNavController(itemViewFAB).navigate(R.id.show_add_task);
        };
        fabAddTask.setOnClickListener(FABonClickListener);



        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            int selectedTaskId = (int) itemView.getTag();
            TaskList.getInstance(requireContext()).removeTask(selectedTaskId);
            try {
                TaskList.getInstance(requireContext()).saveToFile(); //after adding the tasks to the list had to save them to file
            } catch (IOException e) {
                e.printStackTrace();
            }
        };


        // Send tasks to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.list_fragment);
        List<Task> tasks = TaskList.getInstance(requireContext()).getTasks();
        recyclerView.setAdapter(new TaskAdapter(tasks, onClickListener));

        return rootView;
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private final List<Task> mTasks;
        private final View.OnClickListener mOnClickListener;


        public TaskAdapter(List<Task> tasks, View.OnClickListener onClickListener) {
            mTasks = tasks;
            mOnClickListener = onClickListener;

        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, @SuppressLint("RecyclerView") int position) {
            Task task = mTasks.get(position);
            int taskID = task.getId();
            holder.bind(task);
            holder.mButtonView.setTag(task.getId());
            holder.mButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeAt(position);

                    }
            });

        }
        private void removeAt(int position) {
            mTasks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mTasks.size());
        }

        @Override
        public int getItemCount() {
            try {
                return mTasks.size();
            }
            catch (Exception e){
                return 0;
            }
        }
    }

    private static class TaskHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mDateTextView;
        private final ImageButton mButtonView;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            mNameTextView = itemView.findViewById(R.id.taskText);
            mDateTextView = itemView.findViewById(R.id.taskDueDate);
            mButtonView = itemView.findViewById(R.id.taskDone);
        }

        public void bind(Task task) {
            DateTimeFormatter fr = DateTimeFormatter.ofPattern("MM/dd/YYYY");
            mNameTextView.setText(task.getName());
            mDateTextView.setText(task.getDate().format(fr));
        }

    }
}
