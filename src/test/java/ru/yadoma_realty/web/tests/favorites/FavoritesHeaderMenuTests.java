package ru.yadoma_realty.web.tests.favorites;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.api.models.auth_model.LoginResponse;
import ru.yadoma_realty.api.steps.auth_api_steps.AuthApiSteps;
import ru.yadoma_realty.enums.UsersForTesting;
import ru.yadoma_realty.web.pages.FavoritesPage;
import ru.yadoma_realty.web.tests.TestBase;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yadoma_realty.enums.AuthCookies.*;
import static ru.yadoma_realty.enums.AuthCookies.REFERRAL_CODE;
import static ru.yadoma_realty.utils.GenerationData.setUsersForTesting;


@Tag("Web")
@Owner("PrudnikovaEkaterina")
@Story("FavoritesHeaderMenu")
public class FavoritesHeaderMenuTests extends TestBase {
    FavoritesPage favoritesPage = new FavoritesPage();
    UsersForTesting user;
    LoginResponse loginResponse;


    @BeforeEach
    void beforeEach() {
        user = setUsersForTesting();
        loginResponse = AuthApiSteps.auth(user);
        var cookiesMap = AuthApiSteps.collectAuthCookies(loginResponse);
        favoritesPage
                .openFavoritesPageWithAPIAuth(cookiesMap.get(REFRESH_TOKEN), cookiesMap.get(SESSION_EXPIRES_AT), cookiesMap.get(REFERRAL_CODE))
                .checkFavoritesHeaderTitle();
    }

    @Test
    @DisplayName("Проверить содержание header menu страницы Мое избранное")
    void checkFavoritesHeaderMenu() {
        favoritesPage.checkFavoritesHeaderMenu();
    }

    @Test
    @DisplayName("Проверить значение счетчика Жилые комплексы в header menu страницы Мое избранное")
    void checkFavoritesBuildingsCount() {
        int countFavoritesBuildingsExpected = loginResponse.user().favoritesBuildingsCount();
        int countFavoritesBuildingsActual = favoritesPage.getFavoritesBuildingsCount();
        assertThat(countFavoritesBuildingsActual, is(countFavoritesBuildingsExpected));
    }

    @Test
    @DisplayName("Проверить значение счетчика Квартиры в header menu страницы Мое избранное")
    void checkFavoritesFlatsCount() {
        int countFavoritesFlatsExpected = loginResponse.user().favoritesFlatsCount();
        int countFavoritesFlatsActual = favoritesPage.getFavoritesFlatsCount();
        assertThat(countFavoritesFlatsActual, is(countFavoritesFlatsExpected));
    }

    @Test
    @DisplayName("Проверить значение счетчика Рекомендации менеджера в header menu страницы Мое избранное")
    void checkRecommendationsCount() {
        int countRecommendationsExpected = loginResponse.user().recommendationsBuildingsCount() + loginResponse.user().recommendationsFlatsCount();
        int countRecommendationsActual = favoritesPage.getRecommendationsCount();
        assertThat(countRecommendationsActual, is(countRecommendationsExpected));
    }
}
