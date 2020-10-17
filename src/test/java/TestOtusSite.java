import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestOtusSite {

    protected WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestOtusSite.class);

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkSiteTitle() {
        logger.info("Open \"OTUS\" main page");
        driver.get("https://otus.ru/");
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        logger.info(String.format("Check that page title is equal to \"%s\"", expectedTitle));
        Assert.assertEquals(expectedTitle, driver.getTitle());
    }
}
