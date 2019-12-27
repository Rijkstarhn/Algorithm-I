import java.util.Arrays;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LearnComparator {

	private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
	
	//public static final Comparator<String> LENGTH_SENSITIVE = new lengthSensitive();
	
	private static class lengthSensitive implements Comparator<String>{
		public int compare(String v, String w) {
			if (v.length() < w.length()) {
				return -1;
			}
			else if (v.length() == w.length()) return 0;
			else return 1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] testStrings = {"Java", "is", "beautiful", "Rock","and","roll","Film","lest"};
		
		Arrays.sort(testStrings);
		show(testStrings);
		Arrays.sort(testStrings,new lengthSensitive());
		show(testStrings);
		
//		Arrays.sort(testStrings, String.CASE_INSENSITIVE_ORDER);
//		show(testStrings);
		
		List numbers = Arrays.asList(10, 2, 3, 1, 9, 15, 4);
        Collections.sort(numbers);
        System.out.println(numbers);
        List<String> words = Arrays.asList("B", "X", "A", "M", "F", "W", "O");
        Collections.sort(words, String::compareTo);
        words.sort(String::compareTo);
	}

}
