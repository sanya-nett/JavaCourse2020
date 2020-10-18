package browser;

import enums.Browser;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestSensitiveName {

    protected WebDriver driver;
    private final String browserName;

    public TestSensitiveName(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters
    public static Collection<String> browser() {
        return Arrays.asList("chRome", "chrome", "CHROME", "Firefox");
    }

    @Test
    public void checkBrowserSensitiveName() {
        Browser browser = Browser.getBrowser(browserName);
        browser.installDriver();
        driver = browser.create();
        driver.get("https://otus.ru/");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
