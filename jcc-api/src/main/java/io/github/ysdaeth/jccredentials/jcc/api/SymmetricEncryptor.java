package io.github.ysdaeth.jccredentials.jcc.api;

import javax.crypto.SecretKey;

/**
 * Symmetric encryptor that requires single secret key to encrypt and decrypt credentials.
 * Interface is extended with {@link Encryptor}
 */
public interface SymmetricEncryptor extends Encryptor {
    void setKey(SecretKey secretKey);
}
