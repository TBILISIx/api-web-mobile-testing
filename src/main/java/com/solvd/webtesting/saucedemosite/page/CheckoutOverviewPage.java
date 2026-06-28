package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.CartItem;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutOverviewPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='cart_item']")
    private List<CartItem> cartItems;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_subtotal_label']")
    private ExtendedWebElement itemTotalLabel;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_tax_label']")
    private ExtendedWebElement taxLabel;

    @FindBy(xpath = "//div[@class='summary_info']/div[@class='summary_total_label']")
    private ExtendedWebElement totalLabel;

    @FindBy(xpath = "//div[@class='cart_footer']/button[@data-test='finish']")
    private ExtendedWebElement finishButton;

    @FindBy(xpath = "//div[@class='cart_footer']/button[@data-test='cancel']")
    private ExtendedWebElement cancelButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_checkout_step_two_url"));
    }


    public List<String> getOverviewItemNames() {
        return cartItems.stream().map(CartItem::getItemNameLinkText).toList();
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
