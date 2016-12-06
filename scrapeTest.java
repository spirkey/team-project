import java.*;



public class scrapeTest {
	
	
	
	public static void main(String[] args) {
	Scrape test = new Scrape();
	System.out.println(test.webScrape("run"));//test in order to make sure webscrape is working properly
	
	System.out.println("\n\n\n--------------------------");
	
	
	
	//test for word that doesn't exist
	Scrape test2 = new Scrape();
	System.out.println(test2.webScrape("asdf123"));//If the word does not exist an exception should be thrown
	
	
	
	
	
	
	}
	
}
