package de.amazon.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartPage {
    private ElementsCollection itemNameList = $$x("//div[@class='sc-list-item-content']//li[1]");
    private ElementsCollection itemList = $$x("//div[@class='sc-list-item-content']");
    private SelenideElement moveToCardButton = $x("//input[@value='In den Einkaufswagen' and @class='a-color-link']");

    @Step("Get list names of items in the Shopping Cart")
    public List<String> getListNamesItemsInShoppingCart(){
        return itemNameList.shouldHave(CollectionCondition.size(2), 5000).texts();
    }

    @Step("Click on 'Save for later' button for item with number {itemNumber} in list")
    public ShoppingCartPage clickSaveForLater(Integer itemNumber){
        itemList.get(itemNumber - 1)
                .$x(".//input[@data-action='save-for-later']")
                .click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Check that {message} exist")
    public ShoppingCartPage messageExist(String message){
        $(byText(message)).waitUntil(Condition.exist, 6999);
        return this;
    }

    @Step("Click on 'Move to card' Button")
    public ShoppingCartPage clickMoveToCardButton() {
        moveToCardButton.click(ClickOptions.usingJavaScript());
        return this;
    }

}
