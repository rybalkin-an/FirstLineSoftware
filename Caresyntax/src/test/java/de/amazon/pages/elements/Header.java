package de.amazon.pages.elements;

import com.codeborne.selenide.SelenideElement;
import de.amazon.pages.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    private SelenideElement shoppingCardIcon = $("#nav-cart-count-container");

    public ShoppingCartPage clickOnShoppingCardIcon(){
        shoppingCardIcon.click();
        return new ShoppingCartPage();
    }
}
