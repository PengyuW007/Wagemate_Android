package pengyuw007.wagemate.presentation;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TextView userInfoView;
    private EditText urlText,positionText;
    private Spinner provincePicker;
    private Button calculate;

    private static final String provinces[] = {"Select the province you located","Alberta",
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
                net_annual_wage();
            }
        });
    }

    private void initUI() {
        String userInfo =getIntent().getStringExtra("User information");
        userInfoView = findViewById(R.id.User_info);
        userInfoView.setText(userInfo);

        provincePicker = (Spinner)findViewById(R.id.Province);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provincePicker.setAdapter(adapter);
        provincePicker.setOnItemSelectedListener(this);

        calculate = findViewById(R.id.calculate);
    }

    private double net_annual_wage(){
        String province = provincePicker.getSelectedItem().toString();
        String url = urlText.getText().toString();

        AccessJobs job = new AccessJobs();

        return job.net_annual_wage(url,province);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
