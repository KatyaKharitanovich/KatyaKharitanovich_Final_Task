package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;

    public ProductPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }
}
