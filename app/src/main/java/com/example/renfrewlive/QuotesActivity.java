package com.example.renfrewlive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {

    private RecyclerView mMainList;

    private static final String TAG = "FireLog";

    private DocumentReference serviceDocRef = FirebaseFirestore.getInstance().document("Local_Services/2zxSVZOi0vvPihcALEBd");

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("quotes/qJcyMG6y39sbQIautLen");
    private List<QuotesDetails> quotesDetailsList;

    private static final String AUTHOR_KEY = "author";
    private static final String QUOTE_KEY = "quote";

    TextView mQuoteTextView;
    TextView mServicesTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        mQuoteTextView = (TextView) findViewById(R.id.textViewQuote);
//        mServicesTextView = (TextView) findViewById(R.id.main_list);
    }

    public void fetchLocalService(View view) {
        serviceDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String nameText = documentSnapshot.getString("name");
                    String addressText = documentSnapshot.getString("address");
                    String townText = documentSnapshot.getString("town");
                    String postCodeText = documentSnapshot.getString("postcode");
                    String telephoneText = documentSnapshot.getString("telephone");
                    String webText = documentSnapshot.getString("web");
                }
            }
        });
    }

    public void fetchQuote(View view) {
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String quoteText = documentSnapshot.getString(QUOTE_KEY);
                    String authorText = documentSnapshot.getString(AUTHOR_KEY);
                    mQuoteTextView.setText("\"" + quoteText + "\" -- " + authorText);
                }
            }
        });
    }

    public void saveQuote(View view) {
        EditText quoteView = (EditText) findViewById(R.id.editTextQuote);
        EditText authorView = (EditText) findViewById(R.id.editTextAuthor);
        String quoteText = quoteView.getText().toString();
        String authorText = authorView.getText().toString();

        if(quoteText.isEmpty() || authorText.isEmpty()) {return;}
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(QUOTE_KEY, quoteText);
        dataToSave.put(AUTHOR_KEY, authorText);
        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document has been saved Woooohoo");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Oh No Document never saved booo", e);
            }
        });
    }
}
