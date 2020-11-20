package browser;

import enums.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

public class TestSensitiveName {

    protected WebDriver driver;

    @ParameterizedTest
    @ValueSource(strings = {"chRome", "chrome", "CHROME", "Firefox"})
    public void checkBrowserSensitiveName(String browserName) {
        Browser browser = Browser.getBrowser(browserName);
        browser.installDriver();
        driver = browser.create();
        driver.get("https://otus.ru/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
