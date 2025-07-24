package net.com.alkhawarizmi.login;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import net.com.alkhawarizmi.HomeActivity;
import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.database.CacheHandler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CacheHandler cacheHandler = new CacheHandler(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Class activity;
                if (cacheHandler.isLoggedIn()) {
                    activity = HomeActivity.class;
                } else
                    activity = LoginActivity.class;

                startActivity(new Intent(SplashActivity.this, activity));
                finish();

            }
        }, 2000);
    }

}
