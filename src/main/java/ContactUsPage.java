import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ContactUsPage extends BasePage {
  private final By byContactUsLabel = By.xpath("//h1[contains(text(), 'Customer service - Contact us')]");
  private final By byEmailField = By.xpath("//input[@type='text' and @id='email']");
  private final By bySubjectDropdown = By.xpath("//select[@id='id_contact']");
  private final By bySubjectDropdownOptions = By.cssSelector("select[id='id_contact'] > option");
  private final String byOrderReference = "//input[@type='text' and @id='id_order']";
  private final By bySubmitMessageButton = By.xpath("//button[@type='submit' and @id='submitMessage']");
  private final By byMessageField = By.xpath("//textarea[@id='message']");
  private final By byAlertMessageSuccess = By
      .xpath("//p[contains(text(), 'Your message has been successfully sent to our team.')]");

  public ContactUsPage(WebDriver driver) {
    super(driver);
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
  public void waitUntilContactUsPageIsLoaded(Wait<WebDriver> wait) {
    // Wait<WebDriver> wait = getNewWait(10, 250, driver);

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
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(byContactUsLabel));

    System.out.println("Nos encontramos en Contact Us.");
  }

  public boolean isContactUsLabelDisplayed() {
    return driver
        .findElement(byContactUsLabel)
        .isDisplayed();
  }

  public void clickSubjectDropdown() {
    System.out.println("Seleccionamos el dropdown de Subject Heading.");
    // Podríamos seleccionar una opción sin dar click al select, pero también
    // sería bueno revisar que se pueda hacer click porque podría ser que estén
    // las options, pero no funcione el select o algo así.
    driver
        .findElement(bySubjectDropdown)
        .click();
  }

  public List<WebElement> getSubjectOptions() {
    return driver
        .findElements(bySubjectDropdownOptions);
  }

  public void selectSubjectOption(int optionNumber) {
    // Obtenemos todas las opciones.
    List<WebElement> subjectOptions = driver
        .findElements(bySubjectDropdownOptions);

    System.out.println("Hay " + subjectOptions.size() + " opciones en el Subject Heading.");
    subjectOptions.forEach(option -> System.out.println(option.getText()));

    subjectOptions.get(optionNumber).click();
  }

  public boolean isSubjectOptionSelected(int optionNumber) {
    return getSubjectOptions().get(optionNumber).isSelected();
  }

  public void writeEmailAdress(String email) {
    System.out.println("Escribimos el email: " + email);
    driver
        .findElement(byEmailField)
        .sendKeys(email);
  }

  public void writeOrderReference(String orderReference) {
    System.out.println("Escribimos el Order Reference: " + orderReference);
    driver
        .findElement(By.xpath(byOrderReference))
        .sendKeys(orderReference);
  }

  public void writeMessage(String message) {
    System.out.println("Escribimos el mensaje.");
    driver
        .findElement(byMessageField)
        .sendKeys(message);
  }

  public void submitMessage() {
    System.out.println("Enviando formulario.");
    driver
        .findElement(bySubmitMessageButton)
        .click();
  }

  public Boolean wasMessageSent(Wait<WebDriver> wait) {
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
    return wait.until(new Function<WebDriver, Boolean>() {
      public Boolean apply(WebDriver driver) {
        // Buscamos el banner que nos indica que se envió un mensaje, partiendo
        // de la espera indicada con wait. Solo necesitamos saber si se envió el
        // mensaje o no, por lo que no se requiere que guardemos el resultado
        // en una variable.
        return driver
            .findElement(byAlertMessageSuccess)
            .isDisplayed();
      }
    });
  }

}
