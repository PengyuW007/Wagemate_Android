package pengyuw007.wagemate.presentation;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pengyuw007.wagemate.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // TODO: Your application init goes here.
            Intent mInHome = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(mInHome);
            overridePendingTransition(R.transition.fade_in,R.transition.fade_out);
        }, 3000);
    }
}