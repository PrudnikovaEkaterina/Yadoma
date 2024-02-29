package ru.yadoma_realty.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.dataBase.dao.UserDao;


@Slf4j
public class AllTests {
    @Test
    void test1() {

//        System.out.println(CallbackPhoneDao.getPhoneNumberFromLastEntry());
//        System.out.println(CallbackPhoneDao.getLinkFromLastEntry());
//
//        Map<Integer, String> entityIdAndUpdatedAtMapFromFavorites = FavoriteDao.collectEntityIdAndUpdatedAtToMapWithSetParameters(1, 1);
//        System.out.println(entityIdAndUpdatedAtMapFromFavorites);
//        System.out.println(FavoriteDao.collectBuildingIdToListWithSetParameters(55, 1));
        System.out.println(UserDao.getUserIdWithSetParameterPhoneNumber("79000000099"));
//        System.out.println(MarketcallBundleBuildingDao.countAllNotDeletedAt());
//        System.out.println(MarketcallBundleBuildingDao.countDistinctBuildingIdNotDeletedAt());

    }
}
