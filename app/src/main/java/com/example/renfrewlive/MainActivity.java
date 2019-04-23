package com.example.renfrewlive;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView date, time, t1_temp, t2_conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        t1_temp = findViewById(R.id.temp);
        t2_conditions = findViewById(R.id.conditions);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String currentTime = "Time: " + format.format(calendar.getTime());

        time.setText(currentTime);

        date.setText(currentDate);

        find_weather();
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















