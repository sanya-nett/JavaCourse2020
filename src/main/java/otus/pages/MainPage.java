package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.enums.ProfileMenu;

public class MainPage extends AbstractPage {

    private final static String PAGE_URL = "https://otus.ru/";

    @FindBy(css = ".ic-blog-default-avatar")
    private WebElement personalIcon;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        logger.info("Открыть главную страницу");
        driver.get(PAGE_URL);
    }

    private void openProfileMenu() {
        logger.info("Открыть выпадающий список для профиля");
        moveToElement(wait.until(ExpectedConditions.visibilityOf(personalIcon)));
    }

    private void selectProfileMenu(ProfileMenu profileMenu) {
        logger.info(String.format("Выбрать %s из выпадающего спика для профиля", profileMenu));
        String selector = String.format(
                ".header2__right .header2-menu__dropdown-link[href^=\"%s\"]",
                profileMenu.getLinkPrefix()
        );
        driver.findElement(By.cssSelector(selector)).click();
    }

    public PersonalPage openPersonalPage() {
        logger.info("Перейти в личный кабинет через выпадающий список профиля");
        openProfileMenu();
        selectProfileMenu(ProfileMenu.PERSONAL);
        return new PersonalPage(driver);
    }
}
