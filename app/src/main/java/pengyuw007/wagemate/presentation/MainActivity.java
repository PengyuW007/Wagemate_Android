package pengyuw007.wagemate.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pengyuw007.wagemate.R;
import pengyuw007.wagemate.business.AccessJobs;
import pengyuw007.wagemate.business.Calculate;
import pengyuw007.wagemate.objects.Job;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView userInfoView,net_annual_wage;
    private EditText urlText, positionText, weeklyHoursText, hourlyWageText, annualWageText;
    private Spinner provincePicker;
    private Button calculate;

    private static final String provinces[] = {"Select the province you located", "Alberta",
            "British Columbia",
            "Manitoba",
            "New Brunswick",
            "Newfoundland and Labrador",
            "Northwest Territories",
            "Nova Scotia",
            "Nunavut",
            "Ontario",
            "Prince Edward Island",
            "Quebec",
            "Saskatchewan",
            "Yukon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double wage = net_annual_wage(); // Calculate wage
                    net_annual_wage.setText(String.format("Net Annual Wage: $%.2f", wage)); // Show result
                } catch (Exception e) {
                    net_annual_wage.setText("Error: " + e.getMessage()); // Show error message
                }
            }
        });
    }

    private void initUI() {
        String userInfo = getIntent().getStringExtra("User information");
        userInfoView = findViewById(R.id.User_info);
        userInfoView.setText(userInfo);

        urlText = findViewById(R.id.Website);

        positionText = findViewById(R.id.Position);

        provincePicker = (Spinner) findViewById(R.id.Province);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provincePicker.setAdapter(adapter);
        provincePicker.setOnItemSelectedListener(this);

        weeklyHoursText = findViewById(R.id.hours);

        hourlyWageText = findViewById(R.id.Hourly_wage);

        annualWageText = findViewById(R.id.Annual_wage);

        net_annual_wage = findViewById(R.id.Net_annual_wage);

        calculate = findViewById(R.id.calculate);
    }

    private double net_annual_wage() {
        String userInfo = getIntent().getStringExtra("User information");
        boolean isGuest = userInfo.equals("Guest\t\t\t\t\tGuest0000");
        String province = provincePicker.getSelectedItem().toString();
        double hours = Double.parseDouble(weeklyHoursText.getText().toString());
        double hourlyWage = Double.parseDouble(hourlyWageText.getText().toString());
        double annual_wage = Double.parseDouble(annualWageText.getText().toString());
        double res_net_annual_wage = 0;

        if (weeklyHoursText.getText().toString().isEmpty() ||
                hourlyWageText.getText().toString().isEmpty() ||
                annualWageText.getText().toString().isEmpty()) {
            throw new IllegalArgumentException("Please fill in all fields.");
        }

        if (isGuest) {
            Job job = new Job();
            job.setHours(hours);
            job.setHour_Wage(hourlyWage);
            job.setAnnual_Wage(annual_wage);
            //Log.i("Annual Wage Calculation", "Converted Province: " + province);
            res_net_annual_wage = Calculate.annual_wage(job, province);
        } else {
            String url = urlText.getText().toString();

            AccessJobs job = new AccessJobs();
            res_net_annual_wage = job.net_annual_wage(url, province);
        }

        return res_net_annual_wage;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
