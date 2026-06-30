package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.BurgerMenu;
import com.solvd.webtesting.saucedemosite.components.ProductCard;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private ExtendedWebElement burgerMenuButton;

    @FindBy(xpath = "//div[@class='bm-menu-wrap']")
    private BurgerMenu burgerMenu;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private ExtendedWebElement cartBadge;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private ExtendedWebElement cartIconLink;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<ProductCard> productCards;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private ExtendedWebElement sortProductCardsDropdown;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_homepage_url"));
    }

    // burger menu button + click
    public ExtendedWebElement getBurgerMenuButton() {
        return burgerMenuButton;
    }

    public void openBurgerMenu() {
        burgerMenuButton.click();
    }

    // burger menu with its options
    public BurgerMenu getBurgerMenu() {
        return burgerMenu;
    }

    // product cards
    public List<ProductCard> getProductCards() {
        return productCards;
    }

    // shopping cart + click
    public ExtendedWebElement getCartIconLink() {
        return cartIconLink;
    }

    public CartPage openShoppingCart() {
        cartIconLink.click();
        return new CartPage(getDriver());
    }

    public String getCartBadgeCountText() {
        return cartBadge.getText();
    }

    public boolean isCartBadgeDisplayed() {
        return cartBadge.isElementPresent(2);
    }

    public void clickSortProductCards() {
        sortProductCardsDropdown.click();
    }

    public void sortBy(SortOption option) {
        sortProductCardsDropdown.select(option.getLabel());
    }

    public void waitUntilCardsPresent() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(200))
                .until(d -> productCards.size() == 6);
    }

}
