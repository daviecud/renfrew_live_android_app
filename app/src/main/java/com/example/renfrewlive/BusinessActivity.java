package com.example.renfrewlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BusinessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BusinessAdapter adapter;

    List<BusinessDetails> businessList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        businessList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.bus_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        businessList.add(
                new BusinessDetails(
                    1,
                    "AGM Aerials",
                    "Lang Avenue, Renfrew, PA4 0ZZ",
                    "0759 000 2144",
                    "www.agm-aerials.com",
                    "Services include: Aerial/Satellite, CCTV, Alarms, TV Mounting",
                        R.drawable.agm
                ));

        businessList.add(
                new BusinessDetails(
                        1,
                        "Hugh McWhinnie Butchers",
                        "12 Fulbar Street, Renfrew, PA4 8PE",
                        "0141 886 2509",
                        "www.mcwhinnie-butchers.com",
                        "Traditional Family Butchers",
                        R.drawable.mcwhinnie
                ));

        businessList.add(
                new BusinessDetails(
                        1,
                        "The Black Bull",
                        "18-20 Canal St, Renfrew PA4 8QD",
                        "0141 561 1066",
                        "www.black-bull-renfrew.co.uk",
                        "Traditional Local Pub with Entertainment including Karaoke",
                        R.drawable.bull
                ));

    Log.d("businessList", "businessList");

        adapter = new BusinessAdapter(this, businessList);
        recyclerView.setAdapter(adapter);
    }
}
