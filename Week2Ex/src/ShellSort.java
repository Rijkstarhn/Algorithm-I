
public class ShellSort {
	
	public static void sort(Comparable[] a) {
		// h = 3*x + 1;
		// simple and beautiful one
		int h = 1;
		while(h < a.length/3) h = 3*h + 1;
		
		// InsertSort with interval h
		while(h >= 1) {
			for (int i = h; i < a.length; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j = j-h) {
						exch(a, j, j - h);
				}
			}
			h = h/3;
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
		sort(a);
		show(a);
	}
}
