package dk.sdu.threadingexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dk.sdu.threadingexercise.chucknorris.ChuckNorrisRunner;
import dk.sdu.threadingexercise.randomstring.RandomStringRunner;

public class MainActivity extends AppCompatActivity {

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView label = findViewById(R.id.label);
//        thread = new Thread(new RandomStringRunner(label));
        thread = new Thread(new ChuckNorrisRunner(label));
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}
