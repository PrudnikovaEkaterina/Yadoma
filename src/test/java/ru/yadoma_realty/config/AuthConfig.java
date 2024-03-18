package ru.yadoma_realty.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {
    @Key("authCookieName")
    String authCookieName();

    @Key("authCookieValue")
    String authCookieValue();

    @Key("smsCode")
    String smsCode();
}
