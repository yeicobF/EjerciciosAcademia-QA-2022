import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationPracticeTest {
  public static String BASE_URL = "http://automationpractice.com/index.php";

  public static Wait<WebDriver> getNewWait(
      int timeoutSeconds, int pollingMillis, WebDriver driver) {
    /*
     * Esperar a que un elemento esté disponible en la página para proceder.
     *
     * https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
     * ui/FluentWait.html
     */
    return new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(timeoutSeconds))
        .pollingEvery(Duration.ofMillis(pollingMillis))
        .ignoring(NoSuchElementException.class);
  }

  /**
   * Vamos a la sección de Contact Us, utilizando un Fluent Wait para esperar
   * a que el elemento esté disponible en cierto tiempo, revisando cada
   * ciertos milisegundos si es que no lo ha encontrado. hasta llegar a un
   * límite.
   * 
   * Esto nos evita problemas de no encontrar algún elemento porque no ha
   * cargado la página.
   * 
   * @param driver
   */
  public static void navigateToContactUs(WebDriver driver) {
    WebElement contactUsButton = driver.findElement(By.xpath("//a[@title='Contact Us']"));
    contactUsButton.click();

    Wait<WebDriver> wait = getNewWait(10, 250, driver);

    /*
     * Esperar hasta que el elemento esperado sea visible. Si no lo es dentro
     * del tiempo indicado en el wait, lanza una excepción, propia de la función
     * until.
     * 
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
     * ui/FluentWait.html
     * 
     * WebElement title = wait.until(new Function<WebDriver, WebElement>() {
     * public WebElement apply(WebDriver driver) {
     * return driver.findElement(By.xpath("//h1[contains(text(), 'Contact us')]"));
     * }
     * });
     */
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(
            By.xpath("//h1[contains(text(), 'Contact us')]")));
    System.out.println("Nos encontramos en Contact Us.");
  }

  public static void selectSubjectOption(int optionNumber, WebDriver driver) {
    // Podríamos seleccionar una opción sin dar click al select, pero también
    // sería bueno revisar que se pueda hacer click porque podría ser que estén
    // las options, pero no funcione el select o algo así.
    driver
        .findElement(By.xpath("//select[@id='id_contact']"))
        .click();

    // Obtenemos todas las opciones.
    List<WebElement> subjectOptions = driver
        .findElements(By.cssSelector("select[id='id_contact'] > option"));

    System.out.println("Hay " + subjectOptions.size() + " opciones en el Subject Heading.");

    subjectOptions.forEach(option -> System.out.println(option.getText()));
    subjectOptions.get(optionNumber).click();

    Assert.assertTrue(subjectOptions.get(optionNumber).isSelected());
  }

  public static Boolean wasMessageSent(WebDriver driver) {
    Wait<WebDriver> wait = getNewWait(10, 250, driver);

    /**
     * https://www.selenium.dev/documentation/webdriver/waits/#fluentwait
     * 
     * Waiting 30 seconds for an element to be present on the page, checking
     * for its presence once every 5 seconds.
     * 
     * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
     * .withTimeout(Duration.ofSeconds(30))
     * .pollingEvery(Duration.ofSeconds(5))
     * .ignoring(NoSuchElementException.class);
     * 
     * WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
     * public WebElement apply(WebDriver driver) {
     * return driver.findElement(By.id("foo"));
     * }
     * });
     */
    Boolean wasMessageSent = wait.until(new Function<WebDriver, Boolean>() {
      public Boolean apply(WebDriver driver) {
        // Buscamos el banner que nos indica que se envió un mensaje, partiendo
        // de la espera indicada con wait. Solo necesitamos saber si se envió el
        // mensaje o no, por lo que no se requiere que guardemos el resultado
        // en una variable.
        return driver
            .findElement(By.xpath("//p[contains(text(), 'Your message has been successfully sent to our team.')]"))
            .isDisplayed();
      }
    });

    return wasMessageSent;
  }

  @Test
  public void sendContactMessage() {
    String email = "correo_prueba@gmail.com";
    String orderReference = "1234";
    String message = "Mensaje de prueba =)";

    WebDriver driver = Browser.getBrowserInstance("chrome");
    driver.navigate().to(BASE_URL);

    navigateToContactUs(driver);
    selectSubjectOption(1, driver);

    driver
        .findElement(By.xpath("//input[@type='text' and @id='email']"))
        .sendKeys(email);

    driver
        .findElement(By.xpath("//input[@type='text' and @id='id_order']"))
        .sendKeys(orderReference);

    driver
        .findElement(By.xpath("//textarea[@id='message']"))
        .sendKeys(message);

    driver
        .findElement(By.xpath("//button[@type='submit' and @id='submitMessage']"))
        .click();

    if (wasMessageSent(driver)) {
      System.out.println("El mensaje se envió correctamente.");
    } else {
      System.out.println("El mensaje no se envió.");
    }
  }
}
