package net.com.alkhawarizmi.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.login.LoginActivity;

public class CoursesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        CardView man = findViewById(R.id.man);
        man.setOnClickListener(this::onClick);

        CardView eng = findViewById(R.id.eng);
        eng.setOnClickListener(this::onClick);

        CardView prog = findViewById(R.id.prog);
        prog.setOnClickListener(this::onClick);

        CardView pc = findViewById(R.id.pc);
        pc.setOnClickListener(this::onClick);

        CardView registed = findViewById(R.id.registed);
        registed.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        String type= null;
        String title= null;
        switch (view.getId()) {
            case R.id.man:
                type ="man";
                title ="الإدارة";
                break;

            case R.id.eng:
                type ="eng";
                title ="اللغة الإنجليزيه";
                break;

            case R.id.prog:
                type ="prog";
                title ="الدبلوم والبرامج";
                break;

            case R.id.pc:
                type ="pc";
                title ="الحاسب الالي";
                break;

            case R.id.registed:
                type ="registed";
                title ="الدورات المسجلة";
                break;
        }
        Intent i = new Intent(CoursesActivity.this, CoursesListActivity.class);
        i.putExtra("type",type);
        i.putExtra("title",title);
        startActivity(i);

    }
}
