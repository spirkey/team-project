import java.util.*;


public class quiz_Logic2 {
		// the Word class
		protected static Map<String, String> map;
		
		public quiz_Logic2() {
			map = new HashMap<String, String>();
		}
		
		public void addPair(String word, String def){
			map.put(word, def);
		}
		
		public void readContents(String contents) {
			Scanner s = new Scanner(contents);
			s.useDelimiter(":");
			while(s.hasNext()) {
				//map.put();
				String thisLine = s.next();
				System.out.println(thisLine);
			}
		}
		
		public String getValue(String word) {
			return map.get(word);
		}
		
		public void deleteKey(String word) {
			map.remove(word);
		}
			
		public boolean equals(String word){
			return map.containsKey(word);
		}
		
		public int size() {
			return map.size();
		}
		//the Question class
		
		/*
		public class Question {
		private int TimesMissed;	//how many times the question was missed
		private Word key;			// holds the word
		private String Definition;	//holds the definition
		
		// Accepts a String with format "Word:Definition" and creates a Question Object
		public Question(String s){
			//Splits it using regex ":" into an array of two subStrings
			//super(String word, String definition); // creates a Pair using the 2 subStrings
				this.TimesMissed=0; 
			}
			
			public boolean equals(Question Q2){
				return key.equals(Q2.key);
			}	
		}
		*/
}
