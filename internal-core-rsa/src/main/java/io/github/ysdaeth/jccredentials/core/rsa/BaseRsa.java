package io.github.ysdaeth.jccredentials.core.rsa;

import java.security.KeyException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * interface for internal base RSA implementation.It provides
 * base interface for other dependencies and should not be used outside
 * of this project.
 */
public interface BaseRsa {
    byte[] encrypt(byte[] rawData, PublicKey publicKey) throws KeyException;
    byte[] decrypt(byte[] encrypted, PrivateKey privateKey) throws KeyException;
}
