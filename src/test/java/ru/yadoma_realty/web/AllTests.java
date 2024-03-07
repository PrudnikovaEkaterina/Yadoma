package ru.yadoma_realty.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.dataBase.dao.*;

import java.util.Map;


@Slf4j
public class AllTests {
    @Test
    void test1() {
//        System.out.println(CallbackPhoneDao.getPhoneNumberFromLastEntry());
//        System.out.println(CallbackPhoneDao.getLinkFromLastEntry());
//        Map<Integer, String> entityIdAndUpdatedAtMapFromFavorites = FavoriteDao.collectEntityIdAndUpdatedAtToMapWithSetParameters(1, 1);
//        System.out.println(entityIdAndUpdatedAtMapFromFavorites);
//        System.out.println(FavoriteDao.collectBuildingIdToListWithSetParameters(55, 1));
//        System.out.println(UserDao.getUserIdWithSetParameterPhoneNumber("79000000099"));
//        System.out.println(MarketcallBundleBuildingDao.countAllNotDeletedAt());
//        System.out.println(MarketcallBundleBuildingDao.countDistinctBuildingIdNotDeletedAt());
//        System.out.println(MarketcallBundleDao.collectTitleToListNotDeletedAt());
//        var c = MarketcallBundleDao.getTitleNotDeletedAtAndSetParameterExternalId(5404);
//        System.out.println(MarketcallBundleDao.collectExternalIdToListNotDeletedAt());
//        System.out.println(MarketcallBundleBuildingDao.countDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(5404));
//        System.out.println(MarketcallBundleBuildingDao.collectDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(5404));
//        System.out.println(BuildingDao.collectBuildingRoomTypeValues(17669));
//        System.out.println(BuildingDao.collectBuildingPricesTitleToList(17799));
//        System.out.println(BuildingDao.collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistUnitPriceMin());
//        System.out.println(BuildingDao.collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistAreaMin());
//        System.out.println(BuildingDao.collectBuildingIdToListWithSetParentId(17799));
//        System.out.println(BuildingDao.getBuildingReleaseYear(3744));
//        System.out.println(BuildingDao.getBuildingReleaseQuarter(3744));
//        System.out.println(BuildingDao.collectDistinctBuildingTitleEngWithFlatsAndSetRegionCodeToList());
        System.out.println(BuildingDao.collectDistinctBuildingGarObjectIdWithSetRegionCodeAndFlatsStatus1ToList());
    }
}
