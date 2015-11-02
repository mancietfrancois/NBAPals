package fr.mnf.nbapalsapp.logic.restful;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import fr.mnf.nbapalsapp.logic.utils.InternetUtils;

/**
 * Created by Francois on 31/10/2015.
 */
public abstract class ServiceInvoker extends AsyncHttpClient {

    /*public static final String IP_ADDRESS = "http://192.168.1.18:8080/";*/
    public static final String IP_ADDRESS = "http://10.0.0.20:8080/";
    public static final String WS_PATH = IP_ADDRESS + "NBAPalsJersey/ws/";

    public static final String LOG_TAG = "ServiceInvoker";

    public static final String ERR_NO_WIFI = "EER_NO_WIFI";
    public static final String ERR_NO_INTERNET = "ERR_NO_INTERNET";
    public static final String ERR_NBAPALS_UNREACHABLE = "ERR_NBAPALS_UNREACHABLE";

    protected TextHttpResponseHandler mListener;

    public ServiceInvoker(TextHttpResponseHandler listener) {
        mListener = listener;
    }

    public void invokeWS(final Context context) {
        if (!InternetUtils.isNetworkAvailable(context)) {
            mListener.onFailure(-1, null, ERR_NO_WIFI, null);
            return;
        } else {
            InternetUtils.isInternetAvailable(new InternetUtils.InternetTaskListener() {
                @Override
                public void onInternetResult(boolean result) {
                    if (!result) {
                        mListener.onFailure(-1, null, ERR_NO_INTERNET, null);
                    } else {
                        connect();
                    }
                }
            });
        }
    }

    protected abstract void connect();
}
