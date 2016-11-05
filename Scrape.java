import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrape {
	
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		String s = webScrape(a.next());
		System.out.println(s);
	}
	
	public static String webScrape(String input) {
		
		String url = "http://www.dictionary.com/browse/" + input + "?s=t";
		Document doc = null;
		Element data;

		try {
		    doc = Jsoup.connect(url).get();
		    
		    //System.out.print(doc);
			data = doc.select("div.def-content").first();
			//System.out.println(data);
			
			String muhString = data.toString();
			String output = "";
			char c = ' ' ;
			int i = 0;
			muhString = muhString.substring(28);
			
			while(c != ':' && c != '.') {
				c = muhString.charAt(i);
				output += c;
				i++;
			}
			return output.substring(0,output.length() - 1);
			
		} catch (org.jsoup.HttpStatusException e) {
		    System.out.println("Definition scrape error (HTTP_ERROR)");
		} catch (IOException e) {
			System.out.println("Definition scrape error (IO_ERROR)");
		}
		return "";
	}
}

