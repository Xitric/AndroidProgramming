package dk.sdu.roomexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dk.sdu.roomexercise.database.Database;
import dk.sdu.roomexercise.database.User;

public class ResumeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Extras.EXTRA_NAME);
        String address = intent.getStringExtra(Extras.EXTRA_ADDRESS);
        long birthDate = intent.getLongExtra(Extras.EXTRA_BIRTHDATE, 0);

        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView addressLabel = findViewById(R.id.addressLabel);
        TextView birthDateLabel = findViewById(R.id.birthDateLabel);

        nameLabel.setText(name);
        addressLabel.setText(address);
        birthDateLabel.setText(String.valueOf(birthDate));

        Database.getInstance(this).userDao().deleteAll();
        User user = new User();
        user.name = name;
        user.address = address;
        user.birthDate = birthDate;
        Database.getInstance(this).userDao().add(user);
    }

    public void previousButtonPressed(View view) {
        finish();
    }
}
