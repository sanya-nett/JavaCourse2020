package enums;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum Browser {
    CHROME, FIREFOX, SAFARI;

    public static Browser getBrowser(String browserName) {
        for (Browser browser : values()) {
            if (browser.name().equals(browserName.toUpperCase())) {
                return browser;
            }
        }
        throw new IllegalArgumentException(String.format("No found enum for browser name: %s", browserName));
    }

    public static Browser getDefaultBrowser() {
        return Browser.getBrowser(System.getProperty("browser"));
    }

    public void installDriver() {
        DriverManagerType managerType = DriverManagerType.valueOf(this.name());
        WebDriverManager.getInstance(managerType).setup();
    }

    public WebDriver create(MutableCapabilities options) {
        switch (this) {
            case CHROME: {
                if (options != null) {
                    return new ChromeDriver((ChromeOptions) options);
                }
                return new ChromeDriver();
            }
            case FIREFOX: {
                if (options != null) {
                    return new FirefoxDriver((FirefoxOptions) options);
                }
                return new FirefoxDriver();
            }
            case SAFARI: {
                if (options != null) {
                    return new SafariDriver((SafariOptions) options);
                }
                return new SafariDriver();
            }
            default: {
                throw new IllegalArgumentException(String.format("Not found driver for browser: %s", this));
            }
        }
    }

    public WebDriver create() {
        return create(null);
    }
}
