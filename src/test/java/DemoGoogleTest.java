import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoGoogleTest {
    @Test
    public void openGoogle() {
        // Referenciar el driver que estaremos utilizando.
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // El WebDriver deberá apuntar a Chrome, ya que desde aquí, las instrucciones
        // se ejecutarán sobre el chrome que esté abierto.
        WebDriver driver = new ChromeDriver();

        // Navegamos con el driver hacia google.
        driver.navigate().to("https://demoqa.com/login");

        // Escribir usuario en el formulario.
        System.out.println("Escribiendo usuario");
        WebElement inputUser = driver.findElement(By.id("userName"));
        inputUser.sendKeys("Test_Jacob");

        // Escribir contraseña en el formulario.
        System.out.println("Escribiendo contraseña");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("Test1234");

        // Hacer click en el botón de login.
        System.out.println("Haciendo click en el botón de login");
        WebElement loginButton = driver.findElement(By.cssSelector("button[id='login']"));
        loginButton.click();

        // Validar mensaje de error del login.
        System.out.println("Validando mensaje de error");

        // Encontrar elemento por selector CSS.
        WebElement warningInvalidMessage = driver.findElement(By.cssSelector("p[id='name']"));

        // Encontrar elemento con Xpath.
        WebElement warningInvalidMessageXpath = driver
                .findElement(By.xpath("//p[contains(text(),'Invalid username or password!')]"));

        // Vamos a hacer uso de Assert para validar ciertas cosas.
        // En este caso, vamos a validar que el valor del warning sea true, que esté
        // desplegado.
        // assertTrue solo acepta valores booleanos.
        Assert.assertTrue(warningInvalidMessage.isDisplayed());

        // Xpath.
        // p[contains(text(),'Invalid user name or password!')]
        // p[id='name']

        // Cerrar el navegador.
        System.out.println("Cerrando el navegador");
        // driver.close();
        // driver.quit();
    }

    @Test
    public void openEdge() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.navigate().to("https://www.google.com");
    }

    @Test
    public void loginInvalidCredentials() {

    }

    @Test
    public void createNewUser() {
        System.out.println("Inicializando Driver.");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Abrir página.
        System.out.println("Navegando a la página DemoQA.");
        driver.navigate().to("https://www.demoqa.com/login");

        System.out.println("Creando nuevo usuario.");
        WebElement inputUser = driver.findElement(By.xpath("//button[@id='newUser']"));

    }

    @Test
    public void loginValidCredentials() {
        String user = "vectortesting";
        String password = "Vector1234!";

        // Referenciar el driver que estaremos utilizando.
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        // El WebDriver deberá apuntar a Chrome, ya que desde aquí, las instrucciones
        // se ejecutarán sobre el chrome que esté abierto.
        WebDriver driver = new ChromeDriver();

        // Navegamos con el driver hacia google.
        driver.navigate().to("https://demoqa.com/login");

        // Escribir usuario en el formulario.
        System.out.println("Escribiendo usuario");
        WebElement inputUser = driver.findElement(By.xpath("//input[@id='userName']"));
        inputUser.sendKeys(user);

        // Escribir contraseña en el formulario.
        System.out.println("Escribiendo contraseña");
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys(password);

        // Hacer click en el botón de login.
        System.out.println("Haciendo click en el botón de login");
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='login']"));
        loginButton.click();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Buscar que se encuentre el username en el label.
        System.out.println("Buscando el username en el label");

        // De esat manera, con este xpath no me funciona, solo con el contains de
        // arriba.
        // WebElement userLabel = driver.findElement(By.xpath("//label[contains(text(),
        // user)]"));
        WebElement userLabel = driver.findElement(
                By.xpath("//label[@id='userName-value' and contains(text(), user)]"));
        Assert.assertTrue(userLabel.isDisplayed());
    }
}
