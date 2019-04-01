package com.xitric.recyclerviewexercise;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements RandomNumberAdapter.RandomNumberRemovalListener {

    private RecyclerView recycler;
    private RandomNumberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.randomNumberRecycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new RandomNumberAdapter(1000, 10000, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void numberRemoved(final int position, final int value) {
        String message = "Deleted number " + value + " at position " + position;
        Snackbar snack = Snackbar.make(findViewById(R.id.content), message, Snackbar.LENGTH_LONG);
        snack.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insert(value, position);
            }
        });
        snack.show();
    }
}
