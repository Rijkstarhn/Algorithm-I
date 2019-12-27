
public class MergeSort {

	private static void merge(Comparable[]a, Comparable[]aux, int lo, int mid, int hi) {
		// 别写aux = a; 不然aux就和a指向同一个数组了，会随着a一起变！！！
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		// terminal
		if (lo == hi) return;
		//processing
		int mid = (lo + hi)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] a = new String [args.length];
		for (int i = 0; i < args.length; i++) {
			a[i] = args[i];
		}
		String [] aux = new String[a.length]; 
		sort(a, aux, 0, a.length -1);
		show(a);
		
//		String[][] tableStrings = {{"ZS","male","worrior"},{"LS","female","mage"}};
//		for(int i = 0; i < tableStrings.length; i++) {
//			System.out.println(tableStrings[i][2]);
//		}
	}

}
