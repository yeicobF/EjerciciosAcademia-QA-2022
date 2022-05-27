import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends BasePage {
  private static final By byYourPersonalInformation = By
      .xpath("//h3[@class='page-subheading' and contains(text(), 'Your personal information')]");
  private static final ExpectedCondition<WebElement> pageLoadedCondition = ExpectedConditions
      .visibilityOfElementLocated(byYourPersonalInformation);
  private static final By byTitle = By.xpath("//input[@type='radio' and @name='id_gender']");
  private static final By byFirstName = By.xpath("//input[@type='text' and @id='customer_firstname']");
  private static final By byLastName = By.xpath("//input[@id='customer_lastname']");
  private static final By byEmail = By.xpath("//input[@id='email']");
  private static final By byPassword = By.xpath("//input[@id='passwd']");
  private static final By byBirthDay = By.xpath("//select[@id='days']");
  private static final By byBirthMonth = By.xpath("//select[@id='months']");
  private static final By byBirthYear = By.xpath("//select[@id='years']");
  private static final By bySignUpNewsletter = By.xpath("//input[@type='checkbox' and @id='newsletter']");
  private static final By byReceiveSpecialOffers = By.xpath("//input[@type='checkbox' and @id='optin']");

  public CreateAccountPage(WebDriver driver) {
    super(driver, pageLoadedCondition);
  }

  public boolean isFormTitleDisplayed() {
    return driver
        .findElement(byYourPersonalInformation)
        .isDisplayed();
  }

  public void selectTitle(String title) {
    List<WebElement> titles = driver.findElements(byTitle);
    int titleNumber = 0;

    if (title.equals("Mr.")) {
      titleNumber = 1;
    }
    if (title.equals("Mrs.")) {
      titleNumber = 2;
    }

    // Busca el title dentro del array de titles (inputs). Si lo encuentra,
    // termina la ejecución de la función. Si no, sigue con el loop y regresa
    // una excepción al salir.
    for (WebElement titleElement : titles) {
      String id = titleElement.getAttribute("id");

      if (id.equalsIgnoreCase("id_gender" + titleNumber)) {
        titleElement.click();
        return;
      }
    }

    throw new IllegalArgumentException("No se encontró el title " + title);
  }

  public void fillFirstName(String firstName) {
    driver.findElement(byFirstName).sendKeys(firstName);
  }

  public void fillLastName(String lastName) {
    driver.findElement(byLastName).sendKeys(lastName);
  }

  public void fillEmail(String email) {
    WebElement emailAddressField = driver.findElement(byEmail);
    emailAddressField.clear();
    emailAddressField.sendKeys(email);
  }

  public void fillPassword(String password) {
    driver.findElement(byPassword).sendKeys(password);
  }

  public void fillDayOfBirth(int day) {
    Select daySelect = new Select(driver.findElement(byBirthDay));
    daySelect.selectByValue(String.valueOf(day));
  }

  public void fillMonthOfBirth(int month) {
    Select monthSelect = new Select(driver.findElement(byBirthMonth));
    monthSelect.selectByValue(String.valueOf(month));
  }

  public void fillYearOfBirth(int year) {
    Select yearSelect = new Select(driver.findElement(byBirthYear));
    yearSelect.selectByValue(String.valueOf(year));
  }

  public void fillFullBirthDate(int day, int month, int year) {
    fillDayOfBirth(day);
    fillMonthOfBirth(month);
    fillYearOfBirth(year);
  }

  public void signUpForNewsletter() {
    driver.findElement(bySignUpNewsletter).click();
  }

  public void receiveSpecialOffers() {
    driver.findElement(byReceiveSpecialOffers).click();
  }

}
