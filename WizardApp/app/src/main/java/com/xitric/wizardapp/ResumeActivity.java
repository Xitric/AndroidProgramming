package com.xitric.wizardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class ResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Extras.EXTRA_NAME);
        String address = intent.getStringExtra(Extras.EXTRA_ADDRESS);
        String birthDate = intent.getStringExtra(Extras.EXTRA_BIRTHDATE);

        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView addressLabel = findViewById(R.id.addressLabel);
        TextView birthDateLabel = findViewById(R.id.birthDateLabel);

        nameLabel.setText(name);
        addressLabel.setText(address);
        birthDateLabel.setText(birthDate);
    }

    public void previousButtonPressed(View view) {
        finish();
    }
}
