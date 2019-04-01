package dk.sdu.threadingexercise.chucknorris;

import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import dk.sdu.threadingexercise.randomstring.RandomStringGenerator;
import retrofit2.Response;

public class ChuckNorrisRunner implements Runnable {

    private WeakReference<TextView> label;

    public ChuckNorrisRunner(TextView label) {
        this.label = new WeakReference<>(label);
        Log.i("ChuckNorrisRunner", "Created new thread");
    }

    @Override
    public void run() {
        ChuckNorrisService service = RetrofitClient.getChuckNorrisService();

        try {
            while (!Thread.interrupted()) {
                Joke joke = service.getRandomJoke().execute().body();

                if(label.get() != null) {
                    Log.v("ChuckNorrisRunner", "Setting text on " + label.get());
                    label.get().post(() -> label.get().setText(joke.getJoke()));
                } else {
                    Log.i("ChuckNorrisRunner", "Something went wrong, the view is garbage collected");
                    break;
                }

                Log.v("ChuckNorrisRunner", Thread.currentThread().getName() + " is sleeping");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            Log.i("ChuckNorrisRunner", "ChuckNorrisRunner was interrupted, goodbye");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
