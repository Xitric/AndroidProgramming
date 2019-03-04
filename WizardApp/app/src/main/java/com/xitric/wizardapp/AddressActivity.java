package com.xitric.wizardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddressActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Intent intent = getIntent();
        name = intent.getStringExtra(Extras.EXTRA_NAME);
    }

    public void nextButtonPressed(View view) {
        Intent intent = new Intent(this, BirthdateActivity.class);
        EditText editText = findViewById(R.id.addressTextField);
        String address = editText.getText().toString();
        intent.putExtra(Extras.EXTRA_NAME, name);
        intent.putExtra(Extras.EXTRA_ADDRESS, address);
        startActivity(intent);
    }

    public void previousButtonPressed(View view) {
        finish();
    }
}
