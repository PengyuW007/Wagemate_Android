package pengyuw007.wagemate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pengyuw007.wagemate.R;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;
import pengyuw007.wagemate.persistence.stub.DataAccessStub;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,pwd;
    private String nameGet, pwdGet;
    private Button login,guestLogin;
    private TextView createNewAcc,forgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        dataReceived(intent,v);
    }

    private void initUI() {
        /*** Edit Text ***/
        name = (EditText) findViewById(R.id.UserName);
        pwd = (EditText) findViewById(R.id.Password);


        /*** Login Button ***/
        login = findViewById(R.id.LoginButton);
        guestLogin = findViewById(R.id.GuestLoginButton);

        /*** Additional Buttons ***/
        createNewAcc = findViewById(R.id.Create_new_user);
        forgetPwd = findViewById(R.id.Forgot_password);

        name.setOnClickListener(this);
        pwd.setOnClickListener(this);
        login.setOnClickListener(this);
        guestLogin.setOnClickListener(this);
        createNewAcc.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }//end initUI

    private void dataReceived(Intent intent, View v){
        nameGet = name.getText().toString(); //String
        pwdGet = pwd.getText().toString(); //String
        String[] user_info = new String[2];
        String information=null;

        if (v.getId() == R.id.LoginButton||v.getId()==R.id.GuestLoginButton){
            if(v.getId() == R.id.LoginButton){
                userRetrieve(nameGet,pwdGet);// need to be reset
            } else if (v.getId() == R.id.GuestLoginButton) {
                user_info[0] = "Guest"; //User name
                user_info[1] = "Guest0000"; //User SIN
                information = user_info[0]+"\t\t\t\t\t"+user_info[1];
            }
            intent.putExtra("User information",information);
            intent.setClass(getApplicationContext(),MainActivity.class);
        } else if(v.getId()==R.id.Create_new_user){
            //intent.setClass(getApplicationContext(),MainActivity.class);
        } else if (v.getId()==R.id.Forgot_password) {
            
        }

        startActivity(intent);
    }

    private User userRetrieve(String nameGet,String pwdGet){
        User user;
        IPersistenceAccess dataAccess = new DataAccessStub();
        dataAccess.open("Stub");

        user = dataAccess.getUserByName(nameGet);
        if(user!=null){
            boolean isMatch = user.getPWD().equals(pwdGet);
            if(!isMatch){
                user = null; // do -while loop, until name and pwd matches
            }
        }

        return user;
    }
}
