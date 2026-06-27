package com.solvd.webtesting.saucedemosite.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@class='cart_list']//div[@class='cart_quantity']")
    private ExtendedWebElement itemQuantity;

    @FindBy(xpath = ".//div[@class='cart_list']//a")
    private ExtendedWebElement itemNameLink; // not using right now

    @FindBy(xpath = ".//div[@class='cart_list']//div[@class='inventory_item_price']")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = ".//div[@class='cart_list']//button[@class='btn btn_secondary btn_small cart_button']")
    private ExtendedWebElement removeButton;

    public String getItemQuantityText() {
        return itemQuantity.getText();
    }

    public String getItemNameLinkText() {
        return itemNameLink.getText();
    }

    public String getItemPriceText() {
        return itemPrice.getText();
    }

    public void clickRemoveButton() {
        removeButton.click();
    }

    public CartItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

}
