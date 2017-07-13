package com.example.romana.grm;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe extends Food {

    public static final String RECIPE = "RECIPES";
    public static final String R_ID = "ID";
    public static final String R_NAME = "NAME";
    public static final String R_TYPE = "CATEGORY";
    public static final String R_IMAGE = "IMAGE";
    public static final String R_PREPTIME = "PREP_TIME";
    public static final String R_COOKTIME = "COOK_TIME";
    public static final String R_I_NAME = "INGREDIENTS";
    public static final String R_QTY = "QUANTITY";
    public static final String R_UNIT = "UNIT";
    public static final String R_METHOD = "METHOD";
    public static final String R_FAVOURITE = "FAVOURITE";
    public static final String R_USERRECIPE = "USER_RECIPE";


    private int Prep_Time;
    private int Cook_Time;
    private ArrayList<Item> Ingredients;
    private String Method;
    private boolean favourite;

    public int getPrep_Time() {
        return Prep_Time;
    }

    public int getCook_Time() {
        return Cook_Time;
    }

    public ArrayList<Item> getIngredients() {
        return Ingredients;
    }

    public String getMethod() {
        return Method;
    }

    public Recipe(int ID, String name, String type, byte[] image,int prep_Time, int cook_Time, ArrayList<Item> ingredients, String method, boolean favourite) {
        super(ID, name, type, image);
        Prep_Time = prep_Time;
        Cook_Time = cook_Time;
        Ingredients = ingredients;
        Method = method;
        this.favourite = favourite;
    }

   static public  ArrayList<Recipe> getAllRecipes(int ch)
    {
        SQLiteDatabase db = Data.dbHelper.read();
        ArrayList<Recipe> in = new ArrayList<>();
        String query = "";
        switch(ch)
        {
            case 0:
                query = "Select * from RECIPES";
                break;
            case 1:
                query = "Select * from USER_RECIPE";
                break;
        }
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String[]i = cursor.getString(7).split(",");
                String[]q = cursor.getString(8).split(",");
                String[]u = cursor.getString(9).split(",");
                ArrayList<Item> ing = new ArrayList<Item>();
                int l = i.length;
                for (int c = 0 ; c<l;c++) {
                    ing.add(new Item (i[c],Double.parseDouble(q[c]),u[c],"Ingredient"));
                }

                byte [] im = cursor.getBlob(3);
                Recipe r = new Recipe(cursor.getInt(0),cursor.getString(1),cursor.getString(2),im,cursor.getInt(4),cursor.getInt(5),ing,cursor.getString(6),Boolean.parseBoolean(cursor.getString(7)));
                in.add(r);
               cursor.moveToNext();
            }
        }
        return in;

    }

    static public ArrayList<ArrayList<Recipe>>  getAllRecipesLists()
    {
        SQLiteDatabase db = Data.dbHelper.read();
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Recipe> cookbook = new ArrayList<>();
        ArrayList<Recipe> favorite = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from RECIPES", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String[]i = cursor.getString(7).split(",");
                String[]q = cursor.getString(8).split(",");
                String[]u = cursor.getString(9).split(",");
                ArrayList<Item> ing = new ArrayList<Item>();
                int l = i.length;
                for (int c = 0 ; c<l;c++) {
                    ing.add(new Item (i[c],Double.parseDouble(q[c]),u[c],"Ingredient"));
                }
                boolean b = cursor.getString(10).equals("1");
                byte [] im = cursor.getBlob(3);
                Recipe r = new Recipe(cursor.getInt(0),cursor.getString(1),cursor.getString(2),im,cursor.getInt(4),cursor.getInt(5),ing,cursor.getString(6),b);
                if(cursor.getString(11).equals("1"))
                    cookbook.add(r);
                else
                    recipes.add(r);
                if(b)
                    favorite.add(r);
                cursor.moveToNext();
            }
        }
        ArrayList<ArrayList<Recipe>> in = new ArrayList<ArrayList<Recipe>>();
        in.add(recipes);
        in.add(cookbook);
        in.add(favorite);
        return in;

    }

    public void setRecipeDetails(ArrayList<Item> ingredients, String method) {
        Ingredients = ingredients;
        Method = method;
    }
    public boolean areRecipeDetailsPresent()
    {
        if(Ingredients!= null)
        return true;
        return false;
    }
    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }


    public Recipe(String name, int i, String ft, String c, String fact, int prep_Time, int cook_Time, ArrayList<Item> ingredients, String method, boolean favourite) {
        super(name, i, ft, c);
        Prep_Time = prep_Time;
        Cook_Time = cook_Time;
        Ingredients = ingredients;
        Method = method;
        this.favourite = favourite;
    }

    static void addNewRecipe (Recipe r,boolean b)
    {
        
        String ing = "",q="",u="";
        for (Item i:r.getIngredients()) {
            ing = ing + i.getName()+",";
            q = q + i.getQuantity()+",";
            u = u + i.getUnit()+",";
        }
        SQLiteDatabase db = Data.dbHelper.write();

        ContentValues values = new ContentValues();

        values.put(R_NAME, r.getName());
        values.put(R_TYPE, r.getCategory());
        values.put(R_IMAGE, r.getImage());
        values.put (R_PREPTIME, r.Prep_Time);
        values.put ( R_COOKTIME, r.Cook_Time);
        values.put(R_I_NAME, ing);
        values.put(R_QTY, q);
        values.put(R_UNIT, u);
        values.put(R_METHOD,r.getMethod());
        values.put(R_FAVOURITE, 0);
        values.put(R_USERRECIPE,b);
        db.insert(RECIPE, null, values);
        db.close();


    }

    public Recipe(String t, String ft, String c, String fact, int prep_Time, int cook_Time, ArrayList<Item> ingredients, String method, boolean favourite) {
        super(t, ft, c);
        Prep_Time = prep_Time;
        Cook_Time = cook_Time;
        Ingredients = ingredients;
        Method = method;
        this.favourite = favourite;
    }
  /* static public Recipe ModifiedRecipe(Recipe r , ArrayList<Item> addedIngredient)
    {
        ArrayList<Item> newIngredients = new ArrayList<>();
        for (Item i:r.getIngredients()
             ) {
            newIngredients.add(new Item(i.getName(),i.getQuantity(),i.getUnit(),i.Category));
        }
        newIngredients.addAll(addedIngredient);
        Recipe cr  = new Recipe(r.Name,r.Type,r.Category,r.Prep_Time,r.Cook_Time,newIngredients,r.getMethod());
        return cr;
    }*/

}