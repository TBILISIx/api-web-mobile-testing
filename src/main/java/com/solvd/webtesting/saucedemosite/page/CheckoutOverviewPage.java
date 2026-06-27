package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.CartItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutOverviewPage extends AbstractPage {

    private List<CartItem> cartItems;
    private ExtendedWebElement itemTotalLabel;
    private ExtendedWebElement taxLabel;
    private ExtendedWebElement totalLabel;
    private ExtendedWebElement finishButton;
    private ExtendedWebElement cancelButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }



    public String getItemTotalText() {
        return itemTotalLabel.getText();
    }

    public String getTaxText() {
        return taxLabel.getText();
    }

    public String getTotalText() {
        return totalLabel.getText();
    }

    public CheckoutCompletePage clickFinishButton() {

        finishButton.click();
        return new CheckoutCompletePage(getDriver());

    }

    public HomePage clickCancelButton() {
        cancelButton.click();
        return new HomePage(getDriver());
    }

}
