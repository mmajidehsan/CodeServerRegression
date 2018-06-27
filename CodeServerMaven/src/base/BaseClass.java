package base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.PackageProperties;

public class BaseClass {

	protected String Token;
	protected String Email;
	protected String URL;
	public String FW_URL,CC_URL,EA_URL,IA_URL,CS_URL,GS_URL,JS_URL,DSLC_URL;
	protected String Environament;
	public static ChromeDriver driver ;
	
/*	@BeforeSuite
	@Parameters({"OTP"})*/
	public void GetToken(String OTP){
		System.setProperty("webdriver.chrome.driver", "Libs/Drivers/chromedriver.exe");
/*		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:/Users/mmehsan/AppData/Local/Google/Chrome/User Data");
		options.addArguments("--start-maximized");*/
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Long.valueOf(120), TimeUnit.SECONDS);
		driver.get("http://codeserver-framework-dev.devfactory.com/#/login");
	//	driver.findElement(By.xpath("/a[class='nav-link cs-action-link']")).click();
	//	driver.findElement(By.partialLinkText("Login")).click();
		driver.findElement(By.id("github-btn")).click();
		driver.findElement(By.id("login_field")).sendKeys("mmajidehsan");
		driver.findElement(By.id("password")).sendKeys("939919mmejfk");
		driver.findElement(By.name("commit")).click();
		driver.findElement(By.id("otp")).sendKeys(OTP);
		driver.findElement(By.tagName("button")).click();
		//a5287-9789d
		String Token=driver.findElement(By.tagName("p")).getText();
		System.out.println(Token);
		driver.close();
	}
	
	
	@BeforeTest
	public void Setup() throws IOException{
		Token=PackageProperties.GetProperty("Token");
		Email=PackageProperties.GetProperty("From");
		Environament=PackageProperties.GetProperty("Environment");
		if(Environament.equalsIgnoreCase("Dev")){
			FW_URL=PackageProperties.GetProperty("FW_URL");
			System.out.println(FW_URL);
			CC_URL=PackageProperties.GetProperty("CC_URL");
			EA_URL=PackageProperties.GetProperty("EA_URL");
			IA_URL=PackageProperties.GetProperty("IA_URL");
			CS_URL=PackageProperties.GetProperty("CS_URL");
			GS_URL=PackageProperties.GetProperty("GS_URL");
			JS_URL=PackageProperties.GetProperty("JS_URL");
			DSLC_URL=PackageProperties.GetProperty("DSLC_URL");
		}
		
	}
}
