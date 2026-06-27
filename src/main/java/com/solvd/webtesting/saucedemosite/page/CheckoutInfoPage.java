package com.solvd.webtesting.saucedemosite.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends AbstractPage {

    private ExtendedWebElement firstNameField;

    private ExtendedWebElement lastNameField;

    private ExtendedWebElement zipCodeField;

    private ExtendedWebElement continueButton;

    private ExtendedWebElement cancelButton;

    private ExtendedWebElement errorMessage;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
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
