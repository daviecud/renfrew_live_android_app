package com.example.renfrewlive;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class LostFoundActivity extends AppCompatActivity {

    String arrayCircle[] = {"Home", "Services", "Businesses", "Crime", "Lost/Found"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);

        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#FF8243"), R.drawable.ic_assignment_white_24dp, R.drawable.ic_remove_white_24dp)
                .addSubMenu(Color.parseColor("#AF3AF8"), R.drawable.ic_local_library_white_24dp)
                .addSubMenu(Color.parseColor("#F10B0B"), R.drawable.ic_shopping_basket_white_24dp)
                .addSubMenu(Color.parseColor("#68C4FA"), R.drawable.renfrew_arms)
                .addSubMenu(Color.parseColor("#F3FA68"), R.drawable.lost)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(LostFoundActivity.this, "You selected " + arrayCircle[index], Toast.LENGTH_SHORT).show();

                            switch(index) {
                                case 0: startActivity(new Intent(LostFoundActivity.this, UsefulInfoActivity.class));break;
                                case 1: startActivity(new Intent(LostFoundActivity.this, BusinessActivity.class));break;
                                case 2: startActivity(new Intent(LostFoundActivity.this, MainActivity.class));
                                case 3: startActivity(new Intent(LostFoundActivity.this, LostFoundActivity.class));
                            }

                    }
                });



    }


}
