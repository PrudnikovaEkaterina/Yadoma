package ru.yadoma_realty.web.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import ru.yadoma_realty.web.pages.components.CallMeWidgetComponent;
import ru.yadoma_realty.web.pages.components.FooterComponent;
import ru.yadoma_realty.web.pages.components.MoreFiltersModalComponent;

import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NovostroykiPage {

    private final SelenideElement
            SEARCH_ITEM_TITLE_TEXT = $(".search-item__title-text"),
            MORE_FILTERS_BUTTON = $(".more-filters__button-text"),
            NOTIFICATION_INDICATOR = $x("//a[@class='notification-indicator']"),
            SEARCH_ITEM_CONTENT_FIRST = $$(".search-item__content").first(),
            SEARCH_ITEM_CONTENT_LAST = $$(".search-item__content").last(),
            CALL_ME_WIDGET_BUTTON_ICON = $x("//div[@class='call-me-widget search-item__call-me'][1]"),
            SEARCH_NOVOSTROYKI_CONTENT_TOTAL = $(".search-novostroyki-content__total");

    private final ElementsCollection
            SEARCH_ITEM_ADDRESS = $$(".search-item__address"),
            SEARCH_ITEM_DEVELOPER_TEXT = $$(".search-item__developer-text"),
            SEARCH_PRICE_LIST_TABLE = $$(".search-price-list__table"),
            TAG = $$(".base-round-link-button__text"),
            SEARCH_FILTERS_TAGS = $$(".search-filters__tags"),
            SEARCH_ITEM_SLIDER_TAGS = $$(".search-item-slider__tags");

    MoreFiltersModalComponent moreFiltersModal = new MoreFiltersModalComponent();
    CallMeWidgetComponent callMeWidget = new CallMeWidgetComponent();
    FooterComponent footer = new FooterComponent();

    @Step("Open the novostroyki page")
    public void openNovostroykiPage() {
        open("/novostroyki");
    }

    @Step("Open the novostroyki page with get parameters buildingId = {buildingId} and no_flats = 1")
    public void openNovostroykiPageWithFilterNoFlatsAndBuildingId(int buildingId) {
        open("/novostroyki?buildings=" + buildingId + "&no_flats=1");
    }

    @Step("Check the title of the building in the search results")
    public void verifySearchBuildingTitleText(String title) {
        SEARCH_ITEM_TITLE_TEXT.shouldHave(text(title));
    }

    @Step("Check that the building address contains {content}")
    public void verifyResultSearchBuildingContent(String content) {
        sleep(1000);
        SEARCH_ITEM_ADDRESS.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(content)));
    }

    @Step("Check that the building developer contains {developer}")
    public void verifyResultSearchBuildingDeveloper(String developer) {
        SEARCH_ITEM_DEVELOPER_TEXT.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(developer)));
    }

    @Step("Check that flats prices section contains room {room}")
    public void verifyResultSearchByFilterRooms(String room) {
        SEARCH_PRICE_LIST_TABLE.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(room)));
    }

    @Step("Check appearances tag {tagName} after applying filter")
    public void verifyTagVisible(String tagName) {
        TAG.findBy(text(tagName)).shouldBe(visible);
    }

    @Step("Check the opening of the modal by clicking on the All filters button")
    public MoreFiltersModalComponent openMoreFiltersModal() {
        MORE_FILTERS_BUTTON.click();
        moreFiltersModal.verifyModalHeaderText();
        return moreFiltersModal;
    }

    @Step("Get count of buildings on the page")
    public int getSearchNovostroykiContentTotal() {
        return Integer.parseInt(Arrays.stream(SEARCH_NOVOSTROYKI_CONTENT_TOTAL
                        .getText()
                        .split(" "))
                .reduce((e1, e2) -> e2)
                .toString());
    }

    @Step("Apply the housing class filter and check the tags in the building cards")
    public void verifyResultSearchByFilterHousingClass(String housingClass) {
        int countBuildings = getSearchNovostroykiContentTotal();
        if (countBuildings <= 15) SEARCH_ITEM_SLIDER_TAGS.filter(text(housingClass)).shouldHave(size(countBuildings));
        else SEARCH_ITEM_SLIDER_TAGS.filter(text(housingClass)).shouldHave(size(15));
    }

    @Step("Check the counter value All filters")
    public void verifyNotificationIndicator(String numberOfFiltersSelected) {
        NOTIFICATION_INDICATOR.shouldHave(text(numberOfFiltersSelected));
    }

    @Step("Hover to building card")
    public NovostroykiPage hoverSearchItemContent() {
        SEARCH_ITEM_CONTENT_FIRST.hover();
        return this;
    }

    @Step("Click on the call me button")
    public CallMeWidgetComponent openCallMeWidget() {
        CALL_ME_WIDGET_BUTTON_ICON.shouldBe(visible).click();
        return callMeWidget;
    }

    @Step("Scroll down novostroyki page to the footer")
    public void scrollNovostroykiItemsToLastPage() {
        boolean b = true;
        while (b) {
            actions().moveToElement(SEARCH_ITEM_CONTENT_LAST).build().perform();
//          Selenide.executeJavaScript("document.querySelector('.infinity-scroll__viewport').scrollBy(0,1000)"); //было так, пока не изменили скролл на сайте
            Selenide.executeJavaScript("window.scrollBy(0,500)");
            sleep(1000);
            if (footer.footerContainerIsVisible())
                b = false;
        }
    }

    @Step("Get text from search filters tags")
    public String getTextFromSearchFiltersTags() {
        return SEARCH_FILTERS_TAGS.first().getText();
    }

}

