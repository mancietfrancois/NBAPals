package fr.mnf.nbapalsapp.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SIGN_IN";
    private static final int REQUEST_SIGNUP = 0;


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private TextView mLinkSignUp;
    private View mProgressView;
    private View mLoginFormView;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        // Set up the login form.

        mSharedPreferences = getSharedPreferences(getString(R.string.register_prefs_key),
                Context.MODE_PRIVATE);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.input_username);
        mPasswordView = (EditText) findViewById(R.id.input_password);

        mUsernameView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPasswordView.setText("");
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.input_password || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLinkSignUp = (TextView) findViewById(R.id.link_signup);
        mLinkSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_login);
        mEmailSignInButton.requestFocus();
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
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
        loadCredentials();
        populateAutoComplete();
    }

    private void populateAutoComplete() {
        Set<String> usernames = mSharedPreferences.getStringSet(getString(R.string.register_prefs_username_values),
                new HashSet<String>());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                usernames.toArray(new String[usernames.size()]));
        mUsernameView.setAdapter(adapter);
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

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            Log.d(LOG_TAG, "Username is empty");
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!RegisterUtilities.isEmailValid(username)) {
            Log.d(LOG_TAG, "Username is not valid");
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
            // Check for a valid password, if the user entered one.
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
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
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

    public void loadCredentials() {
        mUsernameView.setText(mSharedPreferences.getString(
                getString(R.string.register_prefs_username_active), ""));
        mPasswordView.setText(mSharedPreferences.getString(
                getString(R.string.register_prefs_password_active), ""));
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
            Log.i("WSInvoker", "Call : http://192.168.1.18:8080/NBAPalsJersey/ws/register/dosignin?name="
                    + mUsername + "&password=" + mPassword);
            this.get("http://192.168.1.18:8080/NBAPalsJersey/ws/register/dosignin?", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.i("WSResponse", "Server response: " + responseString);
                    mAuthTask = null;
                    showProgress(false);
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // Extract JSON Object from JSON returned by REST WS
                    // When the JSON response has status boolean value set to true
                    Log.i("WSResponse", "Server response: " + responseString);
                    try {
                        JSONObject response = new JSONObject(responseString);
                        boolean status = response.getBoolean("status");
                        if (!status) {
                            if (response.getString("message") != null) {
                                Log.i("LOG", response.getString("message") + "");
                            }
                        } else {
                            showProgress(false);
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

