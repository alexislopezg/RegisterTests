import Page.RegisterPage;
import model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegisterTests {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        registerPage = new RegisterPage(driver);
        registerPage.open();
    }

    @Test
    public void canUserSubmitAnEmptyForm() {
        registerPage.submit();

        assertTrue(registerPage.areErrorMessagesShown());
    }

    @Test
    public void canUserCreateAccount() {
        User user = new User(
                "Alexis",
                "Lopez",
                "alexislopezg@pm.me",
                "Test",
                "1993-07-14"
        );

        registerPage.fillOutRegisterForm(user);
        registerPage.submit();

        assertTrue(registerPage.isAccountCreated());
    }

    @Test
    public void canUserInsertAnInvalidBirthDate() {
        User invalidBirthdateUser = new User(
                "Alexis",
                "Lopez",
                "alexislopezg@pm.me",
                "Test",
                "1993/07/14"
        );

        registerPage.fillOutRegisterForm(invalidBirthdateUser);

        assertTrue(registerPage.isInputInvalid("birthdate"));
    }

    @Test
    public void canUserInsertAnInvalidEmail() {
        User invalidEmailUser = new User(
                "Alexis",
                "Lopez",
                "This is an invalid email",
                "Test",
                "1993-07-14"
        );

        registerPage.fillOutRegisterForm(invalidEmailUser);
        registerPage.submit();

        assertTrue(registerPage.isInputInvalid("email"));
    }

    @Test
    public void canUserInsertAnWhiteSpaceAsPassword() {
        User emptyPasswordUser = new User(
                "Alexis",
                "Lopez",
                "alexislopez@pm.me",
                "     ",
                "1993-07-14"
        );

        registerPage.fillOutRegisterForm(emptyPasswordUser);
        registerPage.submit();

        assertFalse(registerPage.isAccountCreated());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
