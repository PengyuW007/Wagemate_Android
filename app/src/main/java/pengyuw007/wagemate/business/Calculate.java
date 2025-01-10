package pengyuw007.wagemate.business;

import java.util.List;

import pengyuw007.wagemate.objects.Job;

public class Calculate {

    public static double annualWage(Job job){
        double annual_wage = job.getAnnual_Wage();
        double hour_wage = job.getHour_Wage();
        double hours = job.getHours();
        double res_annual_wage = -1;

        if(hours>0){
            if(annual_wage == hours*hour_wage){
                res_annual_wage = annual_wage;
            }else {
                res_annual_wage = hour_wage*hours;
            }
        }else{
            System.out.println("Hours invalid!");
        }

        return res_annual_wage;
    }
}
