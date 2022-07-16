package net.com.alkhawarizmi.list;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.models.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdsActivity extends AppCompatActivity {

    private static final String TAG = "AdsActivity";

    RecyclerView recyclerView;
    AdsRvAdapter adsRvAdapter;
    List<Ad> adList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        recyclerView = findViewById(R.id.recyclerView);

        setupList();
    }

    private void setupList() {
        adList = new ArrayList<>();
        Ad ad = new Ad("vaLdqv","دورات الحاسب الي",
                "دورة","2990 SR","17-07-2022",R.drawable.ads1);
        adList.add(ad);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adsRvAdapter = new AdsRvAdapter(adList);
        recyclerView.setAdapter(adsRvAdapter);
    }
}
