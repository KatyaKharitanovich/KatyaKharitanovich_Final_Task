package by.issoft.app.pageObjects;

import by.issoft.app.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class CreateAccountPage extends BasePage {
    private static final By MALE_GENDER_RADIO = By.id("id_gender1");
    private static final By FEMALE_GENDER_RADIO = By.id("id_gender2");
    private static final By FIRST_NAME_FIELD = By.id("customer_firstname");
    private static final By LAST_NAME_FIELD = By.id("customer_lastname");
    private static final By EMAIL_FIELD = By.id("email");
    private static final By PASSWORD_FIELD = By.id("passwd");
    private static final By DAY_SELECT = By.id("days");
    private static final By MONTH_SELECT = By.id("months");
    private static final By YEAR_SELECT = By.id("years");
    private static final By ADDRESS_FIRST_NAME = By.name("firstname");
    private static final By ADDRESS_LAST_NAME = By.name("lastname");
    private static final By ADDRESS1_INPUT = By.id("address1");
    private static final By CITY_INPUT = By.id("city");
    private static final By STATE_SELECT = By.xpath("//*[@id='id_state']");
    private static final By ZIP_INPUT = By.id("postcode");
    private static final By COUNTRY_SELECT = By.id("id_country");
    private static final By MOBILE_INPUT = By.id("phone_mobile");
    private static final By ALIAS_INPUT = By.id("alias");
    private static final By REGISTER_BUTTON = By.id("submitAccount");

    private WebDriver driver;

    public CreateAccountPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_NAME_FIELD));
    }

    public MyAccountPage fillSignUpForm(String firstName, String lastName, String email,
                                        String password, String address, String city, String state,
                                        String zip, String country, String mobile, String alias) {
        typeFirstName(firstName);
        typeLaststName(lastName);
        typeEmail(email);
        typePassword(password);
        typeAddress1(address);
        typeCity(city);
        selectState(state);
        typeZip(zip);
        selectCountry(country);
        typeMobile(mobile);
        typeAddressAlias(alias);
        submitSignUp();
        return new MyAccountPage();
    }

    private void typeFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
    }

    private void typeLaststName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
    }

    private void typeEmail(String email) {
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    private void typePassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    private void typeAddress1(String address) {
        driver.findElement(ADDRESS1_INPUT).sendKeys(address);
    }

    private void typeCity(String city) {
        driver.findElement(CITY_INPUT).sendKeys(city);
    }

    private void selectState(String state) {
        Select stateSelect = new Select(driver.findElement(STATE_SELECT));
        stateSelect.selectByVisibleText(state);
    }

    private void typeZip(String zip) {
        driver.findElement(ZIP_INPUT).sendKeys(zip);
    }

    private void selectCountry(String country) {
        Select yearSelect = new Select(driver.findElement(COUNTRY_SELECT));
        yearSelect.selectByVisibleText(country);
    }

    private void typeMobile(String mobile) {
        driver.findElement(MOBILE_INPUT).sendKeys(mobile);
    }

    private void typeAddressAlias(String alias) {
        driver.findElement(ALIAS_INPUT).clear();
        driver.findElement(ALIAS_INPUT).sendKeys(alias);
    }

    private void submitSignUp() {
        driver.findElement(REGISTER_BUTTON).click();
    }
}
