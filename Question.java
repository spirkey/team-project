
public class Question {
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private boolean right;
	private String correctChoice;
	private String choice;

	

	public Question(String question, String a, String b, String c, String d, String correctChoice) {
		super();
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.correctChoice = correctChoice;
		
		this.right = false;
		
	}
	public boolean pick(String ch){
		this.choice = ch;
		if(ch.equals(correctChoice)){
			this.right = true;
			return true;
		}
		this.right = false;
		return false;
	}
	public String printQuestion(){
		String str = "";
		str =	"Question:" + question + 
				"\na: " + a 
				+ "\nb: " + b
				+ "\nc: " + c
				+ "\nd: " + d
				;
		return str;
	}
	
	public String printResults(){
		String str = "";
		str =	"Question: " + question + 
				"\na: " + a 
				+ "\nb: " + b
				+ "\nc: " + c
				+ "\nd: " + d;
		if(right == true){
			str+= "\nYou got it right!";
		}
		else{
			str+= "\nYou got it wrong!";
			str+= "\nThe right answer is " + correctChoice;
		}
		str+= "\nYou picked "  + choice;
		return str;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public String getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

}
