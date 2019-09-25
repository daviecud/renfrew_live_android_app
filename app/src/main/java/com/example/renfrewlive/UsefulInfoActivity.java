package com.example.renfrewlive;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.renfrewlive.R.id.Local_Services;

public class UsefulInfoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InfoAdapter adapter;

    List<InfoDetails> infoList;

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<String, Object> service = new HashMap<>();
        service.put("name", "Renfrew High School");
        service.put("address", "Haining Road");
        service.put("town", "Renfrew");
        service.put("postcode", "PA4 0AN");
        service.put("telephone", "0330 300 1414");
        service.put("web", "www.renfrewhighschool.com");

        db.collection("Local_Services")
                .add(service)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                    }
                });

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
            case R.id.Home:
                Intent home_intent = new Intent(this, MainActivity.class);
                startActivity(home_intent);
                return true;
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
            case R.id.Notes:
                Intent notesIntent = new Intent(this, DataActivity.class);
                startActivity(notesIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
