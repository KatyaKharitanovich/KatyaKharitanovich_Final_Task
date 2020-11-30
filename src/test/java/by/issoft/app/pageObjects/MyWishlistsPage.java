package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyWishlistsPage extends BasePage {

    private static final By WISHLISTS_TABLE = By.xpath("//*[@id='block-history']/table");
    private static final By WISHLISTS_NAME_INPUT = By.id("name");
    private static final By WISHLISTS_SAVE_BUTTON = By.id("submitWishlist");
    private static final String WISHLIST_NAME_CELL = "//a[contains(text(), '%s')]/parent::td";
    private static final By WISHLIST_ITEMS_COUNT = By.xpath("./following-sibling::td[1]");
    private static final By WISHLIST_VIEW_BUTTON = By.xpath("./following-sibling::td[4]/a");
    private static final By WISHLIST_DELETE_BUTTON = By.xpath("//a[@class='icon']");
    private static final String WISHLIST_ITEM_NAME = "//div[@class='wlp_bought']//p[contains(text(), '%s')]";


    private WebDriver driver;

    public MyWishlistsPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }

    private WebElement getWishlistNameCell(String wishlistName) {
        return driver.findElement(By.xpath(format(WISHLIST_NAME_CELL, wishlistName)));
    }

    public MyWishlistsPage viewWishlistWithName(String wishlistName){
        getWishlistNameCell(wishlistName).findElement(WISHLIST_VIEW_BUTTON).click();
        return new MyWishlistsPage();
    }

    public WebElement getWishlistItemsCount(String wishlistName) {
        return getWishlistNameCell(wishlistName).findElement(WISHLIST_ITEMS_COUNT);
    }

    public boolean getWishlistAvailabilityByName(String wishlistName) {
        return isElementPresent(By.xpath(format(WISHLIST_NAME_CELL, wishlistName)));
    }

    public boolean isWishlistsTableUnavailable() {
        return waitForElementToDisappear(WISHLISTS_TABLE);
    }

    public boolean isItemPresentInWishlist(String itemName) {
        return isElementPresent(By.xpath(format(WISHLIST_ITEM_NAME, itemName)));
    }

    public void addNewWishlist(String wishlistName) {
        driver.findElement(WISHLISTS_NAME_INPUT).sendKeys(wishlistName);
        driver.findElement(WISHLISTS_SAVE_BUTTON).click();
    }

    public MyWishlistsPage deleteAllWishlists() {
        List<WebElement> deleteButtons = driver.findElements(WISHLIST_DELETE_BUTTON);
        Alert alert;
        if (deleteButtons.size() > 0) {
            for (WebElement button : deleteButtons) {
                button.click();
                alert = driver.switchTo().alert();
                alert.accept();
            }
        }
        return new MyWishlistsPage();
    }
}
