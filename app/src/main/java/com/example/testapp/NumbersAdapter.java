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

    private final int numberItems;
    private final Context parent;
    private final ArrayList<String> tasks;

    public NumbersAdapter(int numberOfItems, Context parent, ArrayList<String> tasks) {
        this.numberItems = numberOfItems;
        this.parent = parent;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(getTime(Task.getTimeStart(tasks.get(position))) + " - " +
                getTime(Task.getTimeFinish(tasks.get(position))) + " | " +
                Task.getName(tasks.get(position)));
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView listItemNumberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_number_item);

            itemView.setOnClickListener(view -> {
                int positionIndex = getAdapterPosition();

                Intent taskActivityIntent = new Intent(parent, TaskActivity.class);
                taskActivityIntent.putExtra("TASK", Task.getName(tasks.get(positionIndex)) + "\n" +
                        Task.getDate(tasks.get(positionIndex)) + "\n" +
                        getTime(Task.getTimeStart(tasks.get(positionIndex))) + " - " +
                        getTime(Task.getTimeFinish(tasks.get(positionIndex))) + "\n" +
                        Task.getDescription(tasks.get(positionIndex)));
                parent.startActivity(taskActivityIntent);
            });
        }

        void bind(String listIndex) {
            listItemNumberView.setText(listIndex);
        }
    }

    private String getTime(long millSec) {
        return DateUtils.formatDateTime(parent,
                millSec,
                DateUtils.FORMAT_SHOW_TIME);
    }
}
