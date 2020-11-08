package otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FindBy(css = "div[data-modal-id='new-log-reg']")
public class MainSignPage extends MainPage {

    @FindBy(css = "button[data-modal-id=new-log-reg]")
    private WebElement authButton;

    @FindBy(css = "div[data-modal-id='new-log-reg']")
    private WebElement signForm;

    // Selectors for signIn/authorization tab
    @FindBy(css = "div[data-mode=login] input[name=email]")
    private WebElement loginEmailField;

    @FindBy(css = "div[data-mode=login] input[name=password]")
    private WebElement loginPasswordField;

    @FindBy(css = "div[data-mode=login] button[type=submit]")
    private WebElement loginSubmitButton;

    public MainSignPage(WebDriver driver) {
        super(driver);
    }

    private void openSignForm() {
        logger.info("Открыть форму входа/регистрации");
        authButton.click();
        wait.until(ExpectedConditions.visibilityOf(signForm));
    }

    /**
     * @param email User email for authorization
     */
    private void setLoginEmail(String email) {
        logger.info(String.format("Ввести в поле email: %s", email));
        wait.until(ExpectedConditions.visibilityOf(loginEmailField)).sendKeys(email);
    }

    /**
     * @param password User password for authorization
     */
    private void setLoginPassword(String password) {
        logger.info(String.format("Ввести в поле password: %s", password));
        wait.until(ExpectedConditions.visibilityOf(loginPasswordField)).sendKeys(password);
    }

    /**
     * Click on authorization submit
     */
    private void clickOnAuthSubmit() {
        logger.info("Нажать на кнопку 'Войти'");
        loginSubmitButton.click();
    }

    /**
     * Authorization in the system
     *
     * @param email    User's email
     * @param password User's password
     */
    public void authorization(String email, String password) {
        openSignForm();
        logger.info("Заполнить данные для авторизации");
        setLoginEmail(email);
        setLoginPassword(password);
        clickOnAuthSubmit();
    }

}
