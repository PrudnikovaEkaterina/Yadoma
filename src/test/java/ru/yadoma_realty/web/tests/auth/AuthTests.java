package ru.yadoma_realty.web.tests.auth;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.*;
import ru.yadoma_realty.config.AuthConfig;
import ru.yadoma_realty.enums.UsersForTesting;
import ru.yadoma_realty.utils.GenerationData;
import ru.yadoma_realty.web.pages.AuthPage;
import ru.yadoma_realty.web.pages.components.HeaderComponent;
import ru.yadoma_realty.web.tests.TestBase;

import static ru.yadoma_realty.utils.GenerationData.*;


@Tag("Web")
@Story("Auth")
@Owner("PrudnikovaEkaterina")
public class AuthTests extends TestBase {
    AuthPage authPage = new AuthPage();
    HeaderComponent header = new HeaderComponent();
    AuthConfig authConfig = ConfigCache.getOrCreate(AuthConfig.class);

    @BeforeEach
    void beforeEach() {
        authPage.openAuthPage();
    }

    @Test
    @Disabled
    @DisplayName("Позитивная проверка регистрации нового пользователя")
    void registrationNewUserSuccessful() {
        String phoneNumber = setRandomPhone();
        String smsCode = authConfig.smsCode();
        String userName = setRandomUserName();
        authPage.
                setPhone(phoneNumber)
                .clickCheckboxAuthConfirm()
                .clickButtonSendCode()
                .setSmsCode(smsCode)
                .checkAuthTitle()
                .setUserName(userName)
                .clickCompleteAuthNameButton();
        header.checkAccountName(userName);
        header.hoverHeaderAccountIconAndCheckUserMenuDropdownText();
    }

    @Test
    @DisplayName("Позитивная проверка авторизации пользователя")
    void authUserSuccessful() {
        String smsCode = authConfig.smsCode();
        authPage.
                setPhone(setUsersForTesting().getPhoneNumber())
                .clickCheckboxAuthConfirm()
                .clickButtonSendCode()
                .setSmsCode(smsCode);
        header.hoverHeaderAccountIconAndCheckUserMenuDropdownText();
    }

}
