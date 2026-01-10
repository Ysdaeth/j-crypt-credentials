package io.github.ysdaeth.jccredentials.core.rsa;

/**
 * Factory for internal base RSA implementations only. It provides
 * base implementations for other dependencies and should not be used outside
 * of this project.
 */
public final class BaseRsaFactory {
    private BaseRsaFactory(){}
    public static BaseRsa getInstance(String identifier){
        return switch (identifier){
            case "OAEP" -> new BaseRsaOaep();
            default -> throw new IllegalArgumentException("No such instance:" +identifier);
        };
    }
}
