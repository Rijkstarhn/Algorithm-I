import java.util.HashSet;

public class OlympicAthlete {
	private int age;
	private String name;
	// test for defensive copy
	private int[] ID;
	
	public OlympicAthlete(String name, int age) {
        this.name = name;
        this.age = age;
    }
	
	// test for defensive copy
	public OlympicAthlete(String name, int age, int[] id) {
        this.name = name;
        this.age = age;
        this.ID = id;
        ID[0] = 5;
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
          
        //�����ͬһ�����󷵻�true����֮����false  
        if(this == obj){  
            return true;  
        }  
          
        //�ж��Ƿ�������ͬ  
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
    	// �½�Person����
        OlympicAthlete p1 = new OlympicAthlete("eee", 100);
        OlympicAthlete p2 = new OlympicAthlete("eee", 100);
        OlympicAthlete p3 = new OlympicAthlete("aaa", 200);
        OlympicAthlete p4 = new OlympicAthlete("EEE", 100);

        // �½�HashSet���� 
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        // �Ƚ�p1 �� p2�� ����ӡ���ǵ�hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // ��ӡset
        System.out.printf("set:%s\n", set);
        // test for defensive copy
        int[] id = {3,4,0,8};
        System.out.println(id[0]);
        OlympicAthlete defcopy = new OlympicAthlete("e",3, id);
        System.out.println(id[0]);
        id[3] = 7;
        System.out.println(defcopy.ID[3]);
	}
}
