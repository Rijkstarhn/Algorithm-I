
public class Heap {

	public static void sort(Comparable[] a) {
		// create MaxHeap
		int N = a.length;
		for (int k = N/2; k >= 1; k--) {
			sink(a,k,N);
		}
		
		// sort by getting the heap root
		while(N > 1) {
			exch(a, N, 1);
			sink(a, 1, --N);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N) {
		while (2*k <= N) {
			int j = k*2;
			if (j < N && less(a,j, j+1)) j++;// j should have condition < N if j is the first item in its row
			if (less(a, k, j)) {
				exch(a,k, j);
				k = j;
			}
			else break;
		}
	}
	
	private static boolean less(Comparable[] a, int x, int y) {
		return a[x-1].compareTo(a[y-1]) < 0;// here the -1 is crucial 
	}
	
	public static void exch(Comparable[] a, int x, int y) {
		Comparable mid = a[x-1];// here the -1 is crucial
		a[x-1] = a[y-1];
		a[y-1] = mid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap testHeap = new Heap();
		Integer[] testArray = {1,5,2,6,9,3,1,6,7,4,0};
		testHeap.sort(testArray);
		for (Integer i : testArray) {
			System.out.print(i + " ");
		}
	}

}
