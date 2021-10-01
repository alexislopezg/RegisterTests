package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegisterPage extends Page {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "firstname")
    private WebElement nombreInput;

    @FindBy(name = "lastname")
    private WebElement apellidoInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "man")
    private WebElement manInput; //sexo masculino

    @FindBy(name = "birthdate")
    private WebElement birthDateInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class='error']")
    private List<WebElement> errorSpanList;

    @FindBy(xpath = "//input[contains(@class,'invalid')]")
    private List<WebElement> invalidInputErrorList;

    public RegisterPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        final int TIME_OUT_IN_SECONDS = 10;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    public void navigateToRegisterPage() {
        driver.get("http://3.87.50.247:3000/");
    }

    public void fillOutRegisterForm(String name, String lastName, String email, String password, String birthDate) {
        nombreInput.sendKeys(name);
        apellidoInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        birthDateInput.sendKeys(birthDate);
        manInput.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public boolean isAccountCreated() {
        try {
            driver.switchTo().alert();
            return true;
        }  
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public boolean areErrorMessagesShown() {
        return errorSpanList.size() == 6;
    }

    public boolean isInputInvalid(String fieldName) {
        return driver.findElement(By.xpath("//input[contains(@class,'invalid') and @name='" + fieldName + "']")).isDisplayed();
    }
}
