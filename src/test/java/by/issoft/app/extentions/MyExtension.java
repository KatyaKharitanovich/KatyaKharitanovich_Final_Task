package by.issoft.app.extentions;

import by.issoft.app.driver.WebDriverSingleton;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.opentest4j.AssertionFailedError;

public class MyExtension implements TestWatcher, TestExecutionExceptionHandler {

    @Attachment(value = "Test Env Information", type = "txt")
    private String showTestEnvInfo() {
        RemoteWebDriver driver = (RemoteWebDriver) WebDriverSingleton.getInstance().getWebDriver();
        Capabilities capabilities = driver.getCapabilities();
        String platformVersion = capabilities.getPlatform().name();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getVersion();
        String messageBrowser = "Platform: " + platformVersion + ";\n Browser: " + browserName + ", " + browserVersion;
        return messageBrowser;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshot() {
        return ((TakesScreenshot) WebDriverSingleton.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        showTestEnvInfo();
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof AssertionFailedError) {
            captureScreenshot();
        }
        throw throwable;
    }
}

