package com.example.renfrewlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Local_BusinessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Local_Business_Adapter adapter;

    List<Local_Business> local_businessList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__business);

        local_businessList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview); //might need to change this
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        local_businessList.add(
                new Local_Business(
                        1,
                        "Digital Coding Services",
                        "Renfrewshire",
                        "07590895771",
                        "www.digital-coding.com",
                        "All things coding -  Websites, Database creation, Android/IOS apps",
                        R.drawable.renfrew_arms));

        local_businessList.add(
                new Local_Business(
                        1,
                        "AGM Aerials",
                        "Lang Avenue, Renfrew, PA4 0ZZ",
                        "07827972234",
                        "www.agmaerials.com",
                        "All Aerial/Satellite work carried out, CCTV, Alarms, TV Mounting",
                        R.drawable.agm));

        local_businessList.add(
                new Local_Business(
                        1,
                        "Hugh McWhinnie Butchers",
                        "12 Fulbar Street, Renfrew, PA4 8PE",
                        "0141 886 2509",
                        "www.butchers.com",
                        "Local family butchers, fresh produce, Home made Steak pie",
                        R.drawable.mcwhinnie));

        local_businessList.add(
                new Local_Business(
                        1,
                        "The Black Bull",
                        "18-20 Canal Street, Renfrew, PA4 8QD",
                        "0141 561 1066",
                        "www.renfrew-bull.com",
                        "Local Pub with Live football & karaoke",
                        R.drawable.bull));

        adapter = new Local_Business_Adapter(this, local_businessList);
        recyclerView.setAdapter(adapter);
    }
}
