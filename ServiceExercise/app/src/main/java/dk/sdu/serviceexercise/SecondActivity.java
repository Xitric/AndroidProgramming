package dk.sdu.serviceexercise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dk.sdu.serviceexercise.chucknorris.Joke;
import dk.sdu.serviceexercise.service.Constants;
import dk.sdu.serviceexercise.service.JokeDownloadService;

public class SecondActivity extends AppCompatActivity {

    private JokeDownloadService jokeDownloadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent jokeDownloadServiceIntent = new Intent(this, JokeDownloadService.class);
        bindService(jokeDownloadServiceIntent, jokeDownloadServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(jokeDownloadServiceConnection);
        Log.i(Constants.DEBUG_TAG, "Unbound second activity from service");
    }

    public void onUpdateJokeAction(View view) {
        if (jokeDownloadService != null) {
            Joke joke = jokeDownloadService.getNewlySetJoke();
            ((TextView) findViewById(R.id.jokeLabel)).setText(joke.getJoke());
        }
    }

    public void onUpdateCounterAction(View view) {
        String count = String.valueOf(jokeDownloadService.getJokeCounter());
        ((TextView) findViewById(R.id.jokeCountLabel)).setText(count);
    }

    public void onBackAction(View view) {
        finish();
    }

    private ServiceConnection jokeDownloadServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            jokeDownloadService = ((JokeDownloadService.JokeDownloadServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            jokeDownloadService = null;
        }
    };
}
