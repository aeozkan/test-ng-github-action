import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//trendyol.com'a gir ve bir ürün arat, arama sonucunu doğrula, bir ürünü sepete ekle ve sepete eklendiğini doğrula

public class TrendyolTests {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // 1. Önce WebDriver'ı hazırla
        WebDriverManager.chromedriver().setup();
        // 2. Options nesnesini oluştur (Kızmaması için tipini belirtmelisin)
        ChromeOptions options = new ChromeOptions();
        // 3. Argümanları ekle
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu"); // Windows sistemlerde kararlılık için önerilir
        options.addArguments("--no-sandbox");
        options.addArguments("--headless=new");          // ← EKLE
        options.addArguments("--window-size=1920,1080"); // ← EKLE
        options.addArguments("--disable-dev-shm-usage"); // ← EKLE (Linux'ta bellek sorunu önler)
        // 4. Driver'ı bu options ile başlat (EN KRİTİK ADIM)
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 1)
    public void testSearchProduct() throws InterruptedException {
        driver.get("https://www.trendyol.com/");
        driver.findElement(By.xpath("//div[@class='modal-action-button' and text()='Kadın']")).click();
        driver.findElement(By.cssSelector(".suggestion-placeholder")).click();
        WebElement searchBar = driver.findElement(By.xpath("//input[@data-testid='browsing-search-input']"));
        searchBar.click();
        searchBar.clear();
        Thread.sleep(1000);
        searchBar.sendKeys("52 mm cpl filtre" + Keys.ENTER);
        // driver.findElement(By.xpath("//label[@for='checkbox-LeafCategory-104038']")).click();
    }

    @Test(priority = 2)
    public void testResultVerify() throws InterruptedException {
        WebElement result = driver.findElement(By.className("result-count-info"));
        String actualData = result.getText();
        Assert.assertTrue(actualData.contains("+ Ürün"));
    }

    @Test(priority = 3)
    public void testProductClick() throws InterruptedException {
        Thread.sleep(1000);
        //WebElement productClick =
                driver.findElement(By.xpath("//a[@data-product-index='1']")).click();
        //productClick.click();
    }
    @Test(priority = 4)
    public void sepeteEkle(){
        List<String> sekmeler = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(sekmeler.get(1)); // 1 numaralı (ikinci) sekmeye gider
        driver.findElement(By.xpath("//button[@class='onboarding__default-renderer-primary-button']")).click();
        driver.findElement(By.xpath("//p[@class='add-to-cart-button-label']")).click();
    }
    @Test(priority = 5)
    public void basketCountVerify(){
        WebElement basketCount = driver.findElement(By.xpath("//div[@class='basket-count']"));
        String actualData = basketCount.getText();
        String expectData = "1";
        Assert.assertEquals(actualData, expectData);
    }
    @AfterClass
    public void teakdown(){
        driver.quit();
    }
}
