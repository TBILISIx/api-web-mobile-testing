package com.solvd.webtesting;

import com.solvd.webtesting.saucedemosite.page.*;
import com.zebrunner.carina.core.AbstractTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SauceDemoTest extends AbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(SauceDemoTest.class);

    @Test(description = "This is test for successful login")
    public void successfulLoginTest() {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");

        homePage.waitUntilCardsPresent();

        Assert.assertTrue(homePage.isPageOpened());

    }

    @Test(description = "This is test for unsuccessful login")
    public void unSuccessfulLoginTest() {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("Greetings_Neo o_^ ", "secret_sauce");

        Assert.assertFalse(homePage.isPageOpened(2));

        LOGGER.error("In case of unsuccessful login attempt will fetch error message : {}", loginPage.getErrorText());

    }


    @Test(description = "This is test for logout")
    public void LogoutTest() {

        SoftAssert softAssert = new SoftAssert();

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        homePage.waitUntilCardsPresent();

        softAssert.assertTrue(homePage.isPageOpened(),"Homepage did not open");

        homePage.openBurgerMenu();

        LoginPage logOutLogInPage = homePage.getBurgerMenu().clickLogout();

        softAssert.assertTrue(logOutLogInPage.isPageOpened(),"Logout did not happen");

        softAssert.assertAll();

    }


    @Test
    public void fullHappyPathTestWhereUserLogsInAddsItemsToCartSuccessfullyCheckoutAndLogsOut() {

        SoftAssert softAssert = new SoftAssert();

        WebDriver driver = getDriver();

        // login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        homePage.waitUntilCardsPresent();
        softAssert.assertTrue(homePage.isPageOpened(),"Homepage did not open");


        // all product cards on homepage (6)
        int expectedNumberOfProducts = homePage.getProductCards().size();

        int expectedNumberOfItemsInCart = expectedNumberOfProducts-3;

        // adds 3 products to cart
        for (int i = 0; i <expectedNumberOfItemsInCart ; i++) {
            homePage.getProductCards().get(i).clickAddToCart();
        }

        // soft assert to check if 3 products were added to cart
        softAssert.assertEquals(Integer.parseInt(homePage.getCartBadgeCountText()), expectedNumberOfItemsInCart,
                "Incorrect number of products in the cart. There should be " + expectedNumberOfItemsInCart
                        + " products in the cart");




        CartPage cartPage = homePage.openShoppingCart();
        softAssert.assertTrue(cartPage.isPageOpened(),"Cart page did not open");



        CheckoutInfoPage checkoutInfoPage = cartPage.clickCheckoutButton();
        softAssert.assertTrue(checkoutInfoPage.isPageOpened(),"Checkout Info page did not open");


        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout("WAKE UP NEO", "THE MATRIX HAVE YOU", "FOLLOW THE WHITE RABBIT");
        softAssert.assertTrue(checkoutOverviewPage.isPageOpened(),"Checkout Overview page did not open");

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        softAssert.assertTrue(checkoutCompletePage.isPageOpened(),"Checkout Complete page did not open");

        HomePage homePageAfterCheckout = checkoutCompletePage.clickBackHomeButton();
        softAssert.assertTrue(homePageAfterCheckout.isPageOpened(),"Homepage did not open");





        System.out.println("Hello Neo :) ");


    }





}
