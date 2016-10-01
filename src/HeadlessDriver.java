import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HeadlessDriver {

	private String OSIS,PASSWD;
	private String rawHTML;
	
	public HeadlessDriver(String OSIS, String PASSWD) {
		this.OSIS = OSIS;
		this.PASSWD = PASSWD;
	}
	public String getRawData() throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		final WebClient driver = new WebClient(BrowserVersion.FIREFOX_45);
		try {
			driver.getOptions().setRedirectEnabled(true);
			driver.getOptions().setJavaScriptEnabled(true);
			driver.getOptions().setUseInsecureSSL(true);
			final HtmlPage page2 = driver.getPage("https://pupilpath.skedula.com/redirectToAuth.aspx");
			final HtmlForm form = page2.getForms().get(0);
			final HtmlTextInput username = form.getInputByName("user[username]");
			final HtmlPasswordInput password = form.getInputByName("user[password]");
			final HtmlButton button = (HtmlButton) form.getElementsByTagName("button").get(0);
			username.setValueAttribute(OSIS);
			password.setValueAttribute(PASSWD);
			final HtmlPage page3 = button.click();
			final HtmlButton button2 = (HtmlButton) page3.getElementsByIdAndOrName("loginSKD").get(0);
			Thread.sleep(1000); //page loading
			final HtmlPage page4 = button2.click();
			Thread.sleep(1000); //page loading
			rawHTML = page4.asXml();
			driver.close();
			return rawHTML;
		} catch (Exception e) {
			driver.close();
			return ("Error");
		}
	}
}
