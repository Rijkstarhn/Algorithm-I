
public class SelectionSort{
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (less(a[j],a[min])) {
					min = j;
				}
			}
			exch(a,i,min);
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
	            System.out.println(a[i]);
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer [] a = new Integer [args.length];
		for (int i = 0; i < args.length; i++) {
			a[i] = Integer.parseInt(args[i]);
		}
		SelectionSort.sort(a);
		show(a);
	}

}
