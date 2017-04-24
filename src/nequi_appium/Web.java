package nequi_appium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Web {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	String exePath = "C:\\Users\\juan.arboleda\\Downloads\\chromedriver_win32\\chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", exePath);
	driver = new ChromeDriver();
	baseUrl = "https://lbwasawsqa.bancadigital.com.co/bdigitalpsl/#/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @org.junit.Test
  public void testRes() throws Exception {
	System.out.println("1");  
    driver.get(baseUrl);
    driver.findElement(By.id("Inputphone")).clear();
    driver.findElement(By.id("Inputphone")).sendKeys("3999997878");
    System.out.println("4"); 
    driver.findElement(By.id("Inputammount")).clear();
    driver.findElement(By.id("Inputammount")).sendKeys("2000");
    new Select(driver.findElement(By.xpath("//form[@id='form1']/fieldset/select"))).selectByVisibleText("Cédula de ciudadanía");
    driver.findElement(By.xpath("//option[@value='cc']")).click();
    driver.findElement(By.id("Inputdocnum")).clear();
    driver.findElement(By.id("Inputdocnum")).sendKeys("10354896");
    driver.findElement(By.id("Inputemail")).clear();
    driver.findElement(By.id("Inputemail")).sendKeys("cas@fcas.cam");
    driver.findElement(By.cssSelector("button.submit_btn.ng-binding")).click(); 
  }

  @After
  public void tearDown() throws Exception {
	  System.out.println("sali");  
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}

