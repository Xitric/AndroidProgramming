package dk.sdu.serviceexercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Objects;

public class ChargingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Objects.equals(intent.getAction(), Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "The device is now charging", Toast.LENGTH_LONG)
                    .show();
        } else if(Objects.equals(intent.getAction(), Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "The device is no longer charging", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
