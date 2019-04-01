package dk.sdu.roomexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dk.sdu.roomexercise.database.Database;
import dk.sdu.roomexercise.database.User;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(getClass().getName(), "On create called");

        List<User> users = Database.getInstance(this).userDao().getallUsers();
        if (users.size() > 0) {
            User user = users.get(0);
            TextView text = findViewById(R.id.nameTextField);
            text.setText(user.name);
        }
    }

    public void nextButtonPressed(View view) {
        Intent intent = new Intent(this, AddressActivity.class);
        EditText editText = findViewById(R.id.nameTextField);
        String name = editText.getText().toString();
        intent.putExtra(Extras.EXTRA_NAME, name);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getClass().getName(), "On start called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getClass().getName(), "On resume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getClass().getName(), "On pause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(getClass().getName(), "On stop called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getClass().getName(), "On restart called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getName(), "On destroy called");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(getClass().getName(), "On save instance state called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(getClass().getName(), "On restore instance state called");
    }
}
