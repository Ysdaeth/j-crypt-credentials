package io.github.ysdaeth.jccredentials.core.aes;

/**
 * Factory for internal base AES implementations only. It provides
 * base implementations for other dependencies and should not be used outside
 * of this project.
 */
public class BaseAesFactory {

    public static BaseAes getInstance(String identifier){
        return switch (identifier){
            case "GCM" ->new BaseAesGcm();
            default -> throw new IllegalArgumentException("No such instance: "+ identifier);
        };
    }
}
