import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
  private static final By byHomeSalesLogo = By.xpath("//div[@id='homepage-slider']");
  private static final By byContactUsButton = By.xpath("//a[@title='Contact Us']");
  private static final By bySignIn = By.xpath("//a[@class='login' and contains(text(), 'Sign in')]");
  public static final ExpectedCondition<WebElement> pageLoadedCondition = ExpectedConditions
      .visibilityOfElementLocated(byHomeSalesLogo);

  public HomePage(WebDriver driver) {
    super(driver, pageLoadedCondition);
  }

  public boolean isHomeLogoDisplayed() {
    return driver.findElement(byHomeSalesLogo).isDisplayed();
  }

  public void clickContactUs() {
    driver
        .findElement(byContactUsButton)
        .click();
  }

  public void clickSignIn() {
    driver
        .findElement(bySignIn)
        .click();
  }
}
