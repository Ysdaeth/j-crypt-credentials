package io.github.ysdaeth.jccredentials.jcc.api;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Symmetric encryptor that requires single secret key to encrypt and decrypt credentials.
 * Interface extends {@link Encryptor}
 */
public interface AsymmetricEncryptor extends Encryptor{
    void setKey(PublicKey publicKey, PrivateKey privateKey);
}
