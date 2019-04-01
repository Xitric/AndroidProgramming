package dk.sdu.roomexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.List;

import dk.sdu.roomexercise.database.Database;
import dk.sdu.roomexercise.database.User;

public class BirthdateActivity extends Activity {

    private String name;
    private String address;
    private long birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);

        Intent intent = getIntent();
        name = intent.getStringExtra(Extras.EXTRA_NAME);
        address = intent.getStringExtra(Extras.EXTRA_ADDRESS);

        CalendarView calendarView = findViewById(R.id.datePicker);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                birthDate = view.getDate();
            }
        });

        List<User> users = Database.getInstance(this).userDao().getallUsers();
        if (users.size() > 0) {
            User user = users.get(0);
            calendarView.setDate(user.birthDate);
        }

        birthDate = calendarView.getDate();
    }

    public void nextButtonPressed(View view) {
        Intent intent = new Intent(this, ResumeActivity.class);
        intent.putExtra(Extras.EXTRA_NAME, name);
        intent.putExtra(Extras.EXTRA_ADDRESS, address);
        intent.putExtra(Extras.EXTRA_BIRTHDATE, birthDate);
        startActivity(intent);
    }

    public void previousButtonPressed(View view) {
        finish();
    }
}
