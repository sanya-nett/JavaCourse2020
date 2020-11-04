package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class describe common functional for all pages
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }

    /**
     * @return Actual page url
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * @param element Move mouse to special element
     */
    public void moveToElement(WebElement element) {
        logger.debug("Навести мышкой на элемент");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
