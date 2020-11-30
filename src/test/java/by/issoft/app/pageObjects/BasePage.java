package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    protected Actions actions;

    public BasePage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    public WebElement findByWithWait(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public boolean isElementPresent(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return !driver.findElements(locator).isEmpty();
    }

    public boolean waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return driver.findElements(locator).isEmpty();
    }
}
