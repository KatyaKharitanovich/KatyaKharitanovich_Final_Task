package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

public class TShirtsPage extends BasePage {

    private static final String PRODUCT_CONTAINER = "//h5[@itemprop='name']/a[@title='%s']/ancestor::div[@class='product-container']";
    private static final By ADD_TO_WISHLISTS_BUTTON = By.className("wishlist");
    private static final By ITEM_ADDED_MESSAGE = By.className("fancybox-outer");
    private static final By CLOSE_MESSAGE_BUTTON = By.className("fancybox-close");
    private static final By LIST_VIEW_BUTTON = By.id("list");
    private static final By TSHIRTS_TITLE = By.xpath("//span[contains(text(), 'T-shirts')]");

    private WebDriver driver;

    public TShirtsPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }

    private WebElement getProductContainerByName(String itemName) {
        return findByWithWait(By.xpath(format(PRODUCT_CONTAINER, itemName)));
           }

    public TShirtsPage addItemToWishlist(String itemName) {
        getProductContainerByName(itemName).findElement(ADD_TO_WISHLISTS_BUTTON).click();
        closeAddedToastMessage();
        return new TShirtsPage();
    }

    private TShirtsPage closeAddedToastMessage() {
        findByWithWait(CLOSE_MESSAGE_BUTTON).click();
        return new TShirtsPage();
    }

    public TShirtsPage setListView() {
        WebElement listViewButton = driver.findElement(LIST_VIEW_BUTTON);
        if (!listViewButton.isSelected()) {
            listViewButton.click();
        }
        return new TShirtsPage();
    }
}
