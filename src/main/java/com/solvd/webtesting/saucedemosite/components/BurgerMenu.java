package com.solvd.webtesting.saucedemosite.components;

import com.solvd.webtesting.saucedemosite.page.LoginPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BurgerMenu extends AbstractUIObject {

    @FindBy(xpath = ".//a[@id='inventory_sidebar_link']")
    private ExtendedWebElement allItems;

    @FindBy(xpath = ".//a[@id='about_sidebar_link']")
    private ExtendedWebElement about;

    @FindBy(xpath = ".//a[@id='logout_sidebar_link']")
    private ExtendedWebElement logout;

    @FindBy(xpath = ".//a[@id='reset_sidebar_link']")
    private ExtendedWebElement resetApp;

    @FindBy(xpath = ".//button[@id='react-burger-cross-btn']")
    private ExtendedWebElement burgerMenuClose;

    public BurgerMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickAllItems() {
        allItems.click();
    }

    public void clickAbout() {
        about.click();
    }

    public LoginPage clickLogout() {
        logout.click();
        return new LoginPage(getDriver());
    }

    public void clickResetApp() {
        resetApp.assertElementPresent();
        resetApp.click();
    }

    public void clickBurgerMenuClose() {
        burgerMenuClose.click();
    }

}
