package pengyuw007.wagemate.objects;

public class User {
    private String Name;
    private String Sin;
    private String PWD;

    public User(String name, String sin, String pwd){
        Name = name;
        Sin = sin;
        PWD = pwd;
    }

    public String getName() {
        return Name;
    }

    public String getSin() {
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

    public void setSin(String sin) {
        Sin = sin;
    }
}
