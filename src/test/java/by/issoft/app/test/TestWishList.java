package by.issoft.app.test;


import by.issoft.app.extentions.MyExtension;
import by.issoft.app.utils.PropertyValues;
import by.issoft.app.driver.WebDriverSingleton;
import by.issoft.app.pageObjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyExtension.class)
public class TestWishList {

    AuthenticationPage authenticationPage;
    MyAccountPage myAccountPage;
    MyWishlistsPage myWishlistsPage;
    TShirtsPage tShirtsPage;

    @BeforeAll
    public void openBrowser() {
        authenticationPage = new AuthenticationPage();
        authenticationPage.load();
    }


    @BeforeEach
    public void runPreconditions() {
        myAccountPage = authenticationPage.selectToLogin(PropertyValues.get("test_login"), PropertyValues.get("test_password"));
        myWishlistsPage = myAccountPage.navigateToMyWishlists();
        myWishlistsPage.deleteAllWishlists();
    }

    @AfterEach
    public void tearDown() {
        Set<String> windowHandles = WebDriverSingleton.getInstance().getWebDriver().getWindowHandles();
        if (windowHandles.size() > 1) {
            WebDriverSingleton.getInstance().getWebDriver().close();
            WebDriverSingleton.getInstance().getWebDriver().switchTo().window(windowHandles.iterator().next());
        }
        WebDriverSingleton.getInstance().getWebDriver().manage().deleteAllCookies();
        WebDriverSingleton.getInstance().getWebDriver().navigate().refresh();
    }

    @AfterAll
    public static void quitBrowser() {
        WebDriverSingleton.getInstance().closeWebDriver();
    }

    @Feature("WhishList")
    @Description("Verify the ability to add to auto-created Wishlist")
    @Issue("TEST_3")
    @ParameterizedTest
    @CsvFileSource(resources = "/test3Data.csv", numLinesToSkip = 1)
    public void addToAutoWishList(String itemName, String wishlistName) {
        assertTrue(myWishlistsPage.isWishlistsTableUnavailable());
        HeaderPageComponent headerPageComponent = new HeaderPageComponent();
        tShirtsPage = headerPageComponent.navigateToTShitrs();
        tShirtsPage.setListView();
        tShirtsPage.addItemToWishlist(itemName);
        myAccountPage = headerPageComponent.navigateToAccount();
        myWishlistsPage = myAccountPage.navigateToMyWishlists();
        assertTrue(myWishlistsPage.getWishlistAvailabilityByName(wishlistName));
    }

    @Feature("WhishList")
    @Description("Verify the ability to add to your Wishlist")
    @Issue("TEST_4")
    @ParameterizedTest
    @CsvFileSource(resources = "/test4Data.csv", numLinesToSkip = 1)
    public void addToUserWishlist(String itemName, String wishlistName) {
        assertTrue(myWishlistsPage.isWishlistsTableUnavailable());
        myWishlistsPage.addNewWishlist(wishlistName);
        assertTrue(myWishlistsPage.getWishlistAvailabilityByName(wishlistName));
        HeaderPageComponent headerPageComponent = new HeaderPageComponent();
        tShirtsPage = headerPageComponent.navigateToTShitrs();
        tShirtsPage.setListView();
        tShirtsPage.addItemToWishlist(itemName);
        myAccountPage = headerPageComponent.navigateToAccount();
        myWishlistsPage = myAccountPage.navigateToMyWishlists();
        assertEquals("1", myWishlistsPage.getWishlistItemsCount(wishlistName).getText());
        myWishlistsPage.viewWishlistWithName(wishlistName);
        assertTrue( myWishlistsPage.isItemPresentInWishlist(itemName));
    }
}
