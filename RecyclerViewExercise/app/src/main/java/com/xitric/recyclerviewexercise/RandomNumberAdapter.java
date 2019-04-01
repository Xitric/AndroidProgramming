package com.xitric.recyclerviewexercise;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberAdapter extends RecyclerView.Adapter<RandomNumberAdapter.RandomNumberViewHolder> {

    private List<Integer> randomNumbers;
    private RandomNumberRemovalListener removalListener;

    public RandomNumberAdapter(int count, int max, RandomNumberRemovalListener listener) {
        randomNumbers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randomNumbers.add((int) (Math.random() * max));
        }
        this.removalListener = listener;
    }

    public void insert(int value, int position) {
        randomNumbers.add(position, value);
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public RandomNumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cellView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.random_number_cell, viewGroup, false);
        return new RandomNumberViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomNumberViewHolder randomNumberViewHolder, int i) {
        randomNumberViewHolder.textView.setText(String.valueOf(randomNumbers.get(i)));
    }

    @Override
    public int getItemCount() {
        return randomNumbers.size();
    }

    public class RandomNumberViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public RandomNumberViewHolder(@NonNull View view) {
            super(view);
            this.textView = view.findViewById(R.id.randomNumberLabel);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    int value = randomNumbers.remove(position);
                    notifyItemRemoved(position);

                    removalListener.numberRemoved(position, value);
                }
            });
        }
    }

    public interface RandomNumberRemovalListener {
        void numberRemoved(int position, int value);
    }
}