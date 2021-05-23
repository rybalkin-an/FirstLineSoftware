package de.amazon.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import de.amazon.pages.MainPage;
import de.amazon.pages.SearchResultPage;
import de.amazon.pages.ShoppingCartPage;
import de.amazon.pages.elements.Header;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyingScenario {

    private String host = "https://www.amazon.de/";
    private String itemToSearch = "Drucker";

    @BeforeEach
    void setup(){
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        open(host);
    }

    @AfterEach
    void tearDown(){
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Search for “Drucker”; \n" +
            "Select the first product from list and add to basket; \n" +
            "Go back to search result; \n" +
            "Select the 2nd product from list and add to basket; \n" +
            "Go to shopping basket \n" +
            "1. Validate added items are visible on “Shopping basket” list ")
    void addTwoItemsToTheShoppingCard() {
        String firstItemName;
        String secondItemName;

        MainPage mainPage = new MainPage();
        firstItemName = mainPage
                .clickOnAcceptCookiesButton()
                .searchItem(itemToSearch)
                .getTextElement(1);

        new SearchResultPage().
                clickOnElement(1)
                .clickAddToCartButton();
        Selenide.back();
        Selenide.back();

        secondItemName = mainPage
                .searchItem(itemToSearch)
                .getTextElement(2);

        new SearchResultPage()
                .clickOnElement(2)
                .clickAddToCartButton();
        Selenide.back();
        Selenide.back();
        ShoppingCartPage shoppingCartPage = new Header().clickOnShoppingCardIcon();
        List<String> res = shoppingCartPage.getListNamesItemsInShoppingCart();

        assertThat(res).contains(firstItemName, secondItemName);
    }

    @Test
    @DisplayName("Search for “Drucker”; \n" +
            "Select the first product from list and add to basket; \n" +
            "Go to shopping basket \n" +
            "2. Validate that user able to save the product for later purchase by clicking on “Save for  later”  button.\n" +
            "3. Validate that user can move the product back to basket by clicking on “Move to Basket” ")
    void addItemToTheShoppingCard_thenClickSaveForLater_thenClickMoveToCardButton() {

        new MainPage()
                .clickOnAcceptCookiesButton()
                .searchItem(itemToSearch)
                .clickOnElement(1)
                .clickAddToCartButton();

        ShoppingCartPage shoppingCartPage = new Header().clickOnShoppingCardIcon();
        shoppingCartPage
                .clickSaveForLater(1)
                .messageExist("wurde hierhin verschoben: Für einen späteren Zeitpunkt gespeichert.")
                .clickMoveToCardButton()
                .messageExist("wurde hierhin verschoben: Einkaufswagen.");
    }
}
