package by.issoft.app.pageObjects;

import by.issoft.app.utils.PropertyValues;
import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPage extends BasePage {

    private static final By CREATE_ACCOUNT_EMAIL_INPUT = By.id("email_create");
    private static final By CREATE_ACCOUNT_BUTTON = By.id("SubmitCreate");
    private static final By EMAIL_LOGIN = By.id("email");
    private static final By PASSWORD_LOGIN = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON = By.id("SubmitLogin");
    private static final String URL = PropertyValues.get("url");

    WebDriver driver;

    public AuthenticationPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
    }

    public AuthenticationPage load() {
        this.driver.get(URL);
        return new AuthenticationPage();
    }

    public CreateAccountPage selectToCreateAccount(String email) {
        driver.findElement(CREATE_ACCOUNT_EMAIL_INPUT).sendKeys(email);
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
        return new CreateAccountPage();
    }

    public MyAccountPage selectToLogin(String email, String password) {
        driver.findElement(EMAIL_LOGIN).sendKeys(email);
        driver.findElement(PASSWORD_LOGIN).sendKeys(password);
        driver.findElement(SUBMIT_LOGIN_BUTTON).click();
        return new MyAccountPage();
    }
}
