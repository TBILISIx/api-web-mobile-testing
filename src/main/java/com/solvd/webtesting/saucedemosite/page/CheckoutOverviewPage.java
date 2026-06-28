package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.CartItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutOverviewPage extends AbstractPage {

    private List<CartItem> cartItems;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_subtotal_label']")
    private ExtendedWebElement itemTotalLabel;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_tax_label']")
    private ExtendedWebElement taxLabel;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_total_label']")
    private ExtendedWebElement totalLabel;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='cart_footer']/button[contains(@data-test, 'finish')]")
    private ExtendedWebElement finishButton;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='cart_footer']/button[contains(@data-test, 'cancel')]")
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
