package net.com.alkhawarizmi.list;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.models.Ad;

import java.util.ArrayList;
import java.util.List;

public class CoursesListActivity extends AppCompatActivity {

    private static final String TAG = "CoursesListActivity";

    RecyclerView recyclerView;
    AdsRvAdapter adsRvAdapter;
    List<Ad> adList;

    String type ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        type = getIntent().getExtras().getString("type");

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getExtras().getString("title"));

        recyclerView = findViewById(R.id.recyclerView);

        setupList();
    }

    private void setupList() {
        adList = new ArrayList<>();

        switch (type){
            case "man":
                Ad ad1 = new Ad("tblgcU","برامج صيفية",
                        "دورة","800 SR","28-07-2022",R.drawable.cour1);
                adList.add(ad1);
                Ad ad2 = new Ad("BdqgZI","دورات إدارية",
                        "دورة","200 SR","28-07-2022",R.drawable.cour2);
                adList.add(ad2);

                Ad ad3 = new Ad("RpDOYP","دورات إدارية",
                        "دورة","220 SR","19-08-2022",R.drawable.cour3);
                adList.add(ad3);
                break;

            case "eng":
                Ad ad4 = new Ad("iopeOi","دورات لغة انجليزيه",
                        "دورة","699 SR","31-05-2022",R.drawable.cour4);
                adList.add(ad4);

                Ad ad5 = new Ad("DvmV7s","دورات لغة انجليزيه",
                        "دورة","6000 SR","30-06-2022",R.drawable.cour5);
                adList.add(ad5);
                break;

            case "prog":
                Ad ad6 = new Ad("tblgcU","برامج صيفية",
                        "دورة","800 SR","28-07-2022",R.drawable.cour1);
                adList.add(ad6);
                break;

            case "pc":
                Ad ad7 = new Ad("vaLdqv","دورات الحاسب الي",
                        "دورة","2990 SR","17-07-2022",R.drawable.ads1);
                adList.add(ad7);
                Ad ad8 = new Ad("vxD71b","دورات الحاسب الي",
                        "دورة","1200 SR","28-07-2022",R.drawable.cour6);
                adList.add(ad8);
                break;

            case "registed":

                break;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adsRvAdapter = new AdsRvAdapter(adList);
        recyclerView.setAdapter(adsRvAdapter);
    }
}
