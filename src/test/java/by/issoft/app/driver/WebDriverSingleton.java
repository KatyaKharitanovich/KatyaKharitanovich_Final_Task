package by.issoft.app.driver;

import by.issoft.app.utils.PropertyValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();
    private WebDriver driver;

    private WebDriverSingleton() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";
//        String browser = "firefox";
        boolean useGrid = System.getProperty("grid") != null;

        if (useGrid) {
            initializeRemoteWebDriver(browser);
        } else {
            initializeLocalWebDriver(browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static WebDriverSingleton getInstance() {
        if (instance.get() == null) {
            synchronized (WebDriverSingleton.class) {
                if (instance.get() == null) {
                    instance.set(new WebDriverSingleton());
                }
            }
        }
        return instance.get();
    }

    private void initializeRemoteWebDriver(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        try {
            URL gridUrl = new URL(PropertyValues.get("grid.url"));
            driver = new RemoteWebDriver(gridUrl, capabilities);
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connect to remote Webdriver");
        }
    }

    private void initializeLocalWebDriver(String browser) {
        switch (browser) {
            case BrowserType.FIREFOX:
                driver = new FirefoxDriver();
                break;
            case BrowserType.IE:
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void closeWebDriver() {
        if (instance.get() != null) {
            driver.quit();
            instance.set(null);
        }
    }
}
