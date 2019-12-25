
public class TestComparatorGeneric implements Comparable<TestComparatorGeneric>{
	private int shit;
	
	public TestComparatorGeneric(int x) {
		this.shit = x;
	}
	
	public int compareTo(TestComparatorGeneric hh) {
		return hh.shit < this.shit? -1: (hh.shit > this.shit? 1:0);
	}
	
//	public int compareTo(int hh) {
//		return shit < 3? -1: (shit == 3? 0:1);
//	}
	
	public static void main(String[] args) {
		TestComparatorGeneric tcg = new TestComparatorGeneric(5);
		TestComparatorGeneric hh = new TestComparatorGeneric(1);
		int ss = 1;
		System.out.println(tcg.compareTo(hh));
		//System.out.println(tcg.compareTo(ss));
		
		System.out.println("Below is no generic");
		
		TestComparatorNoGeneric  tcng = new TestComparatorNoGeneric(5);
		System.out.println(tcng.compareTo(ss));
	}
}

class TestComparatorNoGeneric implements Comparable {
	
	private int shit;
	
	public TestComparatorNoGeneric(int x) {
		this.shit = x;
	}
	
	public int compareTo(Object hh) {
		return (int)hh < this.shit? -1: ((int)hh > this.shit? 1:0);
	}
	
}
