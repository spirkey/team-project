import java.util.Map;

public class qlt {

	public static void main(String[] args) {
		Question q = new Question("what color is an orange?", "green", "blue", "orange", "purple", "orange");
		
		quiz_Logic2 ql = new quiz_Logic2();
		
		ql.addPair(q.getQuestion(), q.getCorrectChoice());
		
		System.out.println(ql.map.keySet());
		System.out.println(ql.map.entrySet());
	}

}