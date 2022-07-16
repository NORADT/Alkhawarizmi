package net.com.alkhawarizmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.list.AdsActivity;
import net.com.alkhawarizmi.list.CoursesActivity;
import net.com.alkhawarizmi.login.LoginActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView logout = findViewById(R.id.logout);
        logout.setOnClickListener(this::onClick);

        CardView ads = findViewById(R.id.ads);
        ads.setOnClickListener(this::onClick);

        CardView courses = findViewById(R.id.courses);
        courses.setOnClickListener(this::onClick);

        CardView kho = findViewById(R.id.kho);
        kho.setOnClickListener(this::onClick);

        CardView contact = findViewById(R.id.contact);
        contact.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.logout:
                new CacheHandler(HomeActivity.this).clearData();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.ads:
                startActivity(new Intent(HomeActivity.this, AdsActivity.class));
                break;

            case R.id.courses:
                startActivity(new Intent(HomeActivity.this, CoursesActivity.class));
                break;

            case R.id.kho:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            case R.id.contact:
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                break;
        }

    }
}
