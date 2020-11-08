package otus.pages;

import com.google.common.collect.Iterables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.enums.ContactType;
import otus.fragments.personal.ExtraContactBlock;
import otus.fragments.personal.ExtraContactBlockItem;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class describe interface of personal page
 */
public class PersonalPage extends AbstractPage {

    // Personal data
    @FindBy(css = "#id_fname")
    private WebElement nameField;

    @FindBy(css = "#id_lname")
    private WebElement surnameField;

    @FindBy(css = "#id_fname_latin")
    private WebElement nameLatinField;

    @FindBy(css = "#id_lname_latin")
    private WebElement surnameLatinField;

    @FindBy(css = "#id_blog_name")
    private WebElement blogNameField;

    @FindBy(css = "input[name=date_of_birth]")
    private WebElement birthDatePicker;

    // Main information
    @FindBy(xpath = "//input[@name='country']/../div")
    private WebElement countryField;

    @FindBy(xpath = "//input[@name='country']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
    private List<WebElement> countryList;

    @FindBy(xpath = "//input[@name='city']/../div")
    private WebElement cityField;

    @FindBy(xpath = "//input[@name='city']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
    private List<WebElement> cityList;

    @FindBy(xpath = "//input[@name='english_level']/../div")
    private WebElement languageLevelField;

    @FindBy(xpath = "//input[@name='english_level']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
    private List<WebElement> languageLevelList;

    // Contact data
    @FindBy(css = "div[data-prefix=contact]")
    private WebElement extraContactBlock;

    // Action buttons
    @FindBy(css = ".lk-cv-action-buttons > button[name=continue]")
    private WebElement saveContinueButton;

    @FindBy(css = ".lk-cv-action-buttons > button:not([name=continue])")
    private WebElement saveButton;

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    // Create custom class for work helpers with elements
    private void cleanFieldAndSetText(WebElement textField, String value) {
        logger.debug(String.format("Очистить поле и ввести: %s", value));
        wait.until(ExpectedConditions.visibilityOf(textField)).clear();
        textField.sendKeys(value);
    }

    // Create custom class for work helpers with elements
    private String getFieldText(WebElement element) {
        logger.debug("Взять у элемента текст");
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
    }

    // Create custom class for work helpers with elements
    private void setDivSelector(List<WebElement> elements, String expectedValue) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements))
                    .stream()
                    .filter(element -> element.getText().equalsIgnoreCase(expectedValue))
                    .findFirst().get().click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("Not found list element with value: %s", expectedValue));
        }

    }

    /**
     * @return User's name in native language
     */
    public String getName() {
        logger.info("Получить имя пользователя на русском языке");
        return getFieldText(nameField);
    }

    /**
     * @return User's name in latin language
     */
    public String getLatinName() {
        logger.info("Получить имя пользователя латиницей");
        return getFieldText(nameLatinField);
    }

    /**
     * @return User's surname in native language
     */
    public String getSurname() {
        logger.info("Получить фамилию пользователя на русском языке");
        return getFieldText(surnameField);
    }

    /**
     * @return User's surname in latin language
     */
    public String getLatinSurname() {
        logger.info("Получить фамилию пользователя латиницей");
        return getFieldText(surnameLatinField);
    }

    /**
     * @return User's blog name
     */
    public String getBlogName() {
        logger.info("Получить имя в блоге");
        return getFieldText(blogNameField);
    }

    /**
     * @return User's birth date
     */
    public String getBirthDate() {
        logger.info("Получить дату рождения");
        return getFieldText(birthDatePicker);
    }

    /**
     * @return User's country
     */
    public String getCountry() {
        logger.info("Получить страну");
        return countryField.getText();
    }

    /**
     * @return User's city
     */
    public String getCity() {
        logger.info("Получить город");
        return cityField.getText();
    }

    /**
     * @return User's language level
     */
    public String getLanguageLevel() {
        logger.info("Получить уровень английского");
        return languageLevelField.getText();
    }

    /**
     * Set user's name in native language
     *
     * @param name Expected value for user's native name
     */
    public void setName(String name) {
        logger.info(String.format("Заполнить имя: %s", name));
        cleanFieldAndSetText(nameField, name);
    }

    /**
     * Set user's name in latin language
     *
     * @param name Expected value for user's latin name
     */
    public void setLatinName(String name) {
        logger.info(String.format("Заполнить имя латиницей: %s", name));
        cleanFieldAndSetText(nameLatinField, name);
    }

    /**
     * Set user's surname in native language
     *
     * @param surname Expected value for user's native surname
     */
    public void setSurname(String surname) {
        logger.info(String.format("Заполнить фамилию: %s", surname));
        cleanFieldAndSetText(surnameField, surname);
    }

    /**
     * Set user's surname in latin language
     *
     * @param surname Expected value for user's latin surname
     */
    public void setLatinSurname(String surname) {
        logger.info(String.format("Заполнить фамилию латиницей: %s", surname));
        cleanFieldAndSetText(surnameLatinField, surname);
    }

    /**
     * Set user's blog name
     *
     * @param blogName Expected value for user's blog name
     */
    public void setBlogName(String blogName) {
        logger.info(String.format("Заполнить имя в блоге: %s", blogName));
        cleanFieldAndSetText(blogNameField, blogName);
    }

    /**
     * Set user's blog name
     *
     * @param birthDate Expected value for user's blog name
     */
    public void setBirthDate(String birthDate) {
        logger.info(String.format("Заполнить дату рождения: %s", birthDate));
        cleanFieldAndSetText(birthDatePicker, birthDate);
    }

    /**
     * Set user's country
     *
     * @param country Expected value for user's country
     */
    public void setCountry(String country) {
        logger.info(String.format("Заполнить страну: %s", country));
        countryField.click();
        setDivSelector(countryList, country);
    }

    /**
     * Set user's city
     *
     * @param city Expected value for user's city
     */
    public void setCity(String city) {
        logger.info(String.format("Заполнить город: %s", city));
        cityField.click();
        setDivSelector(cityList, city);
    }

    /**
     * Set user's language level
     *
     * @param languageLevel Expected value for user's language level
     */
    public void setLanguageLevel(String languageLevel) {
        logger.info(String.format("Заполнить уровень английского: %s", languageLevel));
        languageLevelField.click();
        setDivSelector(languageLevelList, languageLevel);
    }

    /**
     * @return Block of additional contacts
     */
    private ExtraContactBlock getExtraContactBlock() {
        return new ExtraContactBlock(wait.until(ExpectedConditions.visibilityOf(extraContactBlock)));
    }

    /**
     * Clean all additional contact data
     */
    public void cleanExtraContacts() {
        logger.info("Очистить все дополнительные контакты");
        getExtraContactBlock().getContactInfoList().forEach(ExtraContactBlockItem::clickOnDelete);
    }

    /**
     * @return All additional contacts
     */
    public HashMap<ContactType, String> getExtraContacts() {
        logger.info("Получить все дополнительные контакты");
        HashMap<ContactType, String> actualContactData = new HashMap<>();
        for (ExtraContactBlockItem contactBlockItem : getExtraContactBlock().getContactInfoList()) {
            actualContactData.put(
                    contactBlockItem.getContactType(),
                    contactBlockItem.getContactTextData()
            );
        }
        ;
        return actualContactData;
    }

    /**
     * Adds a new additional contact block and fill data in it
     *
     * @param contactType Contact type
     * @param contactData Contact text data
     */
    public void addNewExtraContact(ContactType contactType, String contactData) {
        logger.info("Добавить новые контактные данные");
        ExtraContactBlock contactBlock = getExtraContactBlock();
        contactBlock.clickOnAddContactInfo();

        logger.debug("Найти последнее контактное поле");
        ExtraContactBlockItem lastContactBlockItem = Iterables.getLast(contactBlock.getContactInfoList());
        lastContactBlockItem.setContactData(contactType, contactData);
    }

    /**
     * Save changes and don't redirected to another page
     */
    public void clickOnSaveButton() {
        logger.info("Нажать кнопку 'Сохранить и заполнить позже'");
        saveButton.click();
    }

    /**
     * Save changes and redirected to another page
     */
    public void clickOnSaveAndContinueButton() {
        logger.info("Нажать кнопку 'Сохранить и продолжить'");
        saveContinueButton.click();
    }
}
