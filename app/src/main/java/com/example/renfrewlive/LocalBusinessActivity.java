package com.example.renfrewlive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class LocalBusinessActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference businessRef = db.collection("businesses");

    private LocalBusinessAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_business);

        setupRecyclerView();
    }

    private void setupRecyclerView() {

        Query query = businessRef.orderBy("name", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<LocalBusinessDetails> options = new FirestoreRecyclerOptions.Builder<LocalBusinessDetails>()
                .setQuery(query, LocalBusinessDetails.class)
                .build();

        adapter = new LocalBusinessAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    /*To start the listener when called on*/
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


}
