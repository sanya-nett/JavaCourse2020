package otus.pages;

import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.enums.ContactType;
import otus.fragments.personal.AdditionalContactBlock;
import otus.fragments.personal.AdditionalContactBlockItem;

/**
 * This class describe interface of personal page
 */
public class PersonalPage extends BasePage {

    // Personal data
    private final static By NAME_FIELD = By.cssSelector("#id_fname");
    private final static By SURNAME_FIELD = By.cssSelector("#id_lname");

    // Contact data
    private final static By ADDITIONAL_CONTACT_INFO_BLOCK = By.cssSelector("div[data-prefix=contact]");

    // Action buttons
    private final static By SAVE_CONTiNUE_BUTTON = By.cssSelector(".lk-cv-action-buttons > button[name=continue]");
    private final static By SAVE_BUTTON = By.cssSelector(".lk-cv-action-buttons > button:not([name=continue])");

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    // Create custom class for work helpers with elements
    private void cleanFieldAndSetText(By selector, String value) {
        logger.debug(String.format("Очистить поле и ввести: %s", value));
        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        textField.clear();
        textField.sendKeys(value);
    }

    // Create custom class for work helpers with elements
    private String getFieldText(By selector) {
        logger.debug("Взять у элемента текст");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).getAttribute("value");
    }

    /**
     * @return User name on the native language
     */
    public String getName() {
        logger.info("Получить имя пользователя на русском языке");
        return getFieldText(NAME_FIELD);
    }

    /**
     * @return User surname on the native language
     */
    public String getSurname() {
        logger.info("Получить фамилию пользователя на русском языке");
        return getFieldText(SURNAME_FIELD);
    }

    /**
     * Set user name on the native language
     *
     * @param name User name text
     */
    public void setName(String name) {
        logger.info(String.format("Заполнить поле имя: %s", name));
        cleanFieldAndSetText(NAME_FIELD, name);
    }

    /**
     * Set user surname on the native language
     *
     * @param surname User surname text
     */
    public void setSurname(String surname) {
        logger.info(String.format("Заполнить поле фамилия: %s", surname));
        cleanFieldAndSetText(SURNAME_FIELD, surname);
    }

    /**
     * @return Block of additional contacts
     */
    public AdditionalContactBlock getAdditionalContactBlock() {
        return new AdditionalContactBlock(
                wait.until(ExpectedConditions.visibilityOfElementLocated(ADDITIONAL_CONTACT_INFO_BLOCK))
        );
    }

    /**
     * Adds a new additional contact block and fill data in it
     *
     * @param contactType Contact type
     * @param contactData Contact text data
     */
    public void addNewContactData(ContactType contactType, String contactData) {
        logger.info("Добавить новые контактные данные");
        AdditionalContactBlock contactBlock = getAdditionalContactBlock();
        contactBlock.clickOnAddContactInfo();
        logger.debug("Найти последнее контактное поле");
        AdditionalContactBlockItem lastContactBlockItem = Iterables.getLast(contactBlock.getContactInfoList());
        lastContactBlockItem.setContactData(contactType, contactData);
    }

    /**
     * Save changes and don't redirected to another page
     */
    public void clickOnSaveButton() {
        logger.info("Нажать кнопку 'Сохранить и заполнить позже'");
        driver.findElement(SAVE_BUTTON).click();
    }

    /**
     * Save changes and redirected to another page
     */
    public void clickOnSaveAndContinueButton() {
        logger.info("Нажать кнопку 'Сохранить и продолжить'");
        driver.findElement(SAVE_CONTiNUE_BUTTON).click();
    }
}
