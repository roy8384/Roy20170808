package google.homepage.search;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTest {
	WebDriver driver = null;
	@BeforeTest
	public void before() throws MalformedURLException {

		Logger log = Logger.getLogger(SearchTest.class);
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Fan_Yan\\AutomationTesting\\chromedriver.exe");
		DesiredCapabilities ieDesiredcap = DesiredCapabilities.chrome();  
		URL url = new URL("http://192.168.99.100:4444/wd/hub");
		driver = new RemoteWebDriver(url,ieDesiredcap);
	}
	
	@Test
	public void testSearch()  {
		

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        driver.manage().window().maximize();
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
	}
	
	@Test
	public void backToHome() {
		driver.findElement(By.xpath("//img[@src='/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png']"));
	}
	
	
	@AfterClass
	public void after() {

        //Close the browser
        driver.quit();
	}
	
	
}
