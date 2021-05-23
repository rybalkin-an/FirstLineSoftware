package de.amazon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ItemPage {
    private final SelenideElement addToCartButton = $("#add-to-cart-button");

    @Step("Click 'Add To Cart' Button")
    public void clickAddToCartButton(){
        addToCartButton.click();
    }
}
