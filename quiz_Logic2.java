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
			while(s.hasNextLine()) {
				String thisLine = s.nextLine();
				if(thisLine.equals("")) {
					break;
				}
				int delim = thisLine.indexOf(':');
				String word = thisLine.substring(0, delim);
				String def = thisLine.substring(delim+1, thisLine.length());
				map.put(word, def);
			}
		}
		
		public String getValue(String word) {
			return map.get(word);
		}
		
		public void deleteKey(String word) {
			map.remove(word);
		}
			
		public boolean equals(String word){
			return this.equals(word);
		}
		
		public int size() {
			return map.size();
		}
		
		public String toString() {
			return map.keySet().toString() + " " + map.values();
		}
		
		public String randomValue(String key) {
			Random random = new Random();
			List<String> keys = new ArrayList<String>(map.keySet());
			String randomKey = map.get(keys.get( random.nextInt(keys.size()) ));
			return randomKey;
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
