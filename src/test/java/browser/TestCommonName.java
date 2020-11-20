package browser;

import enums.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCommonName {

    protected WebDriver driver;

    private void checkGoogleTitle() {
        driver.get("https://www.google.com/");
        Assertions.assertEquals("Google", driver.getTitle());
    }

    @Test
    public void checkBrowserFromProperty() {
        String browserProperty = System.getProperty("browser");
        Browser browser = Browser.getBrowser(browserProperty);
        browser.installDriver();
        driver = browser.create();
        checkGoogleTitle();
    }

    @Test
    public void checkBrowserChromeOptions() {
        Browser chromeBrowser = Browser.CHROME;
        chromeBrowser.installDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        driver = chromeBrowser.create(chromeOptions);
        checkGoogleTitle();
    }

    @Test
    public void checkBrowserNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Browser.getBrowser("Edge"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
