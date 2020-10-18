package enums;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

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

    public void installDriver() {
        DriverManagerType managerType = DriverManagerType.valueOf(this.name());
        WebDriverManager.getInstance(managerType).setup();
    }
}
