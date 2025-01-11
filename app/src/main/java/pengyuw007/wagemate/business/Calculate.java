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
            if (res_annual_wage <= 52057) {
                provincial_tax_rate = 0.105;
            } else if (res_annual_wage <= 148734) {
                provincial_tax_rate = 0.125;
            } else {
                provincial_tax_rate = 0.145;
            }
        } else if (province.equals("BC")) {

        } else if (province.equals("MB")) {
            if (res_annual_wage <= 47000) {
                provincial_tax_rate = 0.108;
            } else if (res_annual_wage <= 100000) {
                provincial_tax_rate = 0.1275;
            } else {
                provincial_tax_rate = 0.174;
            }
        } else if (province.equals("NB")) {
            if (res_annual_wage <= 49958) {
                provincial_tax_rate = 0.094;
            } else if (res_annual_wage <= 99916) {
                provincial_tax_rate = 0.14;
            } else if (res_annual_wage <= 185064) {
                provincial_tax_rate = 0.16;
            } else {
                provincial_tax_rate = 0.195;
            }
        } else if (province.equals("NS")) {
            if (res_annual_wage <= 29590) {
                provincial_tax_rate = 0.0879;
            } else if (res_annual_wage <= 59180) {
                provincial_tax_rate = 0.1495;
            } else if (res_annual_wage <= 93000) {
                provincial_tax_rate = 0.1667;
            }  else if (res_annual_wage <= 150000) {
                provincial_tax_rate = 0.175;
            } else {
                provincial_tax_rate = 0.21;
            }
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
            if (res_annual_wage <= 32656) {
                provincial_tax_rate = 0.0965;
            } else if (res_annual_wage <= 64313) {
                provincial_tax_rate = 0.1363;
            } else if (res_annual_wage <= 105000) {
                provincial_tax_rate = 0.1665;
            }  else if (res_annual_wage <= 140000) {
                provincial_tax_rate = 0.18;
            } else {
                provincial_tax_rate = 0.1875;
            }
        } else if (province.equals("ON")) {
            if (res_annual_wage <= 51446) {
                provincial_tax_rate = 0.0505;
            } else if (res_annual_wage <= 102894) {
                provincial_tax_rate = 0.0915;
            } else if (res_annual_wage <= 150000) {
                provincial_tax_rate = 0.1116;
            }  else if (res_annual_wage <= 220000) {
                provincial_tax_rate = 0.1216;
            } else {
                provincial_tax_rate = 0.1316;
            }
        } else if (province.equals("QC")) {

        }

        if (hours > 0 && hour_wage > 0) {

        }

        return res_annual_wage;
    }
}
