import Page.RegisterUserForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegisterUserFormTests {
    private WebDriver driver;
    private RegisterUserForm form;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        form = new RegisterUserForm(driver);
        form.open();
    }

    @Test
    public void registerNewUser_HasNoValues_ShouldDisplayError() {
        form.submit();

        assertTrue(form.areErrorMessagesShown());
    }

    @Test
    public void registerNewUser_HasValidValues_ShouldCreateNewUser() {
        User user = new User(
                "Alexis",
                "Lopez",
                "alexislopezg@pm.me",
                "Test",
                "1993-07-14"
        );

        form.fill(user);
        form.submit();

        assertTrue(form.isAccountCreated());
    }

    @Test
    public void registerNewUser_HasInvalidBirthday_ShouldDisplayAnError() {
        User invalidBirthdateUser = new User(
                "Alexis",
                "Lopez",
                "alexislopezg@pm.me",
                "Test",
                "1993/07/14"
        );

        form.fill(invalidBirthdateUser);

        assertTrue(form.isInputInvalid("birthdate"));
    }

    @Test
    public void registerNewUser_HasInvalidEmail_ShouldDisplayAnError() {
        User invalidEmailUser = new User(
                "Alexis",
                "Lopez",
                "This is an invalid email",
                "Test",
                "1993-07-14"
        );

        form.fill(invalidEmailUser);
        form.submit();

        assertTrue(form.isInputInvalid("email"));
    }

    @Test
    public void registerNewUser_HasAWhitespaceOnlyPassword_ShouldDisplayAnError() {
        User whitespacePasswordUser = new User(
                "Alexis",
                "Lopez",
                "alexislopez@pm.me",
                "     ",
                "1993-07-14"
        );

        form.fill(whitespacePasswordUser);
        form.submit();

        /* TODO: checking for the boolean value of this WebElement is too ambiguous
         * it would be better to provide another method that would check for the exact warning/alert that
         * should be displayed.
         */
        assertFalse(form.isAccountCreated());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
