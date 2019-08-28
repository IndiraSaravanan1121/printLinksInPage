package OrangehrmAutomation.OrangehrmFormAutomation;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CallingBrowser {
	static WebDriver driver = null;
	static FileInputStream fis;
	static FileInputStream fis1;
	static FileInputStream fis2;

	@BeforeTest
	public void setup() throws Exception {

		fis = new FileInputStream("./src/main/resources/TestData/config.properties");

		Properties obj = new Properties();
		obj.load(fis);
		String browserName1 = obj.getProperty("browserName");

		String url = obj.getProperty("url");

		switch (obj.getProperty("browserName1")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\indira.saravanan\\eclipse-workspace\\OrangehrmFormAutomation\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\indira.saravanan\\eclipse-workspace\\OrangehrmFormAutomation\\lib\\geckodriver.exe");
			driver = new FirefoxDriver();
	
			break;

		case "IEdriver":
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\indira.saravanan\\eclipse-workspace\\OrangehrmFormAutomation\\lib\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
			break;

		}
		driver.get(url);
	}

	@Test
	public void Automate() throws Exception {

		fis1 = new FileInputStream(
				"C:\\Users\\indira.saravanan\\eclipse-workspace\\OrangehrmFormAutomation\\src\\main\\resources\\Locator\\path.properties");
		Properties obj1 = new Properties();
		obj1.load(fis1);

		fis2 = new FileInputStream(
				"C:\\Users\\indira.saravanan\\eclipse-workspace\\OrangehrmFormAutomation\\src\\main\\resources\\Locator\\data.properties");
		Properties obj2 = new Properties();
		obj2.load(fis2);

		// driver.findElement(By.xpath("//a[@class=\"optanon-allow-all
		// accept-cookies-button\"]")).click();
		driver.findElement(By.name(obj1.getProperty("loc_firstname_txt_name"))).sendKeys(obj2.getProperty("firstname"));
		driver.findElement(By.name(obj1.getProperty("loc_lastname_txt_name"))).sendKeys(obj2.getProperty("lastname"));
		driver.findElement(By.name(obj1.getProperty("loc_companyname_txt_name"))).sendKeys(obj2.getProperty("company"));
		driver.findElement(By.name(obj1.getProperty("loc_numberofemployees_sct_name")))
				.sendKeys(obj2.getProperty("numemployees"));
		driver.findElement(By.name(obj1.getProperty("loc_phonenumber_txt_name"))).sendKeys(obj2.getProperty("phone"));
		driver.findElement(By.name(obj1.getProperty("loc_jobtitle_txt_name"))).sendKeys(obj2.getProperty("jobtitle"));
		driver.findElement(By.name(obj1.getProperty("loc_email_txt_name"))).sendKeys(obj2.getProperty("email"));
		driver.findElement(By.name(obj1.getProperty("loc_country_sct_name"))).sendKeys(obj2.getProperty("country"));
		driver.findElement(By.name(obj1.getProperty("loc_comment_txt_name"))).sendKeys(obj2.getProperty("message"));

	}

	@AfterTest
	public void link() {
		List<WebElement> list = driver.findElements(By.tagName("a"));
		System.out.println("Links" + list.size());

		for (int i = 0; i < list.size(); i++) {

			WebElement element = list.get(i);
			String url = element.getAttribute("href");
			System.out.println(url);
		}
		driver.close();
	}

}
