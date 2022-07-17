package net.com.alkhawarizmi.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.com.alkhawarizmi.PaymentScreen;
import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.database.SqlLiteStatements;
import net.com.alkhawarizmi.models.Ad;
import net.com.alkhawarizmi.models.AppUser;
import net.com.alkhawarizmi.models.RecyclerItemClickListener;
import net.com.alkhawarizmi.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class CoursesListActivity extends AppCompatActivity {

    private static final String TAG = "CoursesListActivity";

    RecyclerView recyclerView;
    AdsRvAdapter adsRvAdapter;
    List<Ad> adList;
    List<Ad> ALLList;

    String type = "";

    SqlLiteStatements sqlLiteStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        sqlLiteStatements = new SqlLiteStatements(this);

        type = getIntent().getExtras().getString("type");

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getExtras().getString("title"));

        recyclerView = findViewById(R.id.recyclerView);


        ALLList = new ArrayList<>();
        Ad ad1 = new Ad("tblgcU", "برامج صيفية",
                "دورة", "800 SR", "28-07-2022", R.drawable.cour1);
        ALLList.add(ad1);
        Ad ad2 = new Ad("BdqgZI", "دورات إدارية",
                "دورة", "200 SR", "28-07-2022", R.drawable.cour2);
        ALLList.add(ad2);

        Ad ad3 = new Ad("RpDOYP", "دورات إدارية",
                "دورة", "220 SR", "19-08-2022", R.drawable.cour3);
        ALLList.add(ad3);
        Ad ad4 = new Ad("iopeOi", "دورات لغة انجليزيه",
                "دورة", "699 SR", "31-05-2022", R.drawable.cour4);
        ALLList.add(ad4);

        Ad ad5 = new Ad("DvmV7s", "دورات لغة انجليزيه",
                "دورة", "6000 SR", "30-06-2022", R.drawable.cour5);
        ALLList.add(ad5);
        Ad ad6 = new Ad("tblgcU", "برامج صيفية",
                "دورة", "800 SR", "28-07-2022", R.drawable.cour1);
        ALLList.add(ad6);
        Ad ad7 = new Ad("vaLdqv", "دورات الحاسب الي",
                "دورة", "2990 SR", "17-07-2022", R.drawable.ads1);
        ALLList.add(ad7);
        Ad ad8 = new Ad("vxD71b", "دورات الحاسب الي",
                "دورة", "1200 SR", "28-07-2022", R.drawable.cour6);
        ALLList.add(ad8);

        setupList();
    }

    private void setupList() {
        adList = new ArrayList<>();

        switch (type) {
            case "man":
                Ad ad1 = new Ad("tblgcU", "برامج صيفية",
                        "دورة", "800 SR", "28-07-2022", R.drawable.cour1);
                adList.add(ad1);
                Ad ad2 = new Ad("BdqgZI", "دورات إدارية",
                        "دورة", "200 SR", "28-07-2022", R.drawable.cour2);
                adList.add(ad2);

                Ad ad3 = new Ad("RpDOYP", "دورات إدارية",
                        "دورة", "220 SR", "19-08-2022", R.drawable.cour3);
                adList.add(ad3);
                break;

            case "eng":
                Ad ad4 = new Ad("iopeOi", "دورات لغة انجليزيه",
                        "دورة", "699 SR", "31-05-2022", R.drawable.cour4);
                adList.add(ad4);

                Ad ad5 = new Ad("DvmV7s", "دورات لغة انجليزيه",
                        "دورة", "6000 SR", "30-06-2022", R.drawable.cour5);
                adList.add(ad5);
                break;

            case "prog":
                Ad ad6 = new Ad("tblgcU", "برامج صيفية",
                        "دورة", "800 SR", "28-07-2022", R.drawable.cour1);
                adList.add(ad6);
                break;

            case "pc":
                Ad ad7 = new Ad("vaLdqv", "دورات الحاسب الي",
                        "دورة", "2990 SR", "17-07-2022", R.drawable.ads1);
                adList.add(ad7);
                Ad ad8 = new Ad("vxD71b", "دورات الحاسب الي",
                        "دورة", "1200 SR", "28-07-2022", R.drawable.cour6);
                adList.add(ad8);
                break;

            case "registed":
                buildList();
                break;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adsRvAdapter = new AdsRvAdapter(adList);
        recyclerView.setAdapter(adsRvAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (!type.equals("registed")) {
                            AppUser user = new CacheHandler(CoursesListActivity.this).loadCurrentUser();
                            if (sqlLiteStatements.courseValidation(String.valueOf(user.getId()),
                                    adList.get(position).getCourseNo())) {
                                Toast.makeText(CoursesListActivity.this,
                                        "انت مشترك في هذه الدورة بالفعل",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(CoursesListActivity.this, PaymentScreen.class);
                                intent.putExtra("userId", String.valueOf(user.getId()));
                                intent.putExtra("courseId", adList.get(position).getCourseNo());
                                intent.putExtra("total", adList.get(position).getPrice());
                                startActivity(intent);
                            }
                        }
                    }
                }));

    }

    private void buildList() {
        adList = new ArrayList<>();
        List<Reservation> reservations = sqlLiteStatements.getReservationList(
                new CacheHandler(CoursesListActivity.this)
                        .loadCurrentUser().getId());

        for (int r = 0; r < reservations.size(); r++) {
            for (int a = 0; a < ALLList.size(); a++) {
                if (reservations.get(r).getCourseId().equals(ALLList.get(a).getCourseNo())) {
                    adList.add(ALLList.get(a));
                    break;
                }
            }
        }
    }
}
