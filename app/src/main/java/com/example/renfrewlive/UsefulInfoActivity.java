package com.example.renfrewlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
                                R.drawable.town_hall
                ));

        adapter = new InfoAdapter(this, infoList);
        recyclerView.setAdapter(adapter);
    }
}
