package fr.mnf.nbapalsapp.register.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Francois on 21/10/2015.
 */
public class InternetUtils {

    public interface InternetTaskListener {
        public void onInternetResult(boolean result);
    }

    private static final String LOG_TAG = "InternetUtilities";

    /**
     * Check if the device is connected to a network.
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi != null && mWifi.isConnectedOrConnecting();
        /*ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnectedOrConnecting();*/
    }

    public static void isInternetAvailable(InternetTaskListener listener) {
        new InternetTesterTask(listener).execute();
    }

    public static class InternetTesterTask extends AsyncTask<Void, Void, Boolean> {

        private InternetTaskListener mListener;

        public InternetTesterTask(InternetTaskListener listener) {
            mListener = listener;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1    www.google.com");
                int returnVal = p1.waitFor();
                boolean reachable = (returnVal==0);
                if(reachable){
                    Log.d(LOG_TAG, "Internet access");
                    return reachable;
                }
                else{
                    Log.d(LOG_TAG, "No Internet access");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (mListener != null)
                mListener.onInternetResult(result);
        }
    }
}
