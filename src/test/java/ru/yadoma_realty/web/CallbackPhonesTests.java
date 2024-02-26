package ru.yadoma_realty.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.yadoma_realty.dataBase.dao.CallbackPhonesDao;

@Slf4j
public class CallbackPhonesTests {
    @Test
    void test1(){
        CallbackPhonesDao.getLastEntryFromCallbackPhonesTables();
    }
}
