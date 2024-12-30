package pengyuw007.wagemate.objects;

public class User {
    private String Name;
    private long Sin;
    private String PWD;

    public User(long sin, String name, String pwd){
        Name = name;
        Sin = sin;
        PWD = pwd;
    }

    public String getName() {
        return Name;
    }

    public long getSin() {
        return Sin;
    }

    public String getPWD() {
        return PWD;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public void setSin(long sin) {
        Sin = sin;
    }
}
