package udaan17.in.udaan17androidapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by pranshu on 5/3/17.
 * For: Udaan17AndroidApp
 */

public class Helper {


    public static boolean hasNetworkconnection(Context context) {

        boolean status = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            status = true;

        return status;
    }

    public static void showNetworkAlertPopup(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Network connection is required when you first connect to the internet. Please turn on mobile network or Wi-Fi in Settings")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                activity.startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                activity.finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

}
