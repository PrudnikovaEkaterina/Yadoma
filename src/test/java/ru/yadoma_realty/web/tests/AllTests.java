package ru.yadoma_realty.web.tests;

import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.api.steps.auth_api_steps.AuthApiSteps;
import ru.yadoma_realty.web.pages.MePage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;



@Slf4j
public class AllTests extends TestBase {
    @Test
    void test1() {
//        System.out.println(CallbackPhoneDao.getPhoneNumberFromLastEntry());
//        System.out.println(CallbackPhoneDao.getLinkFromLastEntry());
//        Map<Integer, String> entityIdAndUpdatedAtMapFromFavorites = FavoriteDao.collectEntityIdAndUpdatedAtToMapWithSetParameters(1386, EntityType.BUILDING);
//        System.out.println(entityIdAndUpdatedAtMapFromFavorites);
//        System.out.println(FavoriteDao.collectBuildingIdToListWithSetParameters(3, EntityType.BUILDING));
//        System.out.println(UserDao.getUserIdWithSetParameterPhoneNumber("79000000099"));
//        System.out.println(MarketcallBundleBuildingDao.countAllNotDeletedAt());
//        System.out.println(MarketcallBundleBuildingDao.countDistinctBuildingIdNotDeletedAt());
//        System.out.println(MarketcallBundleDao.collectTitleToListNotDeletedAt());
//        var c = MarketcallBundleDao.getTitleNotDeletedAtAndSetParameterExternalId(5404);
//        System.out.println(MarketcallBundleDao.collectExternalIdToListNotDeletedAt());
//        System.out.println(MarketcallBundleBuildingDao.countDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(5404));
//        System.out.println(MarketcallBundleBuildingDao.collectDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(5404));
//        System.out.println(BuildingDao.collectBuildingRoomTypeValuesToList(17669));
//        System.out.println(BuildingDao.collectBuildingPricesTitleToList(17799));
//        System.out.println(BuildingDao.collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistUnitPriceMin());
//        System.out.println(BuildingDao.collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistAreaMin());
//        System.out.println(BuildingDao.collectBuildingIdToListWithSetParentId(17799));
//        System.out.println(BuildingDao.getBuildingReleaseYear(3744));
//        System.out.println(BuildingDao.getBuildingReleaseQuarter(3744));
//        System.out.println(BuildingDao.collectDistinctBuildingTitleEngWithSetRegionCodeAndFlatsStatusToList());
//        System.out.println(BuildingDao.collectDistinctBuildingGarObjectIdWithSetRegionCodeAndFlatsStatusToList());
//        System.out.println(BuildingDao.collectDistinctBuildingIdWithSetRegionCodeAndFlatsStatusToList().size());
//        System.out.println(BuildingDao.collectBuildingIdWithoutFlatsWithSetRegionCodeAndExistPricesAndNullParentId());
//        System.out.println(BuildingDao.getTitleEngWithSetBuildingId(17799));
//        System.out.println(BuildingDao.getBuildingPriceMinWithSetBuildingIdAndPricesTitle(17799, "Продажа"));
//        System.out.println(FlatDao.collectDistinctBuildingIdWithSetFloorAndStatus(105));
//        System.out.println(FlatDao.collectDistinctBuildingIdWithSetPaymentMethodAndStatus("subsidy"));
//        System.out.println(FlatDao.collectDistinctBuildingIdWithSetFinishingAndStatus(FinishingTypes.FURNITURE));
//        System.out.println(FlatDao.collectDistinctHouseIdWithSetBuildingIdAndStatus(9246));
//        System.out.println(FlatDao.collectDistinctHouseIdWithSetRegionCodeAndStatus());
//        System.out.println(FlatDao.countAllWithSetBuildingIdAndFlatStatusWithoutHouseId(4496));
//        System.out.println(FlatDao.countAllWithSetBuildingIdAndFlatStatus(4496));
//        System.out.println(FlatDao.countAllWithFilterPriceTotalAndSetBuildingIdAndFlatStatus(4496, 8000000, 9000000));
//        System.out.println(FlatDao.countAllWithFilterAreaTotalAndSetBuildingIdAndFlatStatus(4496, 20.0, 25.0));
//        System.out.println(FlatDao.countAllWithFilterFloorAndSetBuildingIdAndFlatStatus(4496, 5, 7));
//        AuthApiSteps.authRegister(UsersForTesting.USER_WITHOUT_MANAGER);
//        var a = AuthApiSteps.authLogin(UsersForTesting.USER_WITHOUT_MANAGER);
//        String refreshToken = AuthApiSteps.getRefreshToken(a);
//        String referralCode = AuthApiSteps.getReferralCode(a);
//        String sessionExpiresAtCookie = AuthApiSteps.getSessionExpiresAtCookie(a);
//        System.out.println(referralCode);
//        MePage mePage = new MePage();
//        mePage.openMePageWithPreInstalledAPIAuth();

        Selenide.open(baseUrl);


    }
}
