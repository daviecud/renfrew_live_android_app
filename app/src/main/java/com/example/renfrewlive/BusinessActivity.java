package com.example.renfrewlive;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference businessRef = db.collection("businesses");

    private BusinessAdapter adapter;

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        Query query = businessRef.orderBy("name", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<BusinessDetails> options = new FirestoreRecyclerOptions.Builder<BusinessDetails>()
                .setQuery(query, BusinessDetails.class)
                .build();

        adapter = new BusinessAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.bus_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    /*To stop the listener when the user has finished with this activity, to help save memory and lag*/
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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
/*  Map<String, Object> business = new HashMap<>();
        business.put("name", "Digital Coding Services");
        business.put("address", "McClue Road, Renfrew, PA4 9BL");
        business.put("telephone", "07590895571");
        business.put("web", "www.dcservices.com");*/

/*
businessList = new ArrayList<>();
*/

//Create a new business with business name, address, tel, website or email


      /*  //Add a new document with a Generated ID
        db.collection("businesses")
                .add(business)
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
*/

/*       businessList.add(
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

        Log.d("businessList", "businessList");*/

//        adapter = new BusinessAdapter(this, businessList);
//        recyclerView.setAdapter(adapter);

/* recyclerView = (RecyclerView) findViewById(R.id.bus_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
