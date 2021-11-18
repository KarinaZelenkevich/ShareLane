import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {
    @Test
    public void zipCodeShoudldAccept5Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertEquals(isDisplayed, true);
        driver.quit();
    }

    @Test
    public void zipCodeshouldntAccept6Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed, "Баг");
        driver.quit();
    }

    @Test
    public void zipCodeshouldntAccept4Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1234");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        driver.quit();

    }

    @Test
    public void successfullRegistration() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("Karina");
        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
        lastName.sendKeys("Holmes");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("jennifer_holmes@406.04.sharelane.com");
        WebElement password_1 = driver.findElement(By.xpath("//input[@name=\"password1\"]"));
        password_1.sendKeys("12345");
        WebElement password_2 = driver.findElement(By.xpath("//input[@name=\"password2\"]"));
        password_2.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        boolean isDisplayed = driver.findElement(By.xpath("//span[@class=\"confirmation_message\"]")).isDisplayed();;
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"confirmation_message\"]")).getText(),
                "Account is created!");
        driver.quit();
    }

    @Test
    public void registrationWithoutLastName() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("Karina");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("jennifer_holmes@406.04.sharelane.com");
        WebElement password_1 = driver.findElement(By.xpath("//input[@name=\"password1\"]"));
        password_1.sendKeys("12345");
        WebElement password_2 = driver.findElement(By.xpath("//input[@name=\"password2\"]"));
        password_2.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        boolean isDisplayed = driver.findElement(By.xpath("//span[@class=\"confirmation_message\"]")).isDisplayed();;
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"confirmation_message\"]")).getText(),
                "Account is created!");
        driver.quit();
    }

    @Test
    public void registrationWithMismatchedPasswords() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("Karina");
        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
        lastName.sendKeys("Holmes");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("jennifer_holmes@406.04.sharelane.com");
        WebElement password_1 = driver.findElement(By.xpath("//input[@name=\"password1\"]"));
        password_1.sendKeys("12345");
        WebElement password_2 = driver.findElement(By.xpath("//input[@name=\"password2\"]"));
        password_2.sendKeys("12255");
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        boolean isDisplayed = driver.findElement(By.xpath("//span[@class=\"error_message\"]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"error_message\"]")).getText(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
        driver.quit();
    }

    @Test
    public void registrationWithInvalidEmail() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("Karina");
        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
        lastName.sendKeys("Holmes");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("jennifer_holmes");
        WebElement password_1 = driver.findElement(By.xpath("//input[@name=\"password1\"]"));
        password_1.sendKeys("12345");
        WebElement password_2 = driver.findElement(By.xpath("//input[@name=\"password2\"]"));
        password_2.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        boolean isDisplayed = driver.findElement(By.xpath("//span[@class=\"error_message\"]")).isDisplayed();;
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"error_message\"]")).getText(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
        driver.quit();
    }

    @Test
    public void inputSpecialCharactersIntoFields() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("#$&**($#@");
        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
        lastName.sendKeys("Ho*>?<<>}");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("jennifer_holmes@406.04.sharelane.com");
        WebElement password_1 = driver.findElement(By.xpath("//input[@name=\"password1\"]"));
        password_1.sendKeys("12345");
        WebElement password_2 = driver.findElement(By.xpath("//input[@name=\"password2\"]"));
        password_2.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        boolean isDisplayed = driver.findElement(By.xpath("//span[@class=\"error_message\"]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"error_message\"]")).getText(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
        driver.quit();
    }

}
