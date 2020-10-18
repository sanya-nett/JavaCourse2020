package browser;

import enums.Browser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCommonName {

    protected WebDriver driver;

    private void checkGoogleTitle() {
        driver.get("https://www.google.com/");
        Assert.assertEquals("Google", driver.getTitle());
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

    @Test(expected = IllegalArgumentException.class)
    public void checkBrowserNotFound() {
        Browser browser = Browser.getBrowser("Edge");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
