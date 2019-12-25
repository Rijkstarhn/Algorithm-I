
public class InsertSort {

	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {				
					exch(a, j, j-1);
			}
		}
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int min) {
		Comparable mid = a[min];
		a[min] = a[i];
		a[i] = mid;
	}
	
	 private static void show(Comparable[] a) {
	        for (int i = 0; i < a.length; i++) {
	            System.out.print(a[i] + " ");
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer [] a = new Integer [args.length];
		for (int i = 0; i < args.length; i++) {
			a[i] = Integer.parseInt(args[i]);
		}
		InsertSort.sort(a);
		show(a);
	}

}
