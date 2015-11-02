package fr.mnf.nbapalsapp.logic.bets;

import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import fr.mnf.nbapalsapp.logic.restful.ServiceInvoker;

/**
 * Created by Francois on 31/10/2015.
 */
public class GamesClientTask extends ServiceInvoker {

    private String mPeriod;
    private String mMethod;

    public GamesClientTask(String period, String method, TextHttpResponseHandler listener) {
        super(listener);
        mPeriod = period;
        mMethod = method;
    }

    @Override
    protected void connect() {
        Log.d(LOG_TAG, "Network is available");
        RequestParams params = new RequestParams();
        params.put("period", mPeriod);
        // Make RESTful webservice call using AsyncHttpClient object
        Log.d(LOG_TAG, "Call : " + WS_PATH + mMethod + "?period=" + mPeriod);
        this.get(WS_PATH + mMethod +"?", params, mListener);
    }
}
