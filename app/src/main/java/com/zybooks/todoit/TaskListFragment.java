package com.zybooks.todoit;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TaskListFragment extends Fragment {


    private FloatingActionButton fabAddTask;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);
        fabAddTask = (FloatingActionButton) rootView.findViewById(R.id.fab);

        View.OnClickListener FABonClickListener = itemViewFAB -> {
            //Navigation.findNavController(itemViewFAB).navigate(R.id.show_add_task);
            Log.d(TAG,"I'm alive");
        };
        fabAddTask.setOnClickListener(FABonClickListener);



        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected band ID
            int selectedTaskId = (int) itemView.getTag();

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
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bind(task);
            holder.itemView.setTag(task.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
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

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            mNameTextView = itemView.findViewById(R.id.taskText);
            mDateTextView = itemView.findViewById(R.id.taskDueDate);
        }

        public void bind(Task task) {
            DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            mNameTextView.setText(task.getName());
            mDateTextView.setText(task.getDate().format(fr));
        }

    }
}