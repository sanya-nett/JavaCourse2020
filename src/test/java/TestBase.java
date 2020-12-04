import enums.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {

    protected static Browser defaultBrowser = Browser.getDefaultBrowser();

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeAll
    public static void setUpSuite() {
        defaultBrowser.installDriver();
    }

    @BeforeEach
    private void setUp() {
        logger.debug(String.format("Запуск %s браузера", defaultBrowser));
        driver = defaultBrowser.create();
    }

    @AfterEach
    private void tearDown() {
        if (driver != null) {
            logger.debug(String.format("Закрытие %s браузера", defaultBrowser));
            driver.quit();
        }
    }

    public void restartBrowser() {
        logger.info("Перезапустить браузер");
        driver.quit();
        driver = defaultBrowser.create();
    }

}
