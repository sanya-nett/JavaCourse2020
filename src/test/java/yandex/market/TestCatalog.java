package yandex.market;

import enums.PriceSorting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestCatalog {

    protected WebDriver driver;
    private static WebDriverWait wait;
    private final Logger logger = LogManager.getLogger(TestCatalog.class);

    @BeforeEach
    private void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        logger.debug("Create browser");
    }

    @AfterEach
    private void tearDown() {
        driver.quit();
        logger.debug("Close browser");
    }

    // Main page navigation
    private void openMainPage() {
        driver.get("https://market.yandex.ru/");
        logger.info("Open site 'Market Yandex'");
    }

    private void openCatalogMenu() {
        By selectorCatalogMenu = By.cssSelector("[data-apiary-widget-id='/header/catalogEntrypoint'] > button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectorCatalogMenu)).click();
        logger.info("Open catalog menu");
    }

    private void mouseMoveToElectronics() {
        By selectorElectronics = By.cssSelector("[data-zone-name='category-link'] [id='91539921-tab']");
        WebElement electronics = wait.until(ExpectedConditions.visibilityOfElementLocated(selectorElectronics));
        logger.debug("Wait electronics tab is loaded");
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).build().perform();
        logger.info("Move mouse to electronics tab");
    }

    private void clickOnSubCategory(String subCategory) {
        String xpathCategory = String.format("//ul[@data-autotest-id='subItems']//a[text()='%s']", subCategory);
        By selectorSmartphones = By.xpath(xpathCategory);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectorSmartphones)).click();
        logger.info(String.format("Click on '%s' category", subCategory));
    }

    private void openSmartphonePage() {
        openMainPage();
        openCatalogMenu();
        mouseMoveToElectronics();
        clickOnSubCategory("Смартфоны");
    }

    // Smartphones pages
    private void setPriceSorting(PriceSorting priceSorting) {
        By selectorPriceSort = By.cssSelector("[data-autotest-id$='price']");
        WebElement priceSort;
        do {
            logger.info("Click on price sorting button");
            priceSort = driver.findElement(selectorPriceSort);
            priceSort.click();
        } while (PriceSorting.getSortByValue(priceSort.getAttribute("data-autotest-id")) != priceSorting);
        logger.info(String.format("Set %s price sorting", priceSorting));
    }

    private void setBrandFilterStatus(String brandFilter, Boolean expectedStatus) {
        By selectorBrandFieldSet = By.cssSelector("[data-autotest-id='7893318']");
        By selectorInputField = By.cssSelector("input[type=text]");
        By selectorCleatButton = By.cssSelector("label");
        By selectorListItem = By.cssSelector("li span");


        WebElement brandBlock = driver.findElement(selectorBrandFieldSet);
        logger.debug("Found brand block");
        WebElement expandButton = brandBlock.findElement(By.cssSelector("button"));
        logger.debug("Found expand button");
        if (expandButton.getText().equals("Показать всё")) {
            expandButton.click();
            logger.info("Expand all brands");
        }
        WebElement inputField = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(brandBlock, selectorInputField));
        logger.debug("Found input brand field");
        if (!inputField.getAttribute("Value").isEmpty()) {
            logger.info("Click on clean brand input field");
            brandBlock.findElement(selectorCleatButton).click();
        }
        wait.until(driver -> brandBlock.findElements(selectorListItem).size() > 1);
        logger.debug("Wait while number of brands in the list became more that one");
        inputField.sendKeys(brandFilter);
        logger.info(String.format("Type text to brand input field: %s", brandFilter));
        wait.until(driver -> brandBlock.findElements(selectorListItem).size() == 1);
        logger.debug("Wait while number of brands in the list equal to one");
        WebElement listCheckbox = brandBlock.findElement(selectorListItem);
        if (listCheckbox.isSelected() != expectedStatus) {
            listCheckbox.click();
            logger.info("Click on checkbox");
            wait.until(driver -> brandBlock.findElement(By.cssSelector("input[type='checkbox']")).isSelected());
            logger.debug(String.format("Wait while checkbox status became %b", expectedStatus));
        }

    }

    private void waitSearchResultsLoaded() {
        By selectorSearchResults = By.xpath("//div[@data-zone-name='snippetList']/../div");
        wait.until(ExpectedConditions.numberOfElementsToBe(selectorSearchResults, 1));
        logger.debug("Blur disappeared");
    }

    private String addFirstProductWithBrand(String brandFilter) {
        By selectorProduct = By.cssSelector("[data-autotest-id='product-snippet']");
        By selectorProductName = By.cssSelector("[data-zone-name='title']");
        By selectorAddToCompare = By.cssSelector("div[aria-label$='сравнению']");
        List<WebElement> products = driver.findElements(selectorProduct);
        for (WebElement product : products) {
            String productName = product.findElement(selectorProductName).getText();
            logger.debug(String.format("Check '%s' product name contains '%s'", productName, brandFilter));
            if (productName.contains(brandFilter)) {
                product.findElement(selectorAddToCompare).click();
                logger.info(String.format("Add '%s' product to comparing", productName));
                return productName;
            }
        }
        throw new NotFoundException("Failed to add first product for expected brand");
    }

    // Compare notification
    private WebElement getNotification() {
        logger.debug("Wait notification");
        By selectorNotification = By.cssSelector("[data-apiary-widget-id='/content/popupInformer']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectorNotification));
    }

    private void checkNotificationText(String productName) {
        String messageText = String.format("Товар %s добавлен к сравнению", productName);
        By selectorMessage = By.xpath(String.format("//div[contains(text(), '%s')]", messageText));
        WebElement notificationTextElement = getNotification().findElement(selectorMessage);
        wait.until(ExpectedConditions.visibilityOf(notificationTextElement));
        logger.info(String.format("Notification has text: %s", messageText));
    }

    private void clickOnNotificationCompare() {
        By selectorCompareButton = By.cssSelector("a[href='/my/compare-lists']");
        getNotification().findElement(selectorCompareButton).click();
        logger.info("Click on compare button");
    }

    private void clickOnNotificationClose() {
        By selectorCloseButton = By.cssSelector("button");
        getNotification().findElement(selectorCloseButton).click();
        logger.info("Close compare notification");
    }

    // Compare page
    private void checkCompareListCount(Integer countProducts) {
        By selectorProductImage = By.cssSelector("[data-apiary-widget-id='/content/compareContent'] img");
        List<WebElement> products = driver.findElements(selectorProductImage);
        Assertions.assertEquals(countProducts, products.size());
        logger.info(String.format("Check that products count equal to %d", countProducts));
    }

    private WebElement getCompareToolbar() {
        logger.debug("Wait toolbar panel");
        By selectorSpecifications = By.xpath("//div[@data-apiary-widget-id='/content/compareContent/compareToolbar']");
        return driver.findElement(selectorSpecifications);
    }

    private void clickOnAllSpecifications() {
        By selectorSpecifications = By.xpath("//button[text()='Все характеристики']");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(getCompareToolbar(), selectorSpecifications)).click();
        logger.info("Click on all specifications");
    }

    private void clickOnDifferentSpecifications() {
        By selectorSpecifications = By.xpath("//button[text()='Различающиеся характеристики']");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(getCompareToolbar(), selectorSpecifications)).click();
        logger.info("Click on different specifications");
    }

    private void checkTypeVisibilityState(boolean expectedState) {
        By selectorTypeSpec = By.xpath("//div[text()='Тип']/..");
        boolean visibilityState;
        try {
            visibilityState = driver.findElement(selectorTypeSpec).isDisplayed();
        } catch (Exception e) {
            visibilityState = false;
        }
        logger.info(String.format("Check that visible state of type field equal to %s", visibilityState));
        Assertions.assertEquals(visibilityState, expectedState, "Unexpected visible state for type field");
    }

    @Test
    public void checkCompareTwoSmartphones() {
        openSmartphonePage();
        setBrandFilterStatus("Samsung", true);
        setBrandFilterStatus("Xiaomi", true);
        setPriceSorting(PriceSorting.UP);
        waitSearchResultsLoaded();
        String firstProduct = addFirstProductWithBrand("Samsung");
        checkNotificationText(firstProduct);
        clickOnNotificationClose();
        String secondProduct = addFirstProductWithBrand("Xiaomi");
        checkNotificationText(secondProduct);
        clickOnNotificationCompare();
        checkCompareListCount(2);
        clickOnAllSpecifications();
        checkTypeVisibilityState(true);
        clickOnDifferentSpecifications();
        checkTypeVisibilityState(false);
    }

}
