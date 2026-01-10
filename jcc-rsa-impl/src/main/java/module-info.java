module io.github.ysdaeth.jcryptcredentials.rsa.impl {
    requires io.github.ysdaeth.j_crypt_credentials.jcc.api;
    requires io.github.ysdaeth.jcryptcredentials.jcc.common;
    requires io.github.ysdaeth.jcryptcredentials.internal.core.rsa;
    requires io.github.ysdaeth.jcryptcredentials.internal.core.aes;
    opens io.github.ysdaeth.jccredentials.rsa.impl to io.github.ysdaeth.jcryptcredentials.jcc.common;
}