import java.io.IOException;
import java.net.UnknownHostException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrape {
	
	public String webScrape(String input) {
		
		String url = "http://www.dictionary.com/browse/";
		
		String var = input;		
		url = url + var;
		
		url = url + "?s=t";
	
		Document doc = null;
		Element data;
		String test = null;

		try {
		    doc = Jsoup.connect(url).get();
		} catch (org.jsoup.HttpStatusException e) {
		    System.out.println("Definition scrape error (HTTP_ERROR)");
		} catch (IOException e) {
			System.out.println("Definition scrape error (IO_ERROR)");
		}
		
		Document tester = null;
		if (tester == doc)
		{
			System.exit(0);
		}

		data = doc.select("div.def-content").first();

		//System.out.println(data);
		//System.out.print(doc);
		
		String muhString = data.toString();
		String output = "";
		char c = ' ' ;
		int i = 0;
		muhString = muhString.substring(28);
		
		while(c != '<') {
			c = muhString.charAt(i);
			output += c;
			i++;
		}
		return output.substring(0,output.length() - 1);
	}
}

