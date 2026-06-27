package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.CartItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    private List<CartItem> cartItems;

    @FindBy(xpath = "//div[@class='cart_footer']//button[contains(@data-test, 'continue-shopping')]")
    private ExtendedWebElement checkoutButton;

    @FindBy(xpath = ".//button[@class='btn btn_secondary btn_small cart_button']")
    private ExtendedWebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // map cartItems -> getItemNameText()
    public List<String> getCartItemNames() {
        return cartItems.stream().map(CartItem::getItemNameLinkText).toList();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    // loop cartItems, click remove on match
    public void removeItemByName(String name) {

        for (CartItem item : cartItems) {
            if (item.getItemNameLinkText().equals(name)) {
                item.clickRemoveButton();
            }
        }

    }

    // after clicking checkout button, go to checkout info page
    public CheckoutInfoPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutInfoPage(getDriver());

    }

    // after clicking continue shopping button, go to home page
    public HomePage clickContinueShoppingButton() {
        continueShoppingButton.click();
        return new HomePage(getDriver());
    }

}
