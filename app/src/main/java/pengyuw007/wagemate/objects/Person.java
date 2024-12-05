package pengyuw007.wagemate.objects;

public class Person {
    private String Name;
    private String Sin;

    public Person(String name,String sin){
        Name = name;
        Sin = sin;
    }

    public String getName() {
        return Name;
    }

    public String getSin() {
        return Sin;
    }
}
