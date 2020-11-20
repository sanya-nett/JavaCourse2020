import enums.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {

    protected static Browser defaultBrowser = Browser.getDefaultBrowser();

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeAll
    public static void setUpSuite() {
        defaultBrowser.installDriver();
    }

}