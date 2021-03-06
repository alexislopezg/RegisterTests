package Page;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterUserForm extends Page {

    @FindBy(name = "firstname")
    private WebElement inputFirstname;

    @FindBy(name = "lastname")
    private WebElement inputLastname;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(name = "man")
    private WebElement inputMale;

    @FindBy(name = "birthdate")
    private WebElement inputBirthdate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//span[@class='error']")
    private List<WebElement> listErrors;

    public RegisterUserForm(final WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://3.87.50.247:3000/");
    }

    public void fill(User user) {
        inputFirstname.sendKeys(user.getName());
        inputLastname.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        inputBirthdate.sendKeys(user.getBirthDate());
        inputMale.click();
    }

    public void submit() {
        buttonSubmit.click();
    }

    public boolean isAccountCreated() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public boolean areErrorMessagesShown() {
        return listErrors.size() == 6;
    }

    public boolean isInputInvalid(String fieldName) {
        return driver.findElement(By.xpath("//input[contains(@class,'invalid') and @name='" + fieldName + "']")).isDisplayed();
    }
}
