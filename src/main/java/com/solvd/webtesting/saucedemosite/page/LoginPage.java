package com.solvd.webtesting.saucedemosite.page;

import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='user-name']")
    private ExtendedWebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//div[@class='error-message-container error']//h3")
    private ExtendedWebElement errorText;

    @FindBy(xpath = "//input[@id='login-button']")
    private ExtendedWebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(Configuration.getRequired("saucedemo_url"));
    }

    public LoginPage typeUsername(String username) {
        this.usernameField.type(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        this.passwordField.type(password);
        return this;
    }

    public String getErrorText() {
        return errorText.getText();
    }

    public HomePage clickLoginButton() {
        this.loginButton.click();
        return new HomePage(getDriver());
    }

    // for the positive path — login expected to succeed
    public HomePage login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        loginButton.click();
        return new HomePage(getDriver());
    }

    // for negative tests — login expected to fail, stay on this page
    public LoginPage loginExpectingFailure(String username, String password) {
        typeUsername(username);
        typePassword(password);
        loginButton.click();
        return this;
    }

}
