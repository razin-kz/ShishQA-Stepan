import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SecondTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        String titleActual = driver.getTitle();
        String titleExpected = "Unlimint Payment Page";
        Assert.assertEquals("Title not matching", titleExpected, titleActual);
    }

    @Test
    public void secondPayment() throws IOException {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000002");
        WebElement cardholderName = driver.findElement(By.id("input-card-holder"));
        cardholderName.sendKeys("Si Von");
        WebElement cardExperisedMonth = driver.findElement(By.id("card-expires-month"));
        cardExperisedMonth.sendKeys("07");
        WebElement cardExperisedYear = driver.findElement(By.id("card-expires-year"));
        cardExperisedYear.sendKeys("2022");
        WebElement paymentdataCardCvcPlaceholder = driver.findElement(By.id("input-card-cvc"));
        paymentdataCardCvcPlaceholder.sendKeys("777");
        WebElement Question = driver.findElement(By.id("cvc-hint-toggle"));
        Question.click();
        WebElement Page = driver.findElement(By.id("main-container"));

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, Page);
        ImageIO.write(screenshot.getImage(), "jpg", new File("C:\\Users\\админ\\Desktop\\Homework-1\\google-logo.png"));
        assertTrue(new File("C:\\Users\\админ\\Desktop\\Homework-1\\google-logo.png").exists());

        WebElement paymentdataActionsSubmit = driver.findElement(By.id("action-submit"));
        paymentdataActionsSubmit.click();
        WebElement Success = driver.findElement(By.id("success"));
        Success.submit();
    }

}
