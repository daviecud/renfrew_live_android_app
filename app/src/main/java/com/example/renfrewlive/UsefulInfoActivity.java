package com.example.renfrewlive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class UsefulInfoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InfoAdapter adapter;

    List<InfoDetails> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        infoList.add(
                new InfoDetails (
                        1,
                        "Braehead Medical Practice",
                        "10 Ferry Road, Renfrew, PA4 8RU",
                        "0141 207 7480",
                        "NHS Scotland GP",
                        R.drawable.nhsscotland));

        infoList.add(
                new InfoDetails(
                        1,
                        "Renfrew Police Station",
                        "14 Station Road, Renfrew, PA4 8RP",
                        "0141 532 6100",
                        "Police Scotland",
                        R.drawable.policescotland));

        infoList.add(
                new InfoDetails(
                        1,
                        "Renfrew Library",
                        "Paisley Rd, Renfrew, PA4 8LJ",
                        "0300 300 1188",
                        "Public Library",
                        R.drawable.library));

        infoList.add(
                new InfoDetails(
                        1,
                        "Renfrew Victoria Baths",
                        "Inchinnan Rd, Renfrew PA4 8ND",
                        "0300 300 0250",
                        "Public Baths",
                        R.drawable.baths));

        infoList.add(
                        new InfoDetails(
                                1,
                                "Town Hall & Museum",
                                "Renfrew Town Hall and Museum, Hairst St, Renfrew PA4 8PF",
                                "0300 300 1210",
                                "Local Town hall and museum",
                                R.drawable.town_hall));

        adapter = new InfoAdapter(this, infoList);
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
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
