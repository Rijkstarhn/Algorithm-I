import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.valueOf(args[0]);
		RandomizedQueue<String> testQueue = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()) {
			testQueue.enqueue(StdIn.readString());
		}
//		if(k < 0 || k > testQueue.size()) throw new IllegalArgumentException("k's value is illegal!");
		for(int i = 0; i < k; i++) {
			System.out.println(testQueue.dequeue());
		}
	}
}
