import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Screenshot;

public class TestScreenshots {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkScreenshots() {
        driver.get("https://google.com");
        Screenshot.save(driver);
        Screenshot.save(driver, "Browser screenshot");
        WebElement logo = driver.findElement(By.cssSelector("#hplogo"));
        Screenshot.save(logo);
        Screenshot.save(logo, "Logo screenshot");
    }
}
