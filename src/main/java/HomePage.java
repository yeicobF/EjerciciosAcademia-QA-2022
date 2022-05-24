import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
  private final By byHomeSalesLogo = By.xpath("//div[@id='homepage-slider']");
  private final By byContactUsButton = By.xpath("//a[@title='Contact Us']");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public boolean isHomeLogoDisplayed() {
    return driver.findElement(byHomeSalesLogo).isDisplayed();
  }

  public void clickContactUs() {
    driver
        .findElement(byContactUsButton)
        .click();
  }
}
