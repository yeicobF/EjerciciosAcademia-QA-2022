import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationPracticeTest extends AutomationPracticeBaseTest {
  private String email;

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
    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);
    final String email = "correo_prueba@gmail.com";

    homePage.clickSignIn();
    signInPage.waitUntilPageIsLoaded(wait);

    Assert.assertTrue(signInPage.isCreateAnAccountTitleDisplayed());
    signInPage.fillEmailAddress(email);
    signInPage.clickCreateAnAccount();
  }
}
