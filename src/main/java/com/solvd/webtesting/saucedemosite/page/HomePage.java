package com.solvd.webtesting.saucedemosite.page;

import com.solvd.webtesting.saucedemosite.components.BurgerMenu;
import com.solvd.webtesting.saucedemosite.components.ProductCard;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='bm-menu-wrap']")
    private BurgerMenu burgerMenu;

    private List<ProductCard> productCards;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public BurgerMenu getBurgerMenu() {
        return new BurgerMenu(
                getDriver(),
                getDriver().findElement(By.className("bm-menu-wrap"))
        );
    }

}
