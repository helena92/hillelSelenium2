import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class Tests {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Parameters({"baseURL", "username"})
    @Test(priority = 1)
    public void testOpenApp(String baseURL, String username) {
        driver.get(baseURL);
        Assert.assertEquals(driver.getTitle(), "Sign in | Slack");
    }

    @Test(priority = 2)
    public void testGoToTeam() {
        WebElement teamInput = driver.findElement(By.id("domain"));
        teamInput.sendKeys("hilleltestteam");
        teamInput.submit();
        Assert.assertEquals(driver.getTitle(), "Slack");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
