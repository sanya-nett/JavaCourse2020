package otus.fragments.personal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.enums.ContactType;
import otus.fragments.AbstractFragment;

import java.util.List;

/**
 * This class describe interface of each additional contact data on personal page
 */
public class ExtraContactBlockItem extends AbstractFragment {

    private final static By CONTACT_TYPE_DROP_DOWN = By.cssSelector("span.placeholder");
    private final static By CONTACT_TYPE_INPUT = By.cssSelector("input.js-custom-select-input");
    private final static By CONTACT_TYPE_BUTTON = By.cssSelector(".js-custom-select-options > button");
    private final static By TEXT_FIELD = By.cssSelector("input[type='text']");
    private final static By DELETE_BUTTON = By.cssSelector(".container__col_md-0 button.js-formset-delete");

    public ExtraContactBlockItem(WebElement element) {
        super(element);
    }

    /**
     * Remove contact data
     */
    public void clickOnDelete() {
        logger.info("Нажать на кнопку 'Удалить' для блока контакта");
        element.findElement(DELETE_BUTTON).click();
    }

    /**
     * Open select menu for choose necessary contact type
     */
    private void openContactTypeMenu() {
        logger.info("Открыть выпадающий список 'Способ связи'");
        element.findElement(CONTACT_TYPE_DROP_DOWN).click();
    }

    /**
     * @return Current contact type
     */
    public ContactType getContactType() {
        logger.info("Получить установленный способ связи для контакта");
        String contactValue = element.findElement(CONTACT_TYPE_INPUT).getAttribute("value");
        return ContactType.getTypeByValue(contactValue);
    }

    /**
     * @return Current contact data from text field
     */
    public String getContactTextData() {
        logger.info("Получить установленное значение для контакта");
        return element.findElement(TEXT_FIELD).getAttribute("value");
    }

    /**
     * @param contactType Expected contact type
     */
    private void setContactType(ContactType contactType) {
        logger.info(String.format("Установить %s способ связи", contactType));
        List<WebElement> allTypeContacts = element.findElements(CONTACT_TYPE_BUTTON);
        WebElement expectedContactType = allTypeContacts.stream()
                .filter(WebElement::isDisplayed)
                .filter(webElement ->
                        ContactType.getTypeByValue(webElement.getAttribute("data-value")) == contactType)
                .findFirst().orElse(null);
        assert expectedContactType != null;
        expectedContactType.click();
    }

    /**
     * @param data Expected contact data to text field
     */
    private void setContactTextData(String data) {
        logger.info(String.format("Ввести в текстовое поле: %s", data));
        element.findElement(TEXT_FIELD).sendKeys(data);
    }

    /**
     * Public function for set contact type and data
     *
     * @param contactType Expected contact type
     * @param contactData Expected contact data to text field
     */
    public void setContactData(ContactType contactType, String contactData) {
        logger.info("Заполнить контактную информацию");
        openContactTypeMenu();
        setContactType(contactType);
        setContactTextData(contactData);
    }

}
