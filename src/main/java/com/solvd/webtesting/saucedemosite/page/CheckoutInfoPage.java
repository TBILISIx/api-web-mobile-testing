package com.solvd.webtesting.saucedemosite.page;

import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='form_group']//input[@id='first-name']")
    private ExtendedWebElement firstNameField;

    @FindBy(xpath = "//div[@class='checkout_info']//input[contains(@data-test, 'lastName')]")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//div[@class='checkout_info']//input[contains(@data-test, 'postalCode')]")
    private ExtendedWebElement zipCodeField;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private ExtendedWebElement errorMessage;

    @FindBy(xpath = "//div[@class='checkout_buttons']/button[contains(@data-test,'cancel')]")
    private ExtendedWebElement cancelButton;

    @FindBy(xpath = "//div[@class='checkout_buttons']/input[contains(@data-test,'continue')]")
    private ExtendedWebElement continueButton;


    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_checkout_step_one_url"));
    }

    public CheckoutOverviewPage continueCheckout(String firstName, String lastName, String zip) {

        // Carina framework style .type == this.firstNameField.type(firstName) wow )

        firstNameField.type(firstName);
        lastNameField.type(lastName);
        zipCodeField.type(zip);
        continueButton.click();

        return new CheckoutOverviewPage(getDriver());

    }

    public CheckoutInfoPage continueCheckoutExpectingFailure(String firstName, String lastName, String zip) {

        // same method but incase of failure, stay on this page

        firstNameField.type(firstName);
        lastNameField.type(lastName);
        zipCodeField.type(zip);
        continueButton.click();

        return this;
    }

    public String getErrorText() {
        return errorMessage.getText();
    }

    // after clicking cancel button will redirect to cart page
    public CartPage clickCancelButton() {
        cancelButton.click();
        return new CartPage(getDriver());
    }

}
