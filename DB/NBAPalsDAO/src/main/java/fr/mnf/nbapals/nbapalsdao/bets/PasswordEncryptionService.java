/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.nbapalsdao.bets;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Francois
 */
public class PasswordEncryptionService {

    public static boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

        // Authentication succeeds if encrypted password that the user entered
        return Arrays.equals(encryptedPassword.getBytes(), encryptedAttemptedPassword.getBytes());
    }

    public static String getEncryptedPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        return toHex(f.generateSecret(spec).getEncoded());
    }

    public static String generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return toHex(salt);
    }
    
    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
}
