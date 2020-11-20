import com.google.common.collect.Maps;
import enums.Browser;
import org.junit.jupiter.api.*;
import otus.data.User;
import otus.pages.MainSignPage;
import otus.pages.PersonalPage;

public class TestPersonalPage extends TestBase {

    private final String USER_EMAIL = System.getProperty("login", "rahog66236@abbuzz.com");
    private final String USER_PASSWORD = System.getProperty("password", "qaz123qaz");

    @BeforeEach
    private void setUp() {
        logger.debug("Запуск браузера");
        driver = Browser.getDefaultBrowser().create();
    }

    private PersonalPage authAndMoveToPersonalPage() {
        MainSignPage mainSignPage = new MainSignPage(driver);
        mainSignPage.openPage();
        mainSignPage.authorization(USER_EMAIL, USER_PASSWORD);
        return mainSignPage.openPersonalPage();
    }

    private void restartBrowser() {
        logger.info("Перезапустить браузер");
        driver.quit();
        driver = Browser.getDefaultBrowser().create();
    }

    @AfterEach
    private void tearDown() {
        if (driver != null) {
            logger.debug("Закрытие браузера");
            driver.quit();
        }
    }

    @Test
    @DisplayName("Персональные данные пользователя сохраняются после перезапуска браузера")
    public void checkThatPersonalInformationSaveAfterRestartBrowser() {
        PersonalPage personalPage = authAndMoveToPersonalPage();

        logger.info("Заполнить персональные данные на русском языке");
        personalPage.setName(User.NAME);
        personalPage.setSurname(User.SURNAME);

        logger.info("Заполнить персональные данные латиницей");
        personalPage.setLatinName(User.NAME_LATIN);
        personalPage.setLatinSurname(User.SURNAME_LATIN);

        logger.info("Заполнить имя в блоге и дату рождения");
        personalPage.setBlogName(User.BLOG_NAME);
        personalPage.setBirthDate(User.BIRTH_DATE);

        logger.info("Заполнить страну/город/уровень английского");
        personalPage.setCountry(User.COUNTRY);
        personalPage.setCity(User.CITY);
        personalPage.setLanguageLevel(User.LANGUAGE_LEVEL);

        logger.info("Очистка тестовых данных после выполнения теста");
        personalPage.cleanExtraContacts();
        User.EXTRA_CONTACT_DATA.forEach(personalPage::addNewExtraContact);
        personalPage.clickOnSaveButton();
        restartBrowser();
        personalPage = authAndMoveToPersonalPage();

        logger.info(String.format("Проверить, что имя пользователя = %s", User.NAME));
        Assertions.assertEquals(User.NAME, personalPage.getName());

        logger.info(String.format("Проверить, что фамилия пользователя = %s", User.SURNAME));
        Assertions.assertEquals(User.SURNAME, personalPage.getSurname());

        logger.info(String.format("Проверить, что имя пользователя латиницей = %s", User.NAME_LATIN));
        Assertions.assertEquals(User.NAME_LATIN, personalPage.getLatinName());

        logger.info(String.format("Проверить, что фамилия пользователя латиницей = %s", User.SURNAME_LATIN));
        Assertions.assertEquals(User.SURNAME_LATIN, personalPage.getLatinSurname());

        logger.info(String.format("Проверить, что имя в блоге = %s", User.BLOG_NAME));
        Assertions.assertEquals(User.BLOG_NAME, personalPage.getBlogName());

        logger.info(String.format("Проверить, что дата рождения = %s", User.BIRTH_DATE));
        Assertions.assertEquals(User.BIRTH_DATE, personalPage.getBirthDate());

        logger.info(String.format("Проверить, что страна = %s", User.COUNTRY));
        Assertions.assertEquals(User.COUNTRY, personalPage.getCountry());

        logger.info(String.format("Проверить, что город = %s", User.CITY));
        Assertions.assertEquals(User.CITY, personalPage.getCity());

        logger.info(String.format("Проверить, что уровень английского = %s", User.LANGUAGE_LEVEL));
        Assertions.assertEquals(User.LANGUAGE_LEVEL, personalPage.getLanguageLevel());

        logger.info("Проверить, что контактные данные добавились");
        Assertions.assertTrue(Maps.difference(User.EXTRA_CONTACT_DATA, personalPage.getExtraContacts()).areEqual());
    }
}
