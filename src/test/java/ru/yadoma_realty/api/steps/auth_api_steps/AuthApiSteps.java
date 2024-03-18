package ru.yadoma_realty.api.steps.auth_api_steps;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.aeonbits.owner.ConfigCache;
import ru.yadoma_realty.api.models.auth_models.LoginRequest;
import ru.yadoma_realty.api.models.auth_models.LoginResponse;
import ru.yadoma_realty.config.AuthConfig;
import ru.yadoma_realty.enums.UsersForTesting;

import java.sql.Timestamp;
import java.util.Optional;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static ru.yadoma_realty.api.helpers.CustomAllureListener.withCustomTemplates;
import static ru.yadoma_realty.api.specifications.Specification.*;

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

    @Step("Authorization and set refreshToken, sessionExpiresAt, referralCode to browser")
    public static void authAndSetAuthCookiesToBrowser(UsersForTesting usersForTesting) {
        var loginResponse = AuthApiSteps.auth(usersForTesting);
        var refreshToken = AuthApiSteps.getRefreshToken(loginResponse);
        var referralCode = Optional.ofNullable(AuthApiSteps.getReferralCode(loginResponse));
        var sessionExpiresAtCookie = AuthApiSteps.getSessionExpiresAtCookie(loginResponse);
        AuthApiSteps.setAuthCookiesToBrowser(refreshToken, sessionExpiresAtCookie, referralCode);
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

    @Step("Set refreshToken, sessionExpiresAt, referralCode to browser")
    public static void setAuthCookiesToBrowser(String refreshToken, String sessionExpiresAt, Optional<String> referralCode) {
        open(baseUrl + "/build/desktop/assets/header-logo.79405d96.svg");

        var refreshCookie = new org.openqa.selenium.Cookie("refresh_token", refreshToken);
        getWebDriver().manage().addCookie(refreshCookie);

        if (referralCode.isPresent()) {
            var referralCookie = new org.openqa.selenium.Cookie("ref", referralCode.get());
            getWebDriver().manage().addCookie(referralCookie);
        }

        Selenide.localStorage().setItem("session_expires_at", sessionExpiresAt);

        Selenide.refresh();
    }

}
