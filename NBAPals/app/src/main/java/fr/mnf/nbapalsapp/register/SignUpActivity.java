package fr.mnf.nbapalsapp.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import fr.mnf.nbapalsapp.R;

public class SignUpActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SIGN_UP";

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mRepeatPasswordView;
    private TextView mLinkSignIn;
    private View mProgressView;
    private View mLoginFormView;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Set up the login form.

        mSharedPreferences = getSharedPreferences(getString(R.string.register_prefs_key),
                Context.MODE_PRIVATE);
        mUsernameView = (EditText) findViewById(R.id.input_username);

        mRepeatPasswordView = (EditText) findViewById(R.id.input_password_confirm);

        mPasswordView = (EditText) findViewById(R.id.input_password);

        mLinkSignIn = (TextView) findViewById(R.id.link_sign_in);
        mLinkSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

        Button mEmailSignUpButton = (Button) findViewById(R.id.btn_sign_up);
        mEmailSignUpButton.requestFocus();
        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mRepeatPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirmed = mRepeatPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            Log.d(LOG_TAG, "Username is empty");
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        // Check for a valid password, if the user entered one.
        } else if (!RegisterUtilities.isEmailValid(username)) {
            Log.d(LOG_TAG, "Username is not valid");
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            Log.d(LOG_TAG, "password is empty");
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!RegisterUtilities.isPasswordValid(password)) {
            Log.d(LOG_TAG, "password is invalid");
            mPasswordView.setError(getString(R.string.error_forbidden_char_password));
            focusView = mPasswordView;
            cancel = true;
        //Check if the repeated password is valid, and similar to the first one
        } else if (TextUtils.isEmpty(passwordConfirmed)) {
            Log.d(LOG_TAG, "password confirmed is empty");
            mRepeatPasswordView.setError(getString(R.string.error_field_required));
            focusView = mRepeatPasswordView;
            cancel = true;
        } else if (!password.equals(passwordConfirmed)) {
            Log.d(LOG_TAG, "password confirmed not similar to the password");
            mRepeatPasswordView.setError(getString(R.string.error_confirmed_password));
            focusView = mRepeatPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            Log.d(LOG_TAG, "Cancel required.");
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(username, password);
            mAuthTask.invokeWS();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void saveCredentials() {
        Set<String> usernames = mSharedPreferences.getStringSet(getString(R.string.register_prefs_username_values), new HashSet<String>());
        String username = mUsernameView.getText().toString();
        if (!usernames.contains(username)) {
            usernames.add(username);
        }
        mSharedPreferences.edit()
                .putString(getString(R.string.register_prefs_username_active), username)
                .putString(getString(R.string.register_prefs_password_active),
                        mPasswordView.getText().toString())
                .putStringSet(getString(R.string.register_prefs_username_values), usernames)
                .commit();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncHttpClient {

        private final String mUsername;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            super();
            mUsername = email;
            mPassword = password;
        }


        public void invokeWS() {
            showProgress(true);
            RequestParams params = new RequestParams();
            params.put("name", mUsername);
            params.put("password", mPassword);
            // Make RESTful webservice call using AsyncHttpClient object
            Log.i("WSInvoker", "Call : http://192.168.1.18:8080/NBAPalsJersey/ws/register/dosignup?name="
                    + mUsername + "&password=" + mPassword);
            this.get("http://192.168.1.18:8080/NBAPalsJersey/ws/register/dosignup?", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.i("WSResponse", "Server response failure: " + responseString);
                    mAuthTask = null;
                    showProgress(false);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // Extract JSON Object from JSON returned by REST WS
                    // When the JSON response has status boolean value set to true
                    Log.i("WSResponse", "Server response success: " + responseString);
                    showProgress(false);
                    try {
                        JSONObject response = new JSONObject(responseString);
                        boolean status = response.getBoolean("status");
                        if (!status) {
                            if (response.getString("message") != null) {
                                mUsernameView.setError(response.getString("message"));
                                mUsernameView.requestFocus();
                                mAuthTask = null;
                            }
                        } else {
                            saveCredentials();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
