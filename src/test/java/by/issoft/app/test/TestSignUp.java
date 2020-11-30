package by.issoft.app.test;

import by.issoft.app.extentions.MyExtension;
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

import static by.issoft.app.utils.GenericUtils.getRandomEmail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyExtension.class)
public class TestSignUp {

    AuthenticationPage authenticationPage;
    CreateAccountPage createAccountPage;
    MyAccountPage myAccountPage;

    @BeforeAll
    public void openBrowser() {
        authenticationPage = new AuthenticationPage();
        authenticationPage.load();
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

    @Feature("Authorization")
    @Description("Verifies if user can Create Account")
    @Issue("TEST_1")
    @ParameterizedTest
    @CsvFileSource(resources = "/test1Data.csv", numLinesToSkip = 1)
    public void createAccount(String firstName, String lastName, String email,
                      String password, String address, String city, String state,
                      String zip, String country, String mobile, String alias) {
        String genericEmail = getRandomEmail(email);
        createAccountPage = authenticationPage.selectToCreateAccount(genericEmail);
        myAccountPage = createAccountPage.fillSignUpForm(firstName, lastName, genericEmail, password, address, city, state, zip, country, mobile, alias);
        HeaderPageComponent headerPageComponent = new HeaderPageComponent();
        assertTrue(headerPageComponent.getLoggedInResult(firstName, lastName), "New created account name does not match expected or User is not logged in");
    }

    @Feature("Authorization")
    @Description("Verifies if user can log in")
    @Issue("TEST_2")
    @ParameterizedTest
    @CsvFileSource(resources = "/test2Data.csv", numLinesToSkip = 1)
    public void logIn(String email, String password, String firstName, String lastName) {
        myAccountPage = authenticationPage.selectToLogin(email, password);
        HeaderPageComponent headerPageComponent = new HeaderPageComponent();
        assertTrue(headerPageComponent.getLoggedInResult(firstName, lastName), "User name does not match expected or User is not logged in");
    }
}


