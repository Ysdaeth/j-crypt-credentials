module io.github.ysdaeth.jcryptcredentials.aes.impl{
    requires io.github.ysdaeth.j_crypt_credentials.jcc.api;
    requires io.github.ysdaeth.jcryptcredentials.jcc.common;
    requires io.github.ysdaeth.jcryptcredentials.internal.core.aes;
    opens io.github.ysdaeth.jccredentials.aes.impl to io.github.ysdaeth.jcryptcredentials.jcc.common;
}