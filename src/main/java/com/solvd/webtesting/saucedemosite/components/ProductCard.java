package com.solvd.webtesting.saucedemosite.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class ProductCard extends AbstractUIObject {

    private String productName;
    private String productPrice;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver , searchContext);
    }

}
