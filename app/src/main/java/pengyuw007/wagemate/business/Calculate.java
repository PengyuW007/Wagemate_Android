package pengyuw007.wagemate.business;

import android.util.Log;

import pengyuw007.wagemate.objects.Job;

public class Calculate {
    public static double annual_wage(Job job, String province) {
        final int MONTH = 12;
        final int WEEK_MONTH = 4;
        double res_annual_wage = 0;
        double federal_tax_rate;
        double provincial_tax_rate = 0;

        province = provinceConvert(province);
        Log.i("Annual Wage Calculation", "Converted Province: " + province);

        double hours = job.getHours();
        double hour_wage = job.getHour_Wage();
        double annual_wage = job.getAnnual_Wage();

        if(hour_wage > 0 && hours > 0 && !Double.isNaN(hour_wage)&& !Double.isNaN(hours)){
            res_annual_wage = hours * hour_wage * MONTH * WEEK_MONTH;

            if (annual_wage > 0 && !Double.isNaN(annual_wage)) {
                // If both calculated and inputted annual wage exist, check if they match
                if (Math.abs(res_annual_wage - annual_wage) > 1) {  // Allow small float precision differences
                    Log.w("SalaryCalculator", "Mismatch between inputted annual wage (" + annual_wage +
                            ") and calculated annual wage (" + res_annual_wage + "). Please verify your input.");
                }
            }

            // Set annual wage to the calculated value
            job.setAnnual_Wage(res_annual_wage);
            Log.i("SalaryCalculator", "Calculated and set annual wage: " + res_annual_wage);
        }else if(annual_wage > 0 && !Double.isNaN(annual_wage)){
            res_annual_wage = annual_wage;
            job.setAnnual_Wage(res_annual_wage);
            Log.i("SalaryCalculator", "Using provided annual wage: " + res_annual_wage);
        }else{
            res_annual_wage = 0;
            job.setAnnual_Wage(res_annual_wage);
            Log.e("SalaryCalculator", "Invalid input: Both hourly wage and annual wage are missing, zero, or NaN.");
        }

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

        switch (province) {
            case "AB":
                if (res_annual_wage <= 148269) {
                    provincial_tax_rate = 0.1;
                } else if (res_annual_wage <= 177922) {
                    provincial_tax_rate = 0.12;
                } else if (res_annual_wage <= 237230) {
                    provincial_tax_rate = 0.13;
                } else if (res_annual_wage <= 355845) {
                    provincial_tax_rate = 0.14;
                } else {
                    provincial_tax_rate = 0.15;
                }
                break;
            case "NT":
                if (res_annual_wage <= 50597) {
                    provincial_tax_rate = 0.059;
                } else if (res_annual_wage <= 101198) {
                    provincial_tax_rate = 0.086;
                } else if (res_annual_wage <= 164525) {
                    provincial_tax_rate = 0.122;
                } else {
                    provincial_tax_rate = 0.1405;
                }
                break;
            case "NU":
                if (res_annual_wage <= 53268) {
                    provincial_tax_rate = 0.04;
                } else if (res_annual_wage <= 106537) {
                    provincial_tax_rate = 0.07;
                } else if (res_annual_wage <= 173205) {
                    provincial_tax_rate = 0.09;
                } else {
                    provincial_tax_rate = 0.115;
                }
                break;
            case "YT":
                if (res_annual_wage <= 55867) {
                    provincial_tax_rate = 0.064;
                } else if (res_annual_wage <= 111733) {
                    provincial_tax_rate = 0.09;
                } else if (res_annual_wage <= 173205) {
                    provincial_tax_rate = 0.109;
                } else if (res_annual_wage <= 500000) {
                    provincial_tax_rate = 0.128;
                } else {
                    provincial_tax_rate = 0.15;
                }
                break;
            case "SK":
                if (res_annual_wage <= 52057) {
                    provincial_tax_rate = 0.105;
                } else if (res_annual_wage <= 148734) {
                    provincial_tax_rate = 0.125;
                } else {
                    provincial_tax_rate = 0.145;
                }
                break;
            case "BC":
                if (res_annual_wage <= 47937) {
                    provincial_tax_rate = 0.0506;
                } else if (res_annual_wage <= 95875) {
                    provincial_tax_rate = 0.077;
                } else if (res_annual_wage <= 110076) {
                    provincial_tax_rate = 0.105;
                } else if (res_annual_wage <= 133664) {
                    provincial_tax_rate = 0.1229;
                } else if (res_annual_wage <= 181232) {
                    provincial_tax_rate = 0.147;
                } else if (res_annual_wage <= 252752) {
                    provincial_tax_rate = 0.168;
                } else {
                    provincial_tax_rate = 0.205;
                }
                break;
            case "MB":
                if (res_annual_wage <= 47000) {
                    provincial_tax_rate = 0.108;
                } else if (res_annual_wage <= 100000) {
                    provincial_tax_rate = 0.1275;
                } else {
                    provincial_tax_rate = 0.174;
                }
                break;
            case "NB":
                if (res_annual_wage <= 49958) {
                    provincial_tax_rate = 0.094;
                } else if (res_annual_wage <= 99916) {
                    provincial_tax_rate = 0.14;
                } else if (res_annual_wage <= 185064) {
                    provincial_tax_rate = 0.16;
                } else {
                    provincial_tax_rate = 0.195;
                }
                break;
            case "NS":
                if (res_annual_wage <= 29590) {
                    provincial_tax_rate = 0.0879;
                } else if (res_annual_wage <= 59180) {
                    provincial_tax_rate = 0.1495;
                } else if (res_annual_wage <= 93000) {
                    provincial_tax_rate = 0.1667;
                } else if (res_annual_wage <= 150000) {
                    provincial_tax_rate = 0.175;
                } else {
                    provincial_tax_rate = 0.21;
                }
                break;
            case "NL":
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
                break;
            case "PE":
                if (res_annual_wage <= 32656) {
                    provincial_tax_rate = 0.0965;
                } else if (res_annual_wage <= 64313) {
                    provincial_tax_rate = 0.1363;
                } else if (res_annual_wage <= 105000) {
                    provincial_tax_rate = 0.1665;
                } else if (res_annual_wage <= 140000) {
                    provincial_tax_rate = 0.18;
                } else {
                    provincial_tax_rate = 0.1875;
                }
                break;
            case "ON":
                if (res_annual_wage <= 51446) {
                    provincial_tax_rate = 0.0505;
                } else if (res_annual_wage <= 102894) {
                    provincial_tax_rate = 0.0915;
                } else if (res_annual_wage <= 150000) {
                    provincial_tax_rate = 0.1116;
                } else if (res_annual_wage <= 220000) {
                    provincial_tax_rate = 0.1216;
                } else {
                    provincial_tax_rate = 0.1316;
                }
                break;
            case "QC":
                if (res_annual_wage <= 53255) {
                    provincial_tax_rate = 0.14;
                } else if (res_annual_wage <= 106495) {
                    provincial_tax_rate = 0.19;
                } else if (res_annual_wage <= 129590) {
                    provincial_tax_rate = 0.24;
                } else {
                    provincial_tax_rate = 0.2575;
                }
                break;
        }

        if (hours > 0 && hour_wage > 0) {
            double federal_tax = res_annual_wage * federal_tax_rate;
            double provincial_tax = res_annual_wage * provincial_tax_rate;
            res_annual_wage = res_annual_wage - (federal_tax + provincial_tax);
        }

        return res_annual_wage;
    }

    private static String provinceConvert(String province){
        switch (province) {
            case "Ontario":
                return "ON";
            case "Quebec":
                return "QC";
            case "Nova Scotia":
                return "NS";
            case "New Brunswick":
                return "NB";
            case "Manitoba":
                return "MB";
            case "British Columbia":
                return "BC";
            case "Prince Edward Island":
                return "PE";
            case "Saskatchewan":
                return "SK";
            case "Alberta":
                return "AB";
            case "Newfoundland and Labrador":
                return "NL";
            case "Northwest Territories":
                return "NT";
            case "Yukon":
                return "YT";
            case "Nunavut":
                return "NU";
            default:
                return ""; // Return empty string or handle invalid input appropriately
        }
    }
}
