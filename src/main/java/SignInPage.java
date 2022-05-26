import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {
  private static final By byCreateAccountTitle = By
      .xpath("//h3[@class='page-subheading' and contains(text(), 'Create an account')]");
  private static final By byAuthenticationTitle = By
      .xpath("//h1[@class='page-heading' and contains(text(), 'Authentication')]");
  private static final By byEmailAddressField = By
      .xpath("//input[@id='email_create' and @type='text']");
  private static final By byCreateAnAccountButton = By
      .xpath("//button[@id='SubmitCreate' and @type='submit']");
  private static final ExpectedCondition<WebElement> pageLoadedCondition = ExpectedConditions
      .visibilityOfElementLocated(byCreateAccountTitle);

  public SignInPage(WebDriver driver) {
    super(driver, pageLoadedCondition);
  }

  public boolean isCreateAnAccountTitleDisplayed() {
    return driver.findElement(byCreateAccountTitle).isDisplayed();
  }

  public void fillEmailAddress(String email) {
    driver
        .findElement(byEmailAddressField)
        .sendKeys(email);
  }

  public void clickCreateAnAccount() {
    driver
        .findElement(byCreateAnAccountButton)
        .click();
  }
}
