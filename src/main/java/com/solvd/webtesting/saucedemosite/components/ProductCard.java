package com.solvd.webtesting.saucedemosite.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='inventory_item_img']//img")
    private ExtendedWebElement productImage;

    @FindBy(xpath = "//div[@class='inventory_item_img']//a")
    private ExtendedWebElement productImageLink;

    @FindBy(xpath = "//div[@class='inventory_item_label']//a")
    private ExtendedWebElement productNameLink;

    @FindBy(xpath = "//div[@class='inventory_item_label']//div[contains(@data-test, 'inventory-item-desc')]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//div[contains(@data-test,'inventory-item-price')]")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//button[contains(@data-test,'add-to-cart')]")
    private ExtendedWebElement addToCartButton;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    // Product Image and Link with click

    public ExtendedWebElement getProductImage() {
        return productImage;
    }

    public ExtendedWebElement getProductImageLink() {
        return productImageLink;
    }

    public void clickProductImageLink() {
        productImageLink.click();
    }

    // Product Name + in String and Link with click

    public ExtendedWebElement getProductNameLink() {
        return productNameLink;
    }

    public String getProductNameText() {
        return productNameLink.getText();
    }

    public void clickProductNameLink() {
        productNameLink.click();
    }

    // Product Description

    public ExtendedWebElement getProductDescription() {
        return productDescription;
    }

    // Product Price and in String

    public ExtendedWebElement getProductPrice() {
        return productPrice;
    }

    public String getProductPriceText() {
        return productPrice.getText();
    }

    // Add to Cart Button

    public ExtendedWebElement getAddToCartButton() {
        return addToCartButton;
    }

}
