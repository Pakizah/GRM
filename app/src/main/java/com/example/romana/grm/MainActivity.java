package com.example.romana.grm;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Data.getRecipes();
        Data.dbHelper = DatabaseAccess.getInstance(this);
        imageView=(ImageView)findViewById(R.id.imsplash);
        imageView.setBackgroundResource(R.drawable.bgsplash);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        Thread timerThread = new Thread(){
            public void run(){
                try
                {
                    ArrayList<String> types = Ingredient.getTypesOfIngredients();
                    for (String s:types) {
                        ArrayList<Ingredient> childList = Ingredient.getAllTypeIngredients(s);
                        GroupRow continent = new GroupRow(s,childList);
                        Data.Ingredients.add(continent);
                    }
                    ArrayList<ArrayList<Recipe>> Recipes = Recipe.getAllRecipesLists();
                    Data.Standard_Recipe = Recipes.get(0);
                    Data.Cook_Book_Recipe = Recipes.get(1);
                    Data.Favourite_Recipe = Recipes.get(2);
                    sleep(30);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(MainActivity.this,TileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();

    }
}




