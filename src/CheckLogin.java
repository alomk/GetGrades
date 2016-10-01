import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CheckLogin {
	
	private String OSIS,PASSWD;
	private BufferedReader reader;
	//no constructor
	
	public boolean hasLoginInfo(){
		if (new File(".", "data.txt").exists()){
			return true;
		}
		return false;
	
	}
	
	public void createLogin() throws FileNotFoundException, UnsupportedEncodingException {
		Scanner scan = new Scanner(System.in);
		System.out.println("A local file to store your login info will be made (this will remain on your computer only, trust me!)");
		System.out.print("Enter your Pupilpath login (OSIS): ");
		OSIS = scan.next();
		System.out.print("Enter your Pupilpath password: ");
		PASSWD = scan.next();
		PrintWriter writer = new PrintWriter("data.txt", "UTF-8");
		writer.println(OSIS);
		writer.println(PASSWD);
		writer.close();
		scan.close();
	}
	
	public String getOSIS() throws IOException {
		reader = new BufferedReader(new FileReader(new File(".","data.txt")));
		OSIS = reader.readLine();
		reader.close();
		return OSIS;
	}
	
	public String getPASSWD() throws IOException {
		reader = new BufferedReader(new FileReader(new File(".","data.txt")));
		reader.readLine();
		PASSWD = reader.readLine();
		reader.close();
		return PASSWD;
	}
}
