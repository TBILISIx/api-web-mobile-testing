package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.BurgerMenu;
import com.solvd.webtesting.saucedemosite.components.ProductCard;
import com.solvd.webtesting.saucedemosite.components.ShoppingCart;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='bm-menu-wrap']")
    private BurgerMenu burgerMenu;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private ShoppingCart shoppingCart;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<ProductCard> productCards;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public BurgerMenu getBurgerMenu() {
        return burgerMenu;
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public void waitUntilCardsPresent() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(200))
                .until(d -> !productCards.isEmpty());
    }

}
