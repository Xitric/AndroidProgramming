package dk.sdu.threadingexercise.randomstring;

import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class RandomStringRunner implements Runnable {

    private WeakReference<TextView> label;
    private RandomStringGenerator gen;

    public RandomStringRunner(TextView label) {
        this.label = new WeakReference<>(label);
        gen = new RandomStringGenerator(16);
        Log.i("RandomStringRunner", "Created new thread");
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String randString = gen.get();

                if(label.get() != null) {
                    Log.v("RandomStringRunner", "Setting text on " + label.get());
                    label.get().post(() -> label.get().setText(randString));
                } else {
                    Log.i("RandomStringRunner", "Something went wrong, the view is garbage collected");
                    break;
                }

                Log.v("RandomStringRunner", Thread.currentThread().getName() + " is sleeping");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Log.i("RandomStringRunner", "RandomStringRunner was interrupted, goodbye");
        }
    }
}
