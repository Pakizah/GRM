package com.example.romana.grm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pakizah Fatima on 1/8/2017.
 */
public class TileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile);
        Button Tile1 = (Button)findViewById(R.id.tile1);
        Tile1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Activity_Tab_View.class);
                i.putExtra("Activity","Recipe_Lists");
                startActivity(i);
            }
        });

        Button Tile2 = (Button)findViewById(R.id.tile2);
        Tile2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Activity_Meal_Plan.class);
                startActivity(i);
            }
        });
        Button Tile3 = (Button)findViewById(R.id.tile3);
        Tile3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Activity_Grocery.class);
                startActivity(i);
            }
        });
        Button Tile4 = (Button)findViewById(R.id.tile4);
        Tile4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Activity_Ingredient_Glossary.class);
                startActivity(i);
            }
        });
        Button Tile5 = (Button)findViewById(R.id.tile5);
        Tile5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(),Activity_Submit_Recipe.class);
                startActivity(i);
            }
        });
        Button Tile6 = (Button)findViewById(R.id.tile6);
        Tile6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
           //     Intent i = new Intent(getApplicationContext(),Activity_Ingredient_Glossary.class);
             //   startActivity(i);
            }
        });

    }
}