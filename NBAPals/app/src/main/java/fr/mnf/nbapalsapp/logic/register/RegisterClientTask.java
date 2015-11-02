package fr.mnf.nbapalsapp.logic.register;

import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import fr.mnf.nbapalsapp.logic.restful.ServiceInvoker;

/**
 * Created by Francois on 21/10/2015.
 */
public class RegisterClientTask extends ServiceInvoker {

    private final String mUsername;
    private final String mPassword;
    private final String mMethod;


    RegisterClientTask(String email, String password, String method,
                       TextHttpResponseHandler listener) {
        super(listener);
        mUsername = email;
        mPassword = password;
        mMethod = method;
    }

    @Override
    protected void connect() {
        Log.d(LOG_TAG, "Network is available");
        RequestParams params = new RequestParams();
        params.put("name", mUsername);
        params.put("password", mPassword);
        // Make RESTful webservice call using AsyncHttpClient object
        Log.d(LOG_TAG, "Call : " + WS_PATH + mMethod + "?name=" + mUsername +
                "&password=" + mPassword);
        this.get(WS_PATH + mMethod +"?", params, mListener);
    }
}
