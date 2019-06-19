package dk.sdu.serviceexercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dk.sdu.serviceexercise.chucknorris.Joke;
import dk.sdu.serviceexercise.service.Constants;
import dk.sdu.serviceexercise.service.JokeDownloadService;

public class MainActivity extends AppCompatActivity {

    private JokeDownloadService jokeDownloadService;
    private JokeBroadcastReceiver jokeBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent jokeDownloadServiceIntent = new Intent(this, JokeDownloadService.class);
        startService(jokeDownloadServiceIntent);
        //From exercise 1
//        bindService(jokeDownloadServiceIntent, jokeDownloadServiceConnection, Context.BIND_AUTO_CREATE);

        IntentFilter broadcastFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        jokeBroadcastReceiver = new JokeBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(jokeBroadcastReceiver, broadcastFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //From exercise 1
//        unbindService(jokeDownloadServiceConnection);
//        Log.i(Constants.DEBUG_TAG, "Unbound first activity from service");

        Intent jokeDownloadServiceIntent = new Intent(this, JokeDownloadService.class);
        stopService(jokeDownloadServiceIntent);
        Log.i(Constants.DEBUG_TAG, "Stopped joke service");

        LocalBroadcastManager.getInstance(this).unregisterReceiver(jokeBroadcastReceiver);
        Log.i(Constants.DEBUG_TAG, "Unregistered broadcast receiver");
    }

    //From exercise 1
    public void onUpdateJokeAction(View view) {
        if (jokeDownloadService != null) {
            Joke joke = jokeDownloadService.getNewlySetJoke();
            ((TextView) findViewById(R.id.jokeLabel)).setText(joke.getJoke());
        }
    }

    //From exercise 1
    public void onUpdateCounterAction(View view) {
        String count = String.valueOf(jokeDownloadService.getJokeCounter());
        ((TextView) findViewById(R.id.jokeCountLabel)).setText(count);
    }

    public void onNextAction(View view) {
        Intent nextIntent = new Intent(this, SecondActivity.class);
        startActivity(nextIntent);
    }

    //From exercise 1
//    private ServiceConnection jokeDownloadServiceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            jokeDownloadService = ((JokeDownloadService.JokeDownloadServiceBinder) service).getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            jokeDownloadService = null;
//        }
//    };

    private class JokeBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String jokeString = intent.getStringExtra(Constants.JOKE_STRING_EXTRA);
            int jokeCount = intent.getIntExtra(Constants.JOKE_COUNT_EXTRA, -1);

            ((TextView) findViewById(R.id.jokeLabel)).setText(jokeString);
            ((TextView) findViewById(R.id.jokeCountLabel)).setText(String.valueOf(jokeCount));
        }
    }
}
