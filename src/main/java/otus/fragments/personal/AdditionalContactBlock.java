package otus.fragments.personal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.fragments.AbstractFragment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class describe interface of the additional contacts data on personal page
 */
public class AdditionalContactBlock extends AbstractFragment {

    private final static By ADD_BUTTON = By.cssSelector("button.js-formset-add");
    private final static By CONTACT_INFO_ITEM = By.cssSelector(".js-formset-row:not(.hide)");

    public AdditionalContactBlock(WebElement element) {
        super(element);
    }

    /**
     * Add new contact item in the additional contacts block
     */
    public void clickOnAddContactInfo() {
        logger.info("Нажать на кнопку добавить контакт");
        element.findElement(ADD_BUTTON).click();
    }

    /**
     * @return List of additional contacts (without email/phone number)
     */
    public List<AdditionalContactBlockItem> getContactInfoList() {
        logger.info("Получить список всех контактов");
        return element.findElements(CONTACT_INFO_ITEM)
                .stream()
                .map(AdditionalContactBlockItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Remove all additional contacts
     */
    public void deleteAllContacts() {
        logger.info("Удалить все возможные контакты");
        getContactInfoList().forEach(AdditionalContactBlockItem::clickOnDelete);
    }
}
