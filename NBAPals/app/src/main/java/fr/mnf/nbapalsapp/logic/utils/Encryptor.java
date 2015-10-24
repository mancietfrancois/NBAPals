package fr.mnf.nbapalsapp.logic.utils;


import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Francois on 22/10/2015.
 */
public class Encryptor {

    private static final String LOG_TAG = "Encryptor";

    public static final int KEY_SIZE = 16;
    public static final String DEFAULT_KEY = "ThisIsASecretKey";
    public static final String KEY_2 = "fr.mnf.nbapals.=";

    public static String getKey1(String key1) {
        if (key1.length() == 0) {
            return DEFAULT_KEY;
        }
        if (key1.length() > KEY_SIZE) {
            return key1.substring(0, KEY_SIZE);
        } else {
            while(key1.length() < KEY_SIZE) {
                key1 = key1 + key1;
            }
            return key1.substring(0, KEY_SIZE);
        }
    }

    public static String encrypt(String key1, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(KEY_2.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            Log.d(LOG_TAG, "encrypted string:"
                    + Base64.encodeToString(encrypted, Base64.DEFAULT));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key1, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(KEY_2.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
