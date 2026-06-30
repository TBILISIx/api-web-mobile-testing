package com.solvd.webtesting;

import com.solvd.webtesting.saucedemosite.page.*;
import com.solvd.webtesting.util.ChromeOptionsFactory;
import com.zebrunner.carina.core.AbstractTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SauceDemoTest extends AbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(SauceDemoTest.class);

    @Test(description = "This is test for successful login")
    public void successfulLoginTest() {

        WebDriver driver = getDriver("default", ChromeOptionsFactory.withoutAnnoyingPopups());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");

        homePage.waitUntilCardsPresent();

        Assert.assertTrue(homePage.isPageOpened());

    }

    @Test(description = "This is test for unsuccessful login")
    public void unSuccessfulLoginTest() {

        WebDriver driver = getDriver("default", ChromeOptionsFactory.withoutAnnoyingPopups());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("Greetings_Neo o_^ ", "secret_sauce");

        Assert.assertFalse(homePage.isPageOpened(2));

        LOGGER.error("In case of unsuccessful login attempt will fetch error message : {}", loginPage.getErrorText());

    }

    @Test(description = "This is test for logout")
    public void LogoutTest() {

        WebDriver driver = getDriver("default", ChromeOptionsFactory.withoutAnnoyingPopups());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        homePage.waitUntilCardsPresent();

        Assert.assertTrue(homePage.isPageOpened(), "Homepage did not open");

        homePage.openBurgerMenu();

        LoginPage logOutLogInPage = homePage.getBurgerMenu().clickLogout();

        Assert.assertTrue(logOutLogInPage.isPageOpened(), "Logout did not happen");

    }

    @Test(description = "This is test for full happy path")
    public void fullHappyPathTestWhereUserLogsInAddsItemsToCartSuccessfullyCheckoutAndLogsOut() {

        SoftAssert softAssert = new SoftAssert();

        WebDriver driver = getDriver("default", ChromeOptionsFactory.withoutAnnoyingPopups());

        // login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        homePage.waitUntilCardsPresent();
        Assert.assertTrue(homePage.isPageOpened(), "Homepage did not open");

        // all product cards on homepage (6)
        int expectedNumberOfProducts = homePage.getProductCards().size();

        int expectedNumberOfItemsInCart = expectedNumberOfProducts - 3;

        // adds 3 products to cart
        for (int i = 0; i < expectedNumberOfItemsInCart; i++) {
            homePage.getProductCards().get(i).clickAddToCart();
        }

        // soft assert to check if 3 products were added to cart
        softAssert.assertEquals(Integer.parseInt(homePage.getCartBadgeCountText()), expectedNumberOfItemsInCart,
                "Incorrect number of products in the cart. There should be " + expectedNumberOfItemsInCart
                        + " products in the cart");

        CartPage cartPage = homePage.openShoppingCart();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page did not open");

        CheckoutInfoPage checkoutInfoPage = cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutInfoPage.isPageOpened(), "Checkout Info page did not open");

        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout("WAKE UP NEO ", "THE MATRIX HAS YOU", "FOLLOW THE WHITE RABBIT");
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page did not open");

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        Assert.assertTrue(checkoutCompletePage.isPageOpened(), "Checkout Complete page did not open");

        HomePage homePageAfterCheckout = checkoutCompletePage.clickBackHomeButton();
        Assert.assertTrue(homePageAfterCheckout.isPageOpened(), "Homepage did not open");

        homePage.openBurgerMenu();

        LoginPage logOutLogInPage = homePage.getBurgerMenu().clickLogout();

        Assert.assertTrue(logOutLogInPage.isPageOpened(), "Logout did not happen");

        softAssert.assertAll();

        System.out.println(" Knock, knock, Neo. ");

    }

    @Test(description = "This is test for after login sort by price get cheapest and most expensive products verify with cart go back to home page reset app logout")
    public void testAfterLoginSortByPriceGetCheapestAndMostExpensiveProductsVerifyWithCartGoBackToHomePageResetAppLogout() {

        WebDriver driver = getDriver("default", ChromeOptionsFactory.withoutAnnoyingPopups());

        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");

        homePage.waitUntilCardsPresent();

        Assert.assertTrue(homePage.isPageOpened(), "Homepage did not open");

        // sort from low to high add lowest price element to cart from index 0 and add most expensive on last index
        // store values inside List priceList to later verify it against to cartList Prices

        // 1.

        homePage.sortBy(SortOption.PRICE_LOW_TO_HIGH);

        List<String> priceListOfAllItems = new ArrayList<>();

        for (int i = 0; i < homePage.getProductCards().size(); i++) {
            priceListOfAllItems.add(homePage.getProductCards().get(i).getProductPrice().getText());

        }

        // 2.

        homePage.getProductCards().get(0).clickAddToCart();
        homePage.getProductCards().get(homePage.getProductCards().size() - 1).clickAddToCart();

        String homePageCheapest = priceListOfAllItems.get(0);
        String homePageMostExpensive = priceListOfAllItems.get(priceListOfAllItems.size() - 1);

        // 3.

        CartPage cartPage = homePage.openShoppingCart();

        List<String> cartPriceList = new ArrayList<>();

        for (int i = 0; i < cartPage.getCartItems().size(); i++) {
            cartPriceList.add(cartPage.getCartItems().get(i).getItemPriceText());
        }

        // 4.

        softAssert.assertTrue(cartPriceList.contains(homePageCheapest), "Prices of Cheapest product are not equal");
        softAssert.assertTrue(cartPriceList.contains(homePageMostExpensive), "Prices of Most Expensive product are not equal");

        // 5.

        HomePage continueShoppingHomePage = cartPage.clickContinueShoppingButton();

        Assert.assertTrue(continueShoppingHomePage.isPageOpened(), "Homepage did not open");

        continueShoppingHomePage.openBurgerMenu();

        continueShoppingHomePage.getBurgerMenu().clickResetApp();

        softAssert.assertEquals(continueShoppingHomePage.getCartBadgeCountText(), "", "Cart badge should be empty after resetting the app");

        LoginPage logOutLogInPage = continueShoppingHomePage.getBurgerMenu().clickLogout();
        Assert.assertTrue(logOutLogInPage.isPageOpened(), "Logout did not happen");

        softAssert.assertAll();

    }

}
