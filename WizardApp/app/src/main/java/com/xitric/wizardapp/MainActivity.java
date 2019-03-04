package com.xitric.wizardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextButtonPressed(View view) {
        Intent intent = new Intent(this, AddressActivity.class);
        EditText editText = findViewById(R.id.nameTextField);
        String name = editText.getText().toString();
        intent.putExtra(Extras.EXTRA_NAME, name);
        startActivity(intent);
    }
}
