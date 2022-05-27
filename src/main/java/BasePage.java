import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage {
  protected WebDriver driver;
  private final ExpectedCondition<WebElement> pageLoadedCondition;

  public BasePage(WebDriver driver, ExpectedCondition<WebElement> pageLoadedCondition) {
    this.driver = driver;
    this.pageLoadedCondition = pageLoadedCondition;
  }

  public static Wait<WebDriver> getNewWait(int timeoutSeconds, int pollingMillis, WebDriver driver) {
    /*
     * Esperar a que un elemento esté disponible en la página para proceder.
     *
     * https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
     * ui/FluentWait.html
     */
    return new FluentWait<WebDriver>(driver)
        .pollingEvery(Duration.ofMillis(pollingMillis))
        .withTimeout(Duration.ofSeconds(timeoutSeconds))
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
  public void waitUntilPageIsLoaded(Wait<WebDriver> wait) {
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
    wait.until(pageLoadedCondition);
    System.out
        .println("La página " + this.getClass().getSimpleName() + " ya cargó de acuerdo con 'pageLoadedCondition'.");
  }
}
