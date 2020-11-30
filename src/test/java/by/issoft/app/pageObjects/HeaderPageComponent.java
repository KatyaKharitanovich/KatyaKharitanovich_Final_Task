package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPageComponent extends BasePage {
    private static final By LOGO = By.id("header_logo");
    private static final By SIGN_IN_BUTTON = By.cssSelector(".login");
    private static final By SIGN_OUT_BUTTON = By.cssSelector(".logout");
    private static final By ACCOUNT_BUTTON = By.cssSelector(".account");
    private static final By TSHIRTS_TAB = By.xpath("//*[@id='block_top_menu']/ul/li[3]/a");

    private WebDriver driver;

    public HeaderPageComponent() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }

    public boolean getLoggedInResult(String firstName, String lastName) {
        return driver.findElement(ACCOUNT_BUTTON).getText().equals(firstName + " " + lastName)
                && driver.findElement(SIGN_OUT_BUTTON).isDisplayed();
    }

    public TShirtsPage navigateToTShitrs() {
        driver.findElement(TSHIRTS_TAB).click();
        return new TShirtsPage();
    }

    public MyAccountPage navigateToAccount() {
        driver.findElement(ACCOUNT_BUTTON).click();
        return new MyAccountPage();
    }
}
