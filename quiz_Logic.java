import java.util.*;

public class quiz_Logic {
	// the Word class
		public class Word extends String{
			
			public Word(String w){
				super(w);
			}
			public boolean equals(Word w2){
				return word.equals(w2.word);
			}
		}
		//the Question class
		
		public class Question {
			private int TimesMissed;	//how manytimes the question was missed
		private Word key;	// holds the word
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
		// Quiz class
		public class QuizFile{
			private File f;
			private ArrayList<Question> quiz; // holds all the word:definitions in the file
		// creates a QuizFile
		public QuizFile(Sting fileName){
			// checks to see if such a file exists 
			// if there is already an existing file with this name then it will use it
			// if not then it creates an empty file
			//if there is an existing file then it will read the contents into an ArrayList		
		}
		// looks up the words index in the Question array
		// if the word is not in the Array -1 is returned
		public int IndexOfWord(String word){
			int index=-1;
			Word key = new Word(word);
			// loops through the Array to find the word's index
			return index;
		}
		// accepts a string makes a question object adds the question 
		// to the file and returns true, if the word already exist in the
		// File/Array then it deletes it and replaces it with the new one
		public boolean AddQuestion(String s){
			return true;
		}
		//accepts the word's String representation, looks to see if this word
		// exists in the file/Array if it does then it deletes the question
		// from the array
		public boolean StrikeQuestion(String word){
			return true;
		}
		// looks a word up and returns the definition as a string
		public String WordLookUp(String word){
			return " ";
		}
		// returns the ArrayList of Questions
		public ArrayList<Question> AllQuestions(){
			return this.quiz;
		}
	}
}
