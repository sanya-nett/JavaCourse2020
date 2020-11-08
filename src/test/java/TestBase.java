import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeAll
    public static void setUpSuite() {
        WebDriverManager.chromedriver().setup();
    }

}
