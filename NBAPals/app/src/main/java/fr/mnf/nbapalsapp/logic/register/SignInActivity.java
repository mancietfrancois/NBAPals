package fr.mnf.nbapalsapp.logic.register;

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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import fr.mnf.nbapalsapp.R;
import fr.mnf.nbapalsapp.logic.utils.AlertUtilities;
import fr.mnf.nbapalsapp.logic.utils.Encryptor;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SIGN_IN";
    private static final int REQUEST_SIGNUP = 0;


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private RegisterClientTask mAuthTask = null;

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
            password = Encryptor.encrypt(Encryptor.getKey1(username), password);
            mAuthTask = new RegisterClientTask(username, password, "register/dosignin", new RegisterClientInterface() {
                @Override
                public void onInvokeSuccess(String response) {
                    onSigninSuccess(response);
                }

                @Override
                public void onInvokeFailed(String response) {
                    onSigninFailed(response);
                }
            });
            mAuthTask.invokeWS(getApplicationContext());
        }
    }

    private void onSigninSuccess(String serverResponse) {
        showProgress(false);
        try {
            JSONObject response = new JSONObject(serverResponse);
            boolean status = response.getBoolean("status");
            if (status) {
                //TODO start the main menu
                saveCredentials();
                finish();
            } else {
                if (response.has("message")) {
                    mUsernameView.setError(response.getString("message"));
                } else {
                    mUsernameView.setError("Wrong combination of username and password.");
                }
                mUsernameView.requestFocus();
                mAuthTask = null;
            }
        } catch (JSONException e) {
            //case no message
            e.printStackTrace();
            mAuthTask = null;
        }
    }

    private void onSigninFailed(String serverResponse) {
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
        String username = mSharedPreferences.getString(
                getString(R.string.register_prefs_username_active), "");
        //No credentials have been entered yet.
        if (username.isEmpty()) {
            return;
        }
        mUsernameView.setText(username);
        String encryptedPassword = mSharedPreferences.getString(
                getString(R.string.register_prefs_password_active), "");
        mPasswordView.setText(Encryptor.decrypt(Encryptor.getKey1(username), encryptedPassword));
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

