
public class BinaryHeapMaxPQ <Key extends Comparable<Key>>{

	private Key[] pq;
	private int N;
	
	public BinaryHeapMaxPQ(int capacity) {
		pq = (Key[])new Comparable[capacity+1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void insert(Key x) {
		pq[++N] = x;
		swim(N);
	}
	
	public Key delMax() {
		Key del = pq[1];
		exch(N--, 1);
		sink(1);
		pq[N+1] = null;// prevent loitering
		return del;
	}
	
	private void swim(int k) {
		while (k > 1 &&  less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int j = k*2;
			if (j < N && less(j, j+1)) j++;// j should have condition < N if j is the first item in its row
			if (less(k, j)) {
				exch(k, j);
				k = j;
			}
			else break;
		}
	}
	
	private boolean less(int x, int y) {
		return pq[x].compareTo(pq[y]) < 0;
	}
	
	public void exch(int x, int y) {
		Key mid = pq[x];
		pq[x] = pq[y];
		pq[y] = mid;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryHeapMaxPQ testPq = new BinaryHeapMaxPQ(20);
		String[] input = {"S","P","R","N","T","O","A","E","I","H","G"};// for testing insert()
		System.out.println(testPq.isEmpty());
		for (String s: input) {
			testPq.insert(s);
		}
		System.out.println(testPq.isEmpty());
		testPq.insert("Z");// for testing swim()
		testPq.exch(testPq.N-1, 1);
		testPq.sink(1);
		System.out.println(testPq.delMax());
	}

}
