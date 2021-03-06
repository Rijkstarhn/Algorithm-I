import edu.princeton.cs.algs4.StdRandom;

public class ThreeWayQuickSort {
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		while (true) {
			while(less(a[++i], a[lo])) {
				if (i == hi) break;
			}
			while (less(a[lo], a[--j])) {
				if (j == lo) break;
			}
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	private static void exch(Comparable[] a, int i, int min) {
		Comparable mid = a[min];
		a[min] = a[i];
		a[i] = mid;
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) return;
		int i = lo + 1;
		int lt = lo;
		int gt = hi;
		while (i <= gt) {
			if (less(a[i], a[lt])) {
				exch(a, lt++, i++);
			}
			else if (less(a[lt], a[i])) {
				exch(a, i, gt--);
			}
			else {
				i ++;
			}
		}
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
	}
	
	public static void main(String[] args) {
		sort(args);
		for (String s : args) {
			System.out.printf(s + " ");
		}
	}
}
