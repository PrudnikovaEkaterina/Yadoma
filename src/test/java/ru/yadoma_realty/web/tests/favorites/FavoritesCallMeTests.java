package ru.yadoma_realty.web.tests.favorites;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.yadoma_realty.api.steps.auth_api_steps.AuthApiSteps;
import ru.yadoma_realty.api.steps.user_favorites_api_steps.UserFavoritesApiSteps;
import ru.yadoma_realty.enums.UsersForTesting;
import ru.yadoma_realty.utils.StringHelper;
import ru.yadoma_realty.web.pages.FavoritesPage;
import ru.yadoma_realty.web.pages.components.CallMeWidgetComponent;
import ru.yadoma_realty.web.tests.TestBase;
import static ru.yadoma_realty.enums.AuthCookies.*;
import static ru.yadoma_realty.utils.GenerationData.*;

@Tag("Web")
@Owner("PrudnikovaEkaterina")
@Story("FavoritesCallMe")
public class FavoritesCallMeTests extends TestBase {
    FavoritesPage favoritesPage = new FavoritesPage();
    CallMeWidgetComponent callMeWidgetComponent = new CallMeWidgetComponent();
    UsersForTesting user;

    @BeforeEach
    void beforeEach() {
        user = setUsersForTesting();
        var loginResponse = AuthApiSteps.auth(user);
        var cookiesMap = AuthApiSteps.collectAuthCookies(loginResponse);
        UserFavoritesApiSteps.addBuildingToUserFavoritesUseAccessToken(cookiesMap.get(ACCESS_TOKEN));
        favoritesPage
                .openFavoritesPageWithAPIAuth(cookiesMap.get(REFRESH_TOKEN), cookiesMap.get(SESSION_EXPIRES_AT), cookiesMap.get(REFERRAL_CODE))
                .checkFavoritesHeaderTitle();
    }

    @Test
    @DisplayName("Проверить появление callback phone modal после клика на кнопку Заказать звонок на странице Мое избранное")
    void checkFavoritesManagerBlock() {
        favoritesPage
                .hoverFirstSearchItem()
                .clickFirstCallMeWidgetButton()
                .checkCallbackPhoneModalTitle();
    }

    @Test
    @DisplayName("Проверить, что в callback phone modal отображается телефон пользователя")
    void checkPhoneInputValue() {
        String expectedPhone = user.getPhoneNumber();
        favoritesPage
                .hoverFirstSearchItem()
                .clickFirstCallMeWidgetButton();
        String actualPhone = callMeWidgetComponent.getPhoneInputValue();
        String actualPhoneFormatted = StringHelper.getAllNumbersFromString(actualPhone);
        Assertions.assertEquals(expectedPhone, actualPhoneFormatted);
    }
}
