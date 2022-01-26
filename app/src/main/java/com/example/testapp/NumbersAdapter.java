package com.example.testapp;

import static com.example.testapp.MainActivity.dayTasks;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private final int numberItems;
    private final Context parent;

    public NumbersAdapter(int numberOfItems, Context parent) {
        numberItems = numberOfItems;
        this.parent = parent;
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
        holder.bind(dayTasks.get(position));
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
                taskActivityIntent.putExtra("NAME", ("Element " + positionIndex
                        + " was clicked"));
                parent.startActivity(taskActivityIntent);
            });
        }

        void bind(String listIndex) {
            listItemNumberView.setText(listIndex);
        }
    }
}
