package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.utils.Task;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private final int taskItems;
    private final Context parent;
    private final ArrayList<String> tasks;

    public NumbersAdapter(int numberOfItems, Context parent, ArrayList<String> tasks) {
        this.taskItems = numberOfItems;
        this.parent = parent;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.task_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(getTime(Task.getTimeStart(tasks.get(position))) + " - " +
                getTime(Task.getTimeFinish(tasks.get(position))),
                Task.getName(tasks.get(position)));
    }

    @Override
    public int getItemCount() {
        return taskItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView listTaskNameView;
        TextView listTaskTimeView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listTaskNameView = itemView.findViewById(R.id.tv_task_name);
            listTaskTimeView = itemView.findViewById(R.id.tv_task_time);

            itemView.setOnClickListener(view -> {
                int positionIndex = getAdapterPosition();

                Intent taskActivityIntent = new Intent(parent, TaskActivity.class);
                taskActivityIntent.putExtra("NAME", Task.getName(tasks.get(positionIndex)));
                taskActivityIntent.putExtra("DATE", Task.getDate(tasks.get(positionIndex)));
                taskActivityIntent.putExtra("TIME", getTime(Task.getTimeStart(tasks.get(positionIndex))) + " - " +
                        getTime(Task.getTimeFinish(tasks.get(positionIndex))));
                taskActivityIntent.putExtra("DESCRIPTION", Task.getDescription(tasks.get(positionIndex)));
                parent.startActivity(taskActivityIntent);
            });
        }

        void bind(String listTime, String listName) {
            listTaskNameView.setText(listName);
            listTaskTimeView.setText(listTime);
        }
    }

    private String getTime(long millSec) {
        return DateUtils.formatDateTime(parent,
                millSec,
                DateUtils.FORMAT_SHOW_TIME);
    }
}
