package ru.yadoma_realty.web.tests.favorites;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import ru.yadoma_realty.web.tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Tag("Web")
@Owner("PrudnikovaEkaterina")
@Story("FavoritesItemsDateAdd")
public class FavoritesItemsDateTests extends TestBase {
//    FavoritesPage favoritesPage = new FavoritesPage();
//    String phoneNumber = GenerationData.setRandomUserPhone();
//    Response response = AuthApiSteps.getLoginResponse(phoneNumber);
//    String accessToken = AuthApiSteps.getAccessTokenUseLoginResponse(response);
//
//    @Test
//    @DisplayName("Проверить дату добавления ЖК на странице Мое избранное")
//    void checkFavoritesBuildingsDateAdd() {
//        UserFavoritesApiSteps.addBuildingToUserFavoritesUseAccessToken(accessToken);
//        favoritesPage
//                .openFavoritesPageWithAuthUseLoginResponse(response)
//                .checkFavoritesHeaderTitle();
//        int userId = response.as(LoginResponseModel.class).getUser().getId();
//        Map<Integer, String> entityIdAndUpdatedDateMap = FavoritesDao.selectEntityIdAndUpdatedFromFavorites(userId, 1);
//        Map<Integer, String> priceFromUpdatedDateMap = entityIdAndUpdatedDateMap.entrySet()
//                .stream()
//                .collect(toMap(el -> CardNovostroykiApiSteps.getPriceFrom(el.getKey()), Map.Entry::getValue));
//        TreeMap<Integer, String> collectSort = new TreeMap<>(priceFromUpdatedDateMap);
//        List<String> buildingsDateAddExpected = new ArrayList<>(collectSort.values());
//        List<String> buildingsDateAddActual = favoritesPage.getBuildingsDateText();
//        assertThat(buildingsDateAddActual, is(buildingsDateAddExpected));
//    }
}
