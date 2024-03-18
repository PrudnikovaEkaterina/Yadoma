package ru.yadoma_realty.web.tests.me;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import ru.yadoma_realty.web.pages.MePage;
import ru.yadoma_realty.web.pages.components.HeaderComponent;
import ru.yadoma_realty.web.tests.TestBase;

import static ru.yadoma_realty.utils.GenerationData.*;

@Tag("Web")
@Owner("PrudnikovaEkaterina")
@Story("UserProfile")
public class MeTests extends TestBase {

    MePage mePage = new MePage();
    HeaderComponent header = new HeaderComponent();

    @BeforeEach
    void beforeEach() {
        mePage.openMePageWithPreInstalledAPIAuth(setUsersForTesting());
    }

    @Test
    @DisplayName("Измененить имя пользователя в профиле")
    void changeUserName() {
        String userName = setRandomUserName();
        mePage.changeUserName(userName);
        header.checkAccountName(userName);
    }

    @Test
    @DisplayName("Изменение email пользователя в профиле")
    void changeUserEmail() {
        String userEmail = setRandomEmail();
        mePage
                .changeUserEmail(userEmail)
                .verifyChangeUserEmail(userEmail);
    }
}