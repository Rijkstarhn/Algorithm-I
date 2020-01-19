import java.util.HashSet;

public class OlympicAthlete {
	private int age;
	private String name;
	public OlympicAthlete(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "("+name + ", " +age+")";
    }

    //Override
    @Override
    public boolean equals(Object obj){  
        if(obj == null){  
            return false;  
        }  
          
        //如果是同一个对象返回true，反之返回false  
        if(this == obj){  
            return true;  
        }  
          
        //判断是否类型相同  
        if(this.getClass() != obj.getClass()){  
            return false;  
        }  
          
        OlympicAthlete person = (OlympicAthlete)obj;  
        return name.equals(person.name) && age==person.age;  
    }
    
    @Override
    public int hashCode(){  
        int nameHash =  name.toUpperCase().hashCode();
        return nameHash ^ age;
    }
    
    public static void main(String[] args) {
    	// 新建Person对象，
        OlympicAthlete p1 = new OlympicAthlete("eee", 100);
        OlympicAthlete p2 = new OlympicAthlete("eee", 100);
        OlympicAthlete p3 = new OlympicAthlete("aaa", 200);
        OlympicAthlete p4 = new OlympicAthlete("EEE", 100);

        // 新建HashSet对象 
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);
	}
}
