package ru.yadoma_realty.web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FooterComponent {

    private final SelenideElement
            FOOTER_CONTAINER = $(".one-column-footer__container");

    private final ElementsCollection
            FOOTER_MENU_HEADER = $$(".one-column-footer__menu-header");

    @Step("Check footer visibility")
    public boolean footerContainerIsVisible() {
        return FOOTER_CONTAINER.is(Condition.visible);
    }

    @Step("Check footer menu headers")
    public void verifyFooterMenuHeader() {
        FOOTER_MENU_HEADER.first().shouldBe(Condition.visible).shouldHave(Condition.text("Услуги"));
        FOOTER_MENU_HEADER.get(1).shouldBe(Condition.visible).shouldHave(Condition.text("Компания"));
        FOOTER_MENU_HEADER.last().shouldBe(Condition.visible).shouldHave(Condition.text("Пользователям"));
    }
}
