package io.github.ysdaeth.jccredentials.core.aes;

import javax.crypto.SecretKey;
import java.security.KeyException;

/**
 * interface for internal base AES implementation.It provides
 * base interface for other dependencies and should not be used outside
 * of this project.
 */
public interface BaseAes {
    byte[] encrypt(byte[] rawSecret, SecretKey secretKey, byte[] initialVector) throws KeyException;
    byte[] decrypt(byte[] encrypted,SecretKey secretKey, byte[] initialVector) throws KeyException;
}
