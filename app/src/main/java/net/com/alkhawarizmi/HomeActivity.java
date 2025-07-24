package net.com.alkhawarizmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.database.SqlLiteStatements;
import net.com.alkhawarizmi.list.AdsActivity;
import net.com.alkhawarizmi.list.AdsRvAdapter;
import net.com.alkhawarizmi.list.CoursesActivity;
import net.com.alkhawarizmi.list.CoursesListActivity;
import net.com.alkhawarizmi.login.LoginActivity;
import net.com.alkhawarizmi.models.Ad;
import net.com.alkhawarizmi.models.AppUser;
import net.com.alkhawarizmi.models.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    HomevAdapter adsRvAdapter;
    SqlLiteStatements sqlLiteStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sqlLiteStatements = new SqlLiteStatements(this);

        ImageView logout = findViewById(R.id.logout);
        logout.setOnClickListener(this::onClick);

//        CardView ads = findViewById(R.id.ads);
//        ads.setOnClickListener(this::onClick);

        LinearLayout courses = findViewById(R.id.courses);
        courses.setOnClickListener(this::onClick);

        LinearLayout kho = findViewById(R.id.kho);
        kho.setOnClickListener(this::onClick);

        LinearLayout contact = findViewById(R.id.contact);
        contact.setOnClickListener(this::onClick);

        LinearLayout profile = findViewById(R.id.profile);
        profile.setOnClickListener(this::onClick);

        List<Ad> adList = new ArrayList<>();
        Ad ad1 = new Ad("vaLdqv", "دورة اخصائي مايكروسوفت أوفيس \n MOS",
                "دورة", "2990 SR", "17 \n July \n 2022", R.drawable.cour1);
        adList.add(ad1);
        Ad ad2 = new Ad("tblgcU", "برنامج الخوارزمي الصغير الصيفي لعام 1439هـ",
                "دورة", "800 SR", "28 \n July \n 2022", R.drawable.cour2);
        adList.add(ad2);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adsRvAdapter = new HomevAdapter(adList);
        recyclerView.setAdapter(adsRvAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                            AppUser user = new CacheHandler(HomeActivity.this).loadCurrentUser();
                            if (sqlLiteStatements.courseValidation(String.valueOf(user.getId()),
                                    adList.get(position).getCourseNo())) {
                                Toast.makeText(HomeActivity.this,
                                        "انت مشترك في هذه الدورة بالفعل",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(HomeActivity.this, PaymentScreen.class);
                                intent.putExtra("userId", String.valueOf(user.getId()));
                                intent.putExtra("courseId", adList.get(position).getCourseNo());
                                intent.putExtra("total", adList.get(position).getPrice());
                                startActivity(intent);
                            }
                    }
                }));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.logout:
                new CacheHandler(HomeActivity.this).clearData();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;

//            case R.id.ads:
//                startActivity(new Intent(HomeActivity.this, AdsActivity.class));
//                break;

            case R.id.courses:
                startActivity(new Intent(HomeActivity.this, CoursesActivity.class));
                break;

            case R.id.kho:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            case R.id.contact:
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                break;

            case R.id.profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;
        }

    }
}
