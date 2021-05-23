package de.amazon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement searchField = $("#twotabsearchtextbox");
    private final SelenideElement searchSubmitButton = $("#nav-search-submit-button");
    private final SelenideElement acceptCookiesButton = $("#sp-cc-accept");

    @Step("Click on 'Accept Cookies' button")
    public MainPage clickOnAcceptCookiesButton(){
        acceptCookiesButton.click();
        return this;
    }

    @Step("Search item with name {itemName}")
    public SearchResultPage searchItem(String itemName){
        searchField.sendKeys(itemName);
        searchSubmitButton.click();
        return new SearchResultPage();
    }
}
