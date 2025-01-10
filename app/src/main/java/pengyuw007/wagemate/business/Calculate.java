package pengyuw007.wagemate.business;

import pengyuw007.wagemate.objects.Job;

public class Calculate {
    public static double annual_wage(Job job, String province) {
        double res_annual_wage = 0;
        double federal_tax_rate = 0;
        double provincial_tax_rate = 0;

        double hours = job.getHours();
        double hour_wage = job.getHour_Wage();

        res_annual_wage = hours * hour_wage;

        if (res_annual_wage <= 55867) {
            federal_tax_rate = 0.15;
        } else if (res_annual_wage <= 111733) {
            federal_tax_rate = 0.205;
        } else if (res_annual_wage <= 173205) {
            federal_tax_rate = 0.26;
        } else if (res_annual_wage <= 246752) {
            federal_tax_rate = 0.29;
        } else {
            federal_tax_rate = 0.33;
        }

        if (province.equals("AB")) {

        } else if (province.equals("NT")) {

        } else if (province.equals("NU")) {

        } else if (province.equals("YT")) {

        } else if (province.equals("SK")) {

        } else if (province.equals("BC")) {

        } else if (province.equals("MB")) {

        } else if (province.equals("NB")) {

        } else if (province.equals("NS")) {

        } else if (province.equals("NL")) {
            if (res_annual_wage <= 43198) {
                provincial_tax_rate = 0.087;
            } else if (res_annual_wage <= 86395) {
                provincial_tax_rate = 0.145;
            } else if (res_annual_wage <= 154244) {
                provincial_tax_rate = 0.158;
            } else if (res_annual_wage <= 215943) {
                provincial_tax_rate = 0.178;
            } else if (res_annual_wage <= 275870) {
                provincial_tax_rate = 0.198;
            } else if (res_annual_wage <= 551739) {
                provincial_tax_rate = 0.208;
            } else if (res_annual_wage <= 1103478) {
                provincial_tax_rate = 0.213;
            } else {
                provincial_tax_rate = 0.218;
            }
        } else if (province.equals("PE")) {

        } else if (province.equals("ON")) {

        } else if (province.equals("QC")) {

        }

        if (hours > 0 && hour_wage > 0) {

        }

        return res_annual_wage;
    }
}
