import java.*;



public class scrapeTest {
	
	
	
	public static void main(String[] args) {
	Scrape test = new Scrape();
	System.out.println(test.webScrape("run"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("walk"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("sprint"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("house"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("school"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("bicycle"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("fog"));//test in order to make sure webscrape is working properly
	System.out.println(test.webScrape("computer"));
	System.out.println(test.webScrape("laptop"));
	System.out.println(test.webScrape("screen"));
	System.out.println(test.webScrape("mirror"));

	
	
	
	System.out.println("\n\n\n--------------------------");
	
	
	
	//test for word that doesn't exist
	Scrape test2 = new Scrape();
	System.out.println(test2.webScrape("asdf123"));//If the word does not exist an exception should be thrown
	
	
	
	
	
	
	}
	
}
