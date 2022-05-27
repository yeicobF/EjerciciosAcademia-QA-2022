
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AutomationPracticeBaseTest {

  public static final String BASE_URL = "http://automationpractice.com/index.php";
  WebDriver driver;

  @BeforeMethod
  public void setUp() {
    // Declaracion e instanciar variable del driver
    driver = Browser.getBrowserInstance("chrome");
    System.out.println("Abrir pagina ");
    driver.navigate().to(BASE_URL);
  }

  @AfterMethod
  public void tearDown() {
    // driver.quit();
  }
}
