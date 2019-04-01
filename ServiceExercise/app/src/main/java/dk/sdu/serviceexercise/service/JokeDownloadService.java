package dk.sdu.serviceexercise.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

import dk.sdu.serviceexercise.chucknorris.ChuckNorrisService;
import dk.sdu.serviceexercise.chucknorris.Joke;
import dk.sdu.serviceexercise.chucknorris.RetrofitClient;

public class JokeDownloadService extends Service {

    private Thread thread;
    private Joke joke;
    private int jokeCounter;

    public synchronized Joke getNewlySetJoke() {
        return joke;
    }

    public synchronized int getJokeCounter() {
        return jokeCounter;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        thread = new Thread(() -> {
            ChuckNorrisService service = RetrofitClient.getChuckNorrisService();

            try {
                while (!Thread.interrupted()) {
                    Joke joke = service.getRandomJoke().execute().body();

                    Log.v(Constants.DEBUG_TAG, "Setting new joke");
                    synchronized (this) {
                        this.joke = joke;
                        this.jokeCounter++;
                        broadcastResult(joke, jokeCounter);
                    }

                    Log.v(Constants.DEBUG_TAG, Thread.currentThread().getName() + " is sleeping");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Log.i(Constants.DEBUG_TAG, "JokeDownloadService was interrupted, goodbye");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        return Service.START_STICKY;
    }

    private void broadcastResult(Joke joke, int count) {
        Intent broadcastIntent = new Intent(Constants.BROADCAST_ACTION)
                .putExtra(Constants.JOKE_STRING_EXTRA, joke.getJoke())
                .putExtra(Constants.JOKE_COUNT_EXTRA, count);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new JokeDownloadServiceBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        thread.interrupt();

        Log.i(Constants.DEBUG_TAG, "Service destroyed");
    }

    public class JokeDownloadServiceBinder extends Binder {
        public JokeDownloadService getService() {
            return JokeDownloadService.this;
        }
    }
}
