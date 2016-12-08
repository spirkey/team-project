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


	
	// test for words we think can be found on online dictionary
	System.out.println("scrape definition for the word \'run\':");
	System.out.println(test.webScrape("run"));			//test in order to make sure webscrape is working properly
	System.out.println("--------------------------");
	
	System.out.println("scrape definition for the word \'speed\':");
	System.out.println(test.webScrape("speed"));		//test in order to make sure webscrape is working properly
	System.out.println("--------------------------");
	
	//test for words that don't exist
	System.out.println("fail scrape definition for the word \'asdf123\':");
	System.out.println(test.webScrape("asdf123"));		//If the word does not exist an exception should be thrown
	System.out.println("--------------------------");
	
	System.out.println("fail scrape definition for the word \'onwoeif\':");
	System.out.println(test.webScrape("onwoeif"));		//If the word does not exist an exception should be thrown
	System.out.println("--------------------------");
	
	}
	
}