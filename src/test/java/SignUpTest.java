import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {
    @Test
    public void zipCodeShoudldAccept5Digits() {
// Открытие страницы https://sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/Recourses/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");

// Ввести 5 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
// Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertEquals(isDisplayed, true);
// закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeshouldntAccept6Digits() {
// Открытие страницы https://sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/Recourses/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");

        // Ввести 5 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");
        // Нажимаем кнопку Continue

        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться что страница для заполнения формы регистрации не появилась
//        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
//        Assert.assertFalse(isDisplayed, "Баг");
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed, "Баг");
        // Закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeshouldntAccept4Digits() {
// Открытие страницы https://sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/Recourses/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");

        // Ввести 5 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1234");
        // Нажимаем кнопку Continue

        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться что появился текст с ошибкой
        boolean isDisplayed = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        // Закрыть браузер
        driver.quit();

    }
}
