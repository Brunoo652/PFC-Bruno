package com.afundacion.inazumawiki.launcher;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.myaplication.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        int delayMillis = 2000; // 2 seconds (you can adjust the time as needed)

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent to open the MainActivity
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the current activity (LauncherActivity)
            }
        }, delayMillis);
    }
}
