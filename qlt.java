public class qlt {

public static void main(String[] args) {
		Question q = new Question("what color is an orange?", "green", "blue", "orange", "purple", "orange");
		System.out.println(q.getQuestion());
		System.out.println("Enter choice: ");
		java.util.Scanner s = new java.util.Scanner(System.in);
		String pick = s.next();
		q.pick(pick);
		System.out.println(q.isRight());
		System.out.println(q.getCorrectChoice());
		
		Quiz_Logic ql = new Quiz_Logic();
			
		ql.addPair(q.getQuestion(), q.getCorrectChoice());
		System.out.println(ql.map.keySet());
		System.out.println(ql.map.entrySet());
		ql.readContents("TEXT:MORETEXT" + "\nAND:MORETEXT");	
	}
}
