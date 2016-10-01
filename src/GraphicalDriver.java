import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
/*
 * Use of this driver is discouraged
 */
public class GraphicalDriver {
	
	WebDriver driver;
	FirefoxProfile profile;
	private String OSIS,PASSWD;
	private String rawHTML;
	
	public GraphicalDriver(String OSIS, String PASSWD) {
		this.OSIS = OSIS;
		this.PASSWD = PASSWD;
	}
	
	public String getRawData() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override", "Mozilla/5.0 (X11; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0"); //set user-agent
		driver = new FirefoxDriver(profile);
		try{
			driver.get("http://www.pupilpath.com");
			driver.navigate().to("https://pupilpath.skedula.com/redirectToAuth.aspx");
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/form/input[3]")).sendKeys(OSIS);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/form/input[4]")).sendKeys(PASSWD);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/form/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/div/button")).click();
			Thread.sleep(2000); //allowing page to load
			rawHTML = driver.getPageSource();
			driver.close();
			driver.quit();
			return (rawHTML); //page html
		}catch(Exception e){
			driver.close();
			driver.quit();
			System.out.println(e);
			return ("Error");
		}
	}
}
