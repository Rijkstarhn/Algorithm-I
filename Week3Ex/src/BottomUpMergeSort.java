
public class BottomUpMergeSort {

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		for (int sub = 2; sub < a.length; sub *= 2) {
			for (int j = lo + sub - 1; j <= hi; j += sub) {
				int i = j - sub + 1;
				int mid = (i + j)/2;
				merge(a, aux, i, mid, Math.min(j, hi));
			}
		}
		merge(a, aux, lo, (lo+hi)/2, hi);
	}
	
	private static void merge(Comparable[]a, Comparable[]aux, int lo, int mid, int hi) {
		// 别写aux = a; 不然aux就和a指向同一个数组了，会随着a一起变！！！
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
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
	}

}
