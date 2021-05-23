package de.amazon.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;


public class SearchResultPage {

    private final ElementsCollection searchField = $$x("//div[@class='s-include-content-margin s-border-bottom s-latency-cf-section']");
    private final String byItemName =".//span[@class='a-size-medium a-color-base a-text-normal']";

    @Step("Get text from element with number {elementNumber} in Search Result list")
    public String getTextElement(Integer elementNumber){
        return searchField.get(elementNumber - 1).$x(byItemName).getText();
    }

    @Step("Click on element with number {elementNumber} in Search Result list")
    public ItemPage clickOnElement(Integer elementNumber){
        searchField.get(elementNumber - 1).$x(byItemName).click();
        return new ItemPage();
    }

}
