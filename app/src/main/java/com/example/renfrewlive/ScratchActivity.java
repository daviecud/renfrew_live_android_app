package com.example.renfrewlive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ScratchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ScratchImageView scratchImageView = new ScratchImageView(this);

        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                //on reveal
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percentage) {
                //on image percent reveal
            }
        });
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
