package com.solvd.webtesting.saucedemosite.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    private ExtendedWebElement usernameField;
    private ExtendedWebElement passwordField;
    private ExtendedWebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage typeUsername(String username) {
        this.usernameField.type(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        this.passwordField.type(password);
        return this;
    }

    public HomePage clickLoginButton() {
        this.loginButton.click();
        return new HomePage(getDriver());
    }

    public HomePage login (String username, String password) {
        typeUsername(username);
        typePassword(password);
        return clickLoginButton();
    }
}
