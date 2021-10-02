package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Page {
    protected WebDriver driver;

    public Page(final WebDriver driver) {
        this.driver = driver;
        final int TIMEOUT_IN_SECONDS = 15;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT_IN_SECONDS), this);
    }
}
