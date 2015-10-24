package fr.mnf.nbapalsapp.logic.register;

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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import fr.mnf.nbapalsapp.R;
import fr.mnf.nbapalsapp.logic.utils.AlertUtilities;
import fr.mnf.nbapalsapp.logic.utils.Encryptor;

public class SignUpActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SIGN_UP";

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private RegisterClientTask mAuthTask = null;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mRepeatPasswordView;
    private TextView mLinkSignIn;
    private View mProgressView;
    private View mLoginFormView;

    // Shared
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
            password = Encryptor.encrypt(Encryptor.getKey1(username), password);
            mAuthTask = new RegisterClientTask(username, password, "register/dosignup",
                    new RegisterClientInterface() {
                @Override
                public void onInvokeSuccess(String response) {
                    onSignupSuccess(response);
                }

                @Override
                public void onInvokeFailed(String response) {
                    onSignupFailed(response);
                }
            });
            mAuthTask.invokeWS(getApplicationContext());
        }
    }

    /**
     * Called when a response is received from the service
     * @param serverResponse
     */
    private void onSignupSuccess(String serverResponse) {
        showProgress(false);
        try {
            JSONObject response = new JSONObject(serverResponse);
            boolean status = response.getBoolean("status");
            if (status) {
                saveCredentials();
                finish();
            } else {
                if (response.has("message")) {
                    mUsernameView.setError(response.getString("message"));
                } else {
                    mUsernameView.setError("Sorry, something wrong arrived.");
                }
                mUsernameView.requestFocus();
                mAuthTask = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            mAuthTask = null;
        }
    }

    /**
     * Called when the server gives no response. Typically if an web connection is not allowed,
     * or if the service is not connected.
     * @param serverResponse
     */
    private void onSignupFailed(String serverResponse) {
        mAuthTask = null;
        showProgress(false);
        String message;
        String title = getString(R.string.title_internet);
        switch (serverResponse) {
            case RegisterClientTask.ERR_NO_INTERNET: {
                message = getString(R.string.internet_no_internet);
                break;
            }
            case RegisterClientTask.ERR_NO_WIFI: {
                message = getString(R.string.internet_no_wi_fi);
                break;
            }
            case RegisterClientTask.ERR_NBAPALS_UNREACHABLE: {
                message = getString(R.string.internet_service_offline);
                break;
            }
            default: {
                message = getString(R.string.internet_unknown_exception);
                break;
            }
        }
        AlertUtilities.displayAlert(message, title, this);
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
            Log.d(LOG_TAG, "Animation Available");
            int longAnimTime = getResources().getInteger(android.R.integer.config_longAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(longAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(longAnimTime).alpha(
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
        Set<String> usernames = mSharedPreferences.getStringSet(
                getString(R.string.register_prefs_username_values), new HashSet<String>());
        String username = mUsernameView.getText().toString();
        if (!usernames.contains(username)) {
            usernames.add(username);
        }
        mSharedPreferences.edit()
                .putString(getString(R.string.register_prefs_username_active), username)
                .putString(getString(R.string.register_prefs_password_active),
                        Encryptor.encrypt(Encryptor.getKey1(username),
                                mPasswordView.getText().toString()))
                .putStringSet(getString(R.string.register_prefs_username_values), usernames)
                .commit();
    }
}
