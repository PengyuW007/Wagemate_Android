package pengyuw007.wagemate.objects;

public class Job {
    private String URL;
    private String Position; // Name of the job/position
    private double Hour_Wage;
    private double Annual_Wage;

    private double Hours;
    public Job(String URL,String Pos){
        this.URL = URL;
        Position = Pos;
        Annual_Wage = -1;
        Hour_Wage = -1;
        Hours = -1;
    }

    public Job(){
    }
    public double getAnnual_Wage() {
        return Annual_Wage;
    }

    public double getHour_Wage() {
        return Hour_Wage;
    }

    public double getHours() {
        return Hours;
    }

    public String getPosition() {
        return Position;
    }

    public String getURL() {
        return URL;
    }

    public void setAnnual_Wage(double annual_Wage) {
        Annual_Wage = annual_Wage;
    }

    public void setHour_Wage(double hour_Wage) {
        Hour_Wage = hour_Wage;
    }

    public void setHours(double hours) {
        Hours = hours;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
