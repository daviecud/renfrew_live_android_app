package com.example.renfrewlive;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.View;
import android.support.v7.widget.Toolbar;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView date, time, t1_temp, t2_conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        t1_temp = findViewById(R.id.temp);
        t2_conditions = findViewById(R.id.conditions);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String currentTime = "Time: " + format.format(calendar.getTime());

        ImageView imageView = findViewById(R.id.daynightview);

        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        long noon_start = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 30);

        long noon_end = calendar.getTimeInMillis();
        long current_time = System.currentTimeMillis();

        if (current_time > noon_start && current_time < noon_end) {
            imageView.setImageResource(R.drawable.daytownhall);
        } else {
            imageView.setImageResource(R.drawable.townhall);
        }


        time.setText(currentTime);

        date.setText(currentDate);

        find_weather();







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



    private void openBusinessActivity() {
        Intent intent = new Intent(this, BusinessActivity.class);
        startActivity(intent);
    }

    private void openUsefulInfoActivity() {
        Intent intent = new Intent(this, UsefulInfoActivity.class);
        startActivity(intent);
    }

    public void find_weather() {
        String url = "https://api.darksky.net/forecast/27a87fa552b2a741f881b3ee639b994a/55.8753,-4.4401";

        JsonObjectRequest weath = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("currently");

                    String temp = String.valueOf(main_object.getDouble("temperature"));

                    String conditions = main_object.getString("summary");

                    t1_temp.setText(temp);
                    t2_conditions.setText(conditions);

                    double temp_int = Double.parseDouble(temp);
                    double centigrade = (temp_int - 32) / 1.8000;
                    centigrade = Math.round(centigrade);
                    int i = (int) centigrade;
                    t1_temp.setText(String.valueOf(i));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(weath);
    }
}















