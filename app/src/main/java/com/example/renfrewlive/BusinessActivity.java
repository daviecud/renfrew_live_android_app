package com.example.renfrewlive;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selection_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //this is to handle selection of an item in the menu
        switch (item.getItemId()) {
            case R.id.Local_Services:
                Intent intent = new Intent(this, UsefulInfoActivity.class);
                startActivity(intent);
                return true;

            case R.id.Local_Business:
                Intent bus_intent = new Intent(this, BusinessActivity.class);
                startActivity(bus_intent);
                return true;

            case R.id.Lost_Found:
                Intent lostIntent = new Intent(this, LostFoundActivity.class);
                startActivity(lostIntent);
                return true;
            case R.id.Scratch:
                Intent scratchIntent = new Intent(this, ScratchActivity.class);
                startActivity(scratchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
