package pengyuw007.wagemate.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pengyuw007.wagemate.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,pwd;
    private String nameGet, pwdGet;
    private Button login;
    private TextView createNewAcc,forgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    @Override
    public void onClick(View v) {

    }

    private void initUI() {
        /*** Edit Text ***/
        name = (EditText) findViewById(R.id.UserName);
        pwd = (EditText) findViewById(R.id.Password);


        /*** Login Button ***/
        login = findViewById(R.id.LoginButton);

        /*** Additional Buttons ***/
        createNewAcc = findViewById(R.id.Create_new_user);
        forgetPwd = findViewById(R.id.Forgot_password);

        name.setOnClickListener(this);
        pwd.setOnClickListener(this);
        login.setOnClickListener(this);
        createNewAcc.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }
}
