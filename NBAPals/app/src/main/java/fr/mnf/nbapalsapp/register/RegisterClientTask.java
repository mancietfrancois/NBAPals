package fr.mnf.nbapalsapp.register;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import fr.mnf.nbapalsapp.register.utils.InternetUtils;

/**
 * Created by Francois on 21/10/2015.
 */
public class RegisterClientTask extends AsyncHttpClient {

    public static final String WS_REGISTER_PATH = "http://192.168.1.18:8080/NBAPalsJersey/ws/register/";
    public static final String LOG_TAG = "RegisterClientTask";

    public static final String ERR_NO_WIFI = "EER_NO_WIFI";
    public static final String ERR_NO_INTERNET = "ERR_NO_INTERNET";
    public static final String ERR_NBAPALS_UNREACHABLE = "ERR_NBAPALS_UNREACHABLE";

    private final String mUsername;
    private final String mPassword;
    private final String mMethod;

    private RegisterClientInterface mListener;

    RegisterClientTask(String email, String password, String method, RegisterClientInterface listener) {
        super();
        mUsername = email;
        mPassword = password;
        mMethod = method;
        mListener = listener;
    }


    public void invokeWS(final Context context) {
        if (!InternetUtils.isNetworkAvailable(context)) {
            mListener.onInvokeFailed(ERR_NO_WIFI);
            return;
        } else {
            InternetUtils.isInternetAvailable(new InternetUtils.InternetTaskListener() {
                @Override
                public void onInternetResult(boolean result) {
                    if (!result) {
                        mListener.onInvokeFailed(ERR_NO_INTERNET);
                    } else {
                        connect();
                    }
                }
            });
        }
    }

    private void connect() {
        Log.d(LOG_TAG, "Network is available");
        RequestParams params = new RequestParams();
        params.put("name", mUsername);
        params.put("password", mPassword);
        // Make RESTful webservice call using AsyncHttpClient object
        Log.d(LOG_TAG, "Call : " + WS_REGISTER_PATH + mMethod + "?name=" + mUsername +
                "&password=" + mPassword);
        this.get(WS_REGISTER_PATH + mMethod +"?", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(LOG_TAG, "Server response failure: " + responseString);
                mListener.onInvokeFailed(ERR_NBAPALS_UNREACHABLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                // Extract JSON Object from JSON returned by REST WS
                // When the JSON response has status boolean value set to true
                Log.d(LOG_TAG, "Server response success: " + responseString);
                mListener.onInvokeSuccess(responseString);
            }
        });
    }
}
