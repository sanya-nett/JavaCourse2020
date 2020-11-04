package otus.fragments.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.fragments.AbstractFragment;

/**
 * This class describe interface to sign form
 */
public class SignBlock extends AbstractFragment {

    // Selectors for signIn/authorization tab
    private final static By LOGIN_EMAIL_FIELD = By.cssSelector("div[data-mode=login] input[name=email]");
    private final static By LOGIN_PASSWORD_FIELD = By.cssSelector("div[data-mode=login] input[name=password]");
    private final static By LOGIN_SUBMIT_BUTTON = By.cssSelector("div[data-mode=login] button[type=submit]");

    // Selectors for signUp/registration tab
    // ...

    public SignBlock(WebElement element) {
        super(element);
    }

    /**
     * @param email User email
     */
    private void fillLoginEmail(String email) {
        logger.info(String.format("Ввести в поле email: %s", email));
        element.findElement(LOGIN_EMAIL_FIELD).sendKeys(email);
    }

    /**
     * @param password User password
     */
    private void fillLoginPassword(String password) {
        logger.info(String.format("Ввести в поле password: %s", password));
        element.findElement(LOGIN_PASSWORD_FIELD).sendKeys(password);
    }

    /**
     * Fill authorization data
     *
     * @param login    User email
     * @param password User password
     */
    public void fillAuthorizationData(String login, String password) {
        logger.info("Заполнить данные для авторизации");
        fillLoginEmail(login);
        fillLoginPassword(password);
    }

    /**
     * Click on authorization submit
     */
    public void clickOnAuthSubmit() {
        logger.info("Нажать на кнопку 'Войти'");
        element.findElement(LOGIN_SUBMIT_BUTTON).click();
    }

}
