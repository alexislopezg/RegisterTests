import Page.RegisterPage;
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
        registerPage.navigateToRegisterPage();
    }

    @Test
    public void canUserCreateAccount() {
        registerPage.fillOutRegisterForm("Alexis", "Lopez", "alexislopezg@pm.me", "Test",
                "1993-07-14");
        registerPage.clickSubmit();

        assertTrue(registerPage.isAccountCreated());
    }

    @Test
    public void canUserSubmitAnEmptyForm() {
        registerPage.clickSubmit();

        assertTrue(registerPage.areErrorMessagesShown());
    }

    @Test
    public void canUserInsertAnInvalidBirthDate() {
        registerPage.fillOutRegisterForm("Alexis", "Lopez", "alexislopezg@pm.me", "Test",
                "1993/07/14");

        assertTrue(registerPage.isInputInvalid("birthdate"));
    }

    @Test
    public void canUserInsertAnInvalidEmail() {
        registerPage.fillOutRegisterForm("Alexis", "Lopez", "This is an invalid email", "Test",
                "1993-07-14");
        registerPage.clickSubmit();

        assertTrue(registerPage.isInputInvalid("email"));
    }

    @Test
    public void canUserInsertAnWhiteSpaceAsPassword() {
        registerPage.fillOutRegisterForm("Alexis", "Lopez", "alexislopez@pm.me", "     ",
                "1993-07-14");
        registerPage.clickSubmit();

        assertFalse(registerPage.isAccountCreated());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
