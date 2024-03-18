package ru.yadoma_realty.web.pages;

import io.qameta.allure.Step;
import ru.yadoma_realty.api.steps.auth_api_steps.AuthApiSteps;
import ru.yadoma_realty.enums.UsersForTesting;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;


public class MainPage {

    @Step("Open the main page with pre-installed API authorization")
    public void openMainPageWithApiAuth(UsersForTesting usersForTesting) {
        AuthApiSteps.authAndSetAuthCookiesToBrowser(usersForTesting);
        open(baseUrl);
    }
}
