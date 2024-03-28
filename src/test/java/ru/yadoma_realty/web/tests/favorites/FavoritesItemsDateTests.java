package ru.yadoma_realty.web.tests.favorites;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import org.junit.jupiter.api.Test;
import ru.yadoma_realty.api.steps.auth_api_steps.AuthApiSteps;
import ru.yadoma_realty.api.steps.user_favorites_api_steps.UserFavoritesApiSteps;
import ru.yadoma_realty.web.pages.FavoritesPage;
import ru.yadoma_realty.web.tests.TestBase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.yadoma_realty.enums.AuthCookies.*;
import static ru.yadoma_realty.enums.AuthCookies.REFERRAL_CODE;
import static ru.yadoma_realty.utils.GenerationData.setUsersForTesting;


@Tag("Web")
@Owner("PrudnikovaEkaterina")
@Story("FavoritesItemsDateAdd")
public class FavoritesItemsDateTests extends TestBase {
    FavoritesPage favoritesPage = new FavoritesPage();

    @Test
    @DisplayName("Проверить дату добавления ЖК на странице Мое избранное")
    void checkFavoritesBuildingsDateAdd() {
        var user = setUsersForTesting();
        var loginResponse = AuthApiSteps.auth(user);
        var cookiesMap = AuthApiSteps.collectAuthCookies(loginResponse);
        var accessToken = cookiesMap.get(ACCESS_TOKEN);

        UserFavoritesApiSteps.addBuildingToUserFavoritesUseAccessToken(accessToken);

        favoritesPage
                .openFavoritesPageWithAPIAuth(cookiesMap.get(REFRESH_TOKEN),
                        cookiesMap.get(SESSION_EXPIRES_AT),
                        cookiesMap.get(REFERRAL_CODE))
                .checkFavoritesHeaderTitle();

        List<String> buildingsDateAddExpected = UserFavoritesApiSteps.collectDatesAddedBuildingsToFavorites(accessToken);
        List<String> buildingsDateAddActual = favoritesPage.getBuildingsDateText();

        assertThat(buildingsDateAddActual, is(buildingsDateAddExpected));
    }
}
