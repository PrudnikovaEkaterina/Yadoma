package ru.yadoma_realty.api.steps.auth_api_steps;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.aeonbits.owner.ConfigCache;
import ru.yadoma_realty.api.models.auth_model.LoginRequest;
import ru.yadoma_realty.api.models.auth_model.LoginResponse;
import ru.yadoma_realty.config.AuthConfig;
import ru.yadoma_realty.enums.AuthCookies;
import ru.yadoma_realty.enums.UsersForTesting;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static ru.yadoma_realty.api.helpers.CustomAllureListener.withCustomTemplates;
import static ru.yadoma_realty.api.specifications.Specification.*;
import static ru.yadoma_realty.enums.AuthCookies.*;

public class AuthApiSteps {
    static AuthConfig authConfig = ConfigCache.getOrCreate(AuthConfig.class);
    static String password = authConfig.smsCode();
    static String authCookieName = authConfig.authCookieName();
    static String authCookieValue = authConfig.authCookieValue();
    static Cookie authCookie = new Cookie.Builder(authCookieName, authCookieValue).build();

    /*Первый этап авторизации;*/
    private static void authRegister(UsersForTesting usersForTesting) {
        given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .cookie(authCookie)
                .body(new LoginRequest(usersForTesting.getPhoneNumber()))
                .when()
                .post("/api/auth/register")
                .then()
                .spec(responseSpec204);
    }

    /*Второй этап авторизации;*/
    private static LoginResponse authLogin(UsersForTesting usersForTesting) {
        return given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .cookie(authCookie)
                .body(new LoginRequest(password, usersForTesting.getPhoneNumber()))
                .when()
                .post("/api/auth/login")
                .getBody().as(LoginResponse.class);
    }

    /*Полная авторизация;*/
    @Step("Login")
    public static LoginResponse auth(UsersForTesting usersForTesting) {
        authRegister(usersForTesting);
        return authLogin(usersForTesting);
    }

    @Step("Logout")
    public static void logout(String accessToken) {
        given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/api/auth/logout")
                .then()
                .spec(responseSpec200)
                .body("message", is("Successfully logged out"));
    }

    @Step("Get refresh token from loginResponse")
    public static String getRefreshToken(LoginResponse loginResponse) {
        return loginResponse.refreshToken();
    }

    @Step("Get session expiresAt cookie from loginResponse")
    public static String getSessionExpiresAtCookie(LoginResponse loginResponse) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampTime = timestamp.getTime();
        return String.valueOf(timestampTime + loginResponse.refreshExpiresIn()).substring(0, 10);
    }

    @Step("Get referral code from loginResponse")
    public static String getReferralCode(LoginResponse loginResponse) {
        return loginResponse.user().referralCode();
    }

    @Step("Get access token from loginResponse")
    public static String getAccessToken(LoginResponse loginResponse) {
        return loginResponse.accessToken();
    }

    @Step("Collect list auth cookies from loginResponse")
    public static Map<Enum<AuthCookies>, String> collectAuthCookies(LoginResponse loginResponse) {
        var resultMap = new HashMap<Enum<AuthCookies>, String>();
        resultMap.put(REFRESH_TOKEN, getRefreshToken(loginResponse));
        resultMap.put(SESSION_EXPIRES_AT, getSessionExpiresAtCookie(loginResponse));
        resultMap.put(REFERRAL_CODE, getReferralCode(loginResponse));
        resultMap.put(ACCESS_TOKEN, getAccessToken(loginResponse));
        return resultMap;
    }

    @Step("Authorization and set refreshToken, sessionExpiresAt, referralCode to browser")
    public static void authAndSetAuthCookiesToBrowser(UsersForTesting usersForTesting) {
        var loginResponse = AuthApiSteps.auth(usersForTesting);
        var refreshToken = AuthApiSteps.getRefreshToken(loginResponse);
        var referralCode = AuthApiSteps.getReferralCode(loginResponse);
        var sessionExpiresAtCookie = AuthApiSteps.getSessionExpiresAtCookie(loginResponse);
        AuthApiSteps.setAuthCookiesToBrowser(refreshToken, sessionExpiresAtCookie, referralCode);
    }

    @Step("Set refreshToken, sessionExpiresAt, referralCode to browser")
    public static void setAuthCookiesToBrowser(String refreshToken, String sessionExpiresAt, String referralCode) {
        open(baseUrl + "/build/desktop/assets/header-logo.79405d96.svg");


        var refreshCookie = new org.openqa.selenium.Cookie("refresh_token", refreshToken);
        getWebDriver().manage().addCookie(refreshCookie);

        if (referralCode!=null) {
            var referralCookie = new org.openqa.selenium.Cookie("ref", referralCode);
            getWebDriver().manage().addCookie(referralCookie);
        }

        Selenide.localStorage().setItem("session_expires_at", sessionExpiresAt);

        Selenide.refresh();
    }

}
