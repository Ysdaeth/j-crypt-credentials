package io.github.ysdaeth.jccredentials.aes.impl;

import io.github.ysdaeth.jccredentials.aes.impl.config.ModelSerializerConfig;
import io.github.ysdaeth.jccredentials.aes.impl.config.ParametersSerializerConfig;
import io.github.ysdaeth.jccredentials.core.aes.BaseAes;
import io.github.ysdaeth.jccredentials.core.aes.BaseAesFactory;
import io.github.ysdaeth.jccredentials.jcc.api.SymmetricEncryptor;
import io.github.ysdaeth.jccredentials.jcc.common.annotations.Module;
import io.github.ysdaeth.jccredentials.jcc.common.annotations.SerializerCreator;
import io.github.ysdaeth.jccredentials.jcc.common.serializer.ConfigurableSerializer;
import io.github.ysdaeth.jccredentials.jcc.common.serializer.Serializer;

import javax.crypto.SecretKey;
import java.security.KeyException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Class uses AES GCM.
 * Class purpose is to encrypt credentials that are needed to be recovered for
 * service functionality.
 * It is designed to provide Modular Crypt Format standard output.
 * It uses {@link ModelSerializerConfig} for{@link Serializer}.
 * For more details see {@link SymmetricEncryptor}
 * <h5>Example</h5></br>
 * $AES-GCM $v=1 $iv=aBc $encryptedValue  (without spaces)
 */
public class CredentialsEncryptorAesGcm implements SymmetricEncryptor {

    public static final String ID = "AES-GCM";
    private static final String VERSION = "v=1";
    private static final Serializer modelSerializer;
    private static final Serializer paramsSerializer;
    private SecretKey secretKey;
    static{
        modelSerializer = new ConfigurableSerializer(
                new ModelSerializerConfig()
        );
        paramsSerializer = new ConfigurableSerializer(
                new ParametersSerializerConfig()
        );
    }

    private final BaseAes baseAes;

    /**
     * Create instance of symmetric AES GCM.
     * Key provided as an argument is used for encryption and decryption.
     * @param secretKey key for encryption and decryption
     */
    public CredentialsEncryptorAesGcm(SecretKey secretKey){
        this.secretKey = validateKey(secretKey);
        baseAes = BaseAesFactory.getInstance("GCM");
    }

    /**
     * Encrypt character sequence and return Modular Crypt Format string value.
     * It uses {@link Serializer} configured to match MCF format.
     * It generates random 96bit initial vector.
     * Although initial vector collision chance is extremely low it is recommended
     * to rotate a secret keys. Secret passed as an argument is cloned and filled with 0 bytes
     * after encryption. Secret passed as an argument itself is not modified.
     * @param secret credentials to be encrypted
     * @return Modular Crypt Format string representation
     * @throws KeyException when key does not match or is invalid
     */
    @Override
    public String encrypt(byte[] secret) throws KeyException {
        byte[] credentials = secret.clone();
        try{
            return encryptUnsafe(credentials);
        }finally {
            Arrays.fill(credentials,(byte)0);
        }
    }
    private String encryptUnsafe(byte[] secret) throws KeyException{
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        byte[] encrypted = baseAes.encrypt(secret,secretKey,iv.clone());
        String params = paramsSerializer.serialize(new ParamsMcf(iv));
        ModelMcf modelMcf = new ModelMcf(params,encrypted);
        return modelSerializer.serialize(modelMcf);
    }

    /**
     * Decrypt Modular Crypt Format string representation with secret key
     * and return decrypted credentials as string. It uses {@link Serializer} configured to match
     * MCF format.
     * @param serializedMcf credentials to be decrypted from Modular Crypt Format
     *                      string representation
     * @return decrypted credentials as string
     * @throws KeyException when key does not match or is invalid
     */
    @Override
    public byte[] decrypt(String serializedMcf) throws KeyException {
        ModelMcf model = modelSerializer.deserialize(serializedMcf,ModelMcf.class);
        ParamsMcf params = paramsSerializer.deserialize(model.params,ParamsMcf.class);
        return baseAes.decrypt(model.encrypted,secretKey,params.iv);
    }

    /**
     * @return Identifier of this algorithm instance
     */
    @Override
    public String identifier() {
        return ID;
    }

    /**
     * @return version of this algorithm instance
     */
    @Override
    public String version() {
        return VERSION;
    }

    /**
     * Set key for encryption and decryption.
     * @param secretKey key for encryption and decryption
     * @throws IllegalArgumentException when key algorithm does not match, or null
     */
    @Override
    public void setKey(SecretKey secretKey) {
        this.secretKey = validateKey(secretKey);
    }

    private static SecretKey validateKey(SecretKey secretKey){
        if(secretKey == null) throw new IllegalArgumentException("Secret key must not be null");
        String keyAlgorithm = secretKey.getAlgorithm();
        if(!"AES".equals(keyAlgorithm)) throw new IllegalArgumentException("Required key algorithm is AES, but provided was: "+ keyAlgorithm);
        return secretKey;
    }

    /**
     * Class is used as model for Modular Crypt Format representation for
     * this instance algorithm output.
     */
    private static class ModelMcf{
        @Module(order = 0)
        private final String identifier;
        @Module(order = 1)
        private final String version;
        @Module(order = 2)
        private final String params;
        @Module(order = 3)
        private final byte[] encrypted;

        @SerializerCreator
        public ModelMcf(String identifier,String version, String params,byte[] encrypted){
            this.identifier = identifier;
            this.version = version;
            this.params = params;
            this.encrypted = encrypted;
        }

        public ModelMcf(String params,byte[] encrypted){
            this.identifier = ID;
            this.version = VERSION;
            this.params = params;
            this.encrypted = encrypted;
        }
    }
    /**
     * Class is used as model for Modular Crypt Format parameters representation for
     * this instance algorithm output.
     */
    private static class ParamsMcf{
        @Module(order = 0)
        private final byte[] iv;

        @SerializerCreator
        public ParamsMcf(byte[] iv){
            this.iv = iv;
        }
    }
}
