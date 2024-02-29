package ru.yadoma_realty.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.dataBase.dao.CallbackPhoneDao;
import ru.yadoma_realty.dataBase.dao.FavoriteDao;
import ru.yadoma_realty.dataBase.dao.UserDao;

import java.util.Map;

@Slf4j
public class AllTests {
    @Test
    void test1(){

       var d = CallbackPhoneDao.getPhoneNumberFromLastEntryCallbackPhones();
        System.out.println(d);

        System.out.println(CallbackPhoneDao.getLinkFromLastEntryCallbackPhones());

        Map<Integer, String> entityIdAndUpdatedAtMapFromFavorites = FavoriteDao.getEntityIdAndUpdatedAtMapFromFavorites(1, 1);
        System.out.println(entityIdAndUpdatedAtMapFromFavorites);
        System.out.println(FavoriteDao.getBuildingIdFromFavorites(55));
        System.out.println(UserDao.getUserId("79000000099"));


//
    }
}
