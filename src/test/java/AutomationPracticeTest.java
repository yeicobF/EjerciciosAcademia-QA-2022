import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationPracticeTest extends AutomationPracticeBaseTest {
  private String email;
  private String title;
  private String firstName;
  private String lastName;
  private String password;

  @Test
  public void sendContactMessage() {
    final HomePage homePage = new HomePage(driver);
    final ContactUsPage contactUs = new ContactUsPage(driver);
    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);

    final String email = "correo_prueba@gmail.com";
    final String orderReference = "1234";
    final String message = "Mensaje de prueba =)";

    Assert.assertTrue(homePage.isHomeLogoDisplayed());
    homePage.clickContactUs();
    contactUs.waitUntilPageIsLoaded(wait);

    Assert.assertTrue(contactUs.isContactUsLabelDisplayed());
    contactUs.selectSubjectOption(1);

    Assert.assertTrue(contactUs.isSubjectOptionSelected(1));
    contactUs.writeEmailAdress(email);
    contactUs.writeOrderReference(orderReference);
    contactUs.writeMessage(message);
    contactUs.submitMessage();

    Assert.assertTrue(contactUs.wasMessageSent(wait));
  }

  @Test
  public void createAnAccount() {
    final HomePage homePage = new HomePage(driver);
    final SignInPage signInPage = new SignInPage(driver);
    final CreateAccountPage createAccountPage = new CreateAccountPage(driver);

    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);
    String email = "correo_prueba@gmail.com";
    String title = "Mr.";
    String firstName = "Juan";
    String lastName = "Perez";
    String password = "123456789";
    int day = 29;
    int month = 4;
    int year = 1990;

    homePage.clickSignIn();
    signInPage.waitUntilPageIsLoaded(wait);
    Assert.assertTrue(signInPage.isCreateAnAccountTitleDisplayed());

    signInPage.fillEmailAddress(email);
    signInPage.clickCreateAnAccount();

    createAccountPage.waitUntilPageIsLoaded(wait);
    Assert.assertTrue(createAccountPage.isFormTitleDisplayed());

    createAccountPage.selectTitle(title);
    createAccountPage.fillFirstName(firstName);
    createAccountPage.fillLastName(lastName);
    createAccountPage.fillEmail(email);
    createAccountPage.fillPassword(password);
    createAccountPage.fillFullBirthDate(day, month, year);
    createAccountPage.signUpForNewsletter();
    createAccountPage.receiveSpecialOffers();
  }
}
