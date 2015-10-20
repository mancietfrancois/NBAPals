package fr.mnf.nbapalsapp.register;

/**
 * Created by Francois on 19/10/2015.
 */
public class RegisterUtilities {

    public static final int PASSWORD_LENGTH = 6;
    public static final int EMAIL_LENGTH = 4;

    public static boolean isEmailValid(String email) {
        return (email.length() > EMAIL_LENGTH && email.matches("[A-Za-z0-9]*"));
    }

    public static boolean isPasswordValid(String password) {
        return (password.length() > PASSWORD_LENGTH && password.matches("[A-Za-z0-9]*"));
    }
}
