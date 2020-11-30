package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

    private static final By MY_ACCOUNT_TEXT = By.xpath("//*[@id='center_column']/p");
    private static final By MY_WISHLIST_LINK = By.xpath("//a[@title='My wishlists']");

    private WebDriver driver;

    public MyAccountPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }

    public MyWishlistsPage navigateToMyWishlists(){
        driver.findElement(MY_WISHLIST_LINK).click();
        return new MyWishlistsPage();
    }
}
