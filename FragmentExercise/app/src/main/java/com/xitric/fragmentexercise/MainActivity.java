
package com.xitric.fragmentexercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout navDrawer;
    private SparseArray<SelectionData> selectionMapper;

    public MainActivity() {
        super();
        selectionMapper = new SparseArray<>();
        selectionMapper.put(R.id.whiteMenuItem, new SelectionData(0xFFFFFFFF, R.id.topColor));
        selectionMapper.put(R.id.redMenuItem, new SelectionData(0xFFFF0000, R.id.topColor));
        selectionMapper.put(R.id.greenMenuItem, new SelectionData(0xFF00FF00, R.id.topColor));
        selectionMapper.put(R.id.blueMenuItem, new SelectionData(0xFF0000FF, R.id.botColor));
        selectionMapper.put(R.id.yellowMenuItem, new SelectionData(0xFFFFFF00, R.id.botColor));
        selectionMapper.put(R.id.purpleMenuItem, new SelectionData(0xFF9400D3, R.id.botColor));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navDrawer = findViewById(R.id.colorFragmentView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }

        NavigationView colorChooser = findViewById(R.id.colorChooser);
        colorChooser.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // close drawer when item is tapped
        navDrawer.closeDrawers();

        // Add code here to update the UI based on the item selected
        // For example, swap UI fragments here
        SelectionData data = selectionMapper.get(menuItem.getItemId());

        if (data != null) {
            Fragment fragment = new ColorFragment();

            Bundle args = new Bundle();
            args.putInt("color", data.color);
            args.putString("colorString", menuItem.getTitle().toString());
            fragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(data.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        return true;
    }

    private static class SelectionData {
        int color;
        int container;

        SelectionData(int color, int container) {
            this.color = color;
            this.container = container;
        }
    }
}
