
public class UnorderedMaxPQ <Key extends Comparable<Key>>{
	private Key[] pq;
	private int N;
	
	public UnorderedMaxPQ(int capacity) {
		pq = (Key[])new Comparable[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void insert(Key x) {
		pq[N++] = x;
	}
	
	public Key delMax() {
		int max = 0;
		for(int i = 1; i < N; i++) {
			if (less(pq[max], pq[i])) max = i;
		}
		exch(pq, N-1, max);
		return pq[--N];// Instead of N-1
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int min) {
		Comparable mid = a[min];
		a[min] = a[i];
		a[i] = mid;
	}
	
	public static void main(String[] args) {
		UnorderedMaxPQ testpq = new UnorderedMaxPQ(3);
		System.out.println(testpq.isEmpty());
		testpq.insert(1);
		testpq.insert(2);
		testpq.insert(3);
		System.out.println(testpq.isEmpty());
		System.out.println(testpq.delMax());
	}
}