package androidcustomcalendar.adms.com.calender.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import androidcustomcalendar.adms.com.calender.MainActivity;
import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.global.Setting;

import static androidcustomcalendar.adms.com.calender.MainActivity.THEMES;

public class LaunchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        },200);

    }

    private void setupTheme() {
        int theme = THEMES[Setting.theme];
        setTheme(theme);
    }

}
