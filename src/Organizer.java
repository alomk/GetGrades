import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Organizer {
	
	private final String rawHTML;
	private LinkedList<String> classes = new LinkedList<String>();
	private LinkedList<String> grades = new LinkedList<String>();
	private double sum = 0;
	
	public Organizer(String rawHTML) {
		this.rawHTML = rawHTML;
	}
	
	public void siftHTML() {
		Document parsedHTML = Jsoup.parse(rawHTML);
		for (Element row : parsedHTML.getElementById("progress-card").select("tbody").select("tr")) {
			if((!(row.getElementsByTag("td").get(4).text().contains("-")) && !(row.getElementsByTag("td").get(1).text().contains("PHYS EDUCATION")))) {
				classes.add(row.getElementsByTag("td").get(1).text()); //index 1 is the name of the course
				grades.add(row.getElementsByTag("td").get(4).text().substring(4)); //index 4 is the average
			}
		}
		for (int i=0; i<=5;i++) {
			System.out.print(classes.get(i) + ": ");
			System.out.println(grades.get(i));
		}
		for (String i : grades) {
			sum += Double.parseDouble(i);
		}
		System.out.println("Average = " + sum/grades.size());
	}

}
