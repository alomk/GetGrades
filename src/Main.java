import java.io.IOException;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class Main {
	public static void main(String[] args) throws IOException, FailingHttpStatusCodeException, InterruptedException {
		CheckLogin myLogin = new CheckLogin(); // login object for managing login info
		if(myLogin.hasLoginInfo()){
			//GraphicalDriver myDriver = new GraphicalDriver(myLogin.getOSIS(),myLogin.getPASSWD()); //create driver object passing login info
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); //turn off javascript errors
			HeadlessDriver myDriver = new HeadlessDriver(myLogin.getOSIS(),myLogin.getPASSWD());
			Organizer myOrganizer = new Organizer(myDriver.getRawData()); // this object takes the raw html gathered by the driver to organize it
			myOrganizer.siftHTML(); //this method finalizes the data and outputs it
		} else {
			myLogin.createLogin(); //guides user into creating their local profile data
			System.out.println("Run this program again!"); //didnt feel like redirecting to hasLoginInfo again
			return; //force closing program
		}
	}
}
