package com.solvd.webtesting.saucedemosite.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AbstractPage {

    @FindBy(xpath = "//div[@id='header_container']//span[@data-test='title']")
    private ExtendedWebElement completeHeader;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private ExtendedWebElement backToHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }

    // returns back to home page after click
    public HomePage clickBackHomeButton() {
        backToHomeButton.click();
        return new HomePage(getDriver());
    }

}
