package dk.sdu.roomexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dk.sdu.roomexercise.database.Database;
import dk.sdu.roomexercise.database.User;

public class AddressActivity extends Activity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Intent intent = getIntent();
        name = intent.getStringExtra(Extras.EXTRA_NAME);

        List<User> users = Database.getInstance(this).userDao().getallUsers();
        if (users.size() > 0) {
            User user = users.get(0);
            TextView text = findViewById(R.id.addressTextField);
            text.setText(user.address);
        }
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
