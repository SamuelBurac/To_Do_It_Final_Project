package com.zybooks.todoit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskListFragment extends Fragment {

    private List<Task> mTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Click listener for the RecyclerView bob
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected band ID
            int selectedBandId = (int) itemView.getTag();

        };

        // Send bands to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.list_fragment);
        List<Task> tasks = mTasks;
        recyclerView.setAdapter(new BandAdapter(tasks, onClickListener));

        return rootView;
    }

    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {


        private final View.OnClickListener mOnClickListener;

        public BandAdapter(List<Task> tasks, View.OnClickListener onClickListener) {
            mTasks = tasks;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
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

    private static class BandHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;

        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            mNameTextView = itemView.findViewById(R.id.taskText);
        }

        public void bind(Task task) {
            mNameTextView.setText(task.getName());
        }
    }
}