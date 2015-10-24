package fr.mnf.nbapalsapp.logic.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import fr.mnf.nbapalsapp.R;

/**
 * Created by Francois on 21/10/2015.
 */
public class AlertUtilities {

    public static void displayAlert(final String message, final String title, final Context context) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

    }
}
