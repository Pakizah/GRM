package com.example.romana.grm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Ingredient extends Food  {

    private String Urdu_Name;
    public String[] Unit;
    private String Nutritional_Facts;

    public String[] getUnit() {
        return Unit;
    }

    public void setUnit(String[] unit) {
        this.Unit = unit;
    }

    public String getNutritional_Facts() {
        return Nutritional_Facts;
    }

    public void setNutritional_Facts(String nutritional_Facts) {
        Nutritional_Facts = nutritional_Facts;
    }

    public Ingredient( String name, String type, String urdu_Name) {
        super(name, type,"Ingredient");
        Urdu_Name = urdu_Name;
    }

    public Ingredient(String name, String type, byte[] image,String urdu_Name, String[] unit, String nutritional_Facts) {
        super(0, name, type, image);
        Nutritional_Facts = nutritional_Facts;
        Unit = unit;
        Urdu_Name = urdu_Name;
    }
    static public ArrayList<Ingredient> getIngredients ()
    {
        SQLiteDatabase db = Data.dbHelper.read();
        ArrayList<Ingredient> in = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select *  from Ingredient", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String[]u = cursor.getString(4).split(",");
                byte [] im = cursor.getBlob(2);
               // Bitmap img = BitmapFactory.decodeByteArray(im,0,im.length);
                Ingredient i = new Ingredient(cursor.getString(0),cursor.getString(1),im,cursor.getString(3),u,cursor.getString(5));
                in.add(i);
                cursor.moveToNext();
            }
        }
        return in;
    }

    static public  ArrayList<String> getTypesOfIngredients ()
    {
        SQLiteDatabase db = Data.dbHelper.read();
        ArrayList<String> in = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select DISTINCT(TYPE)  from Ingredient", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
               in.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        return in;
    }
    static public ArrayList<Ingredient> getAllTypeIngredients (String Type)
    {
        SQLiteDatabase db = Data.dbHelper.read();
        ArrayList<Ingredient> in = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from Ingredient where TYPE = \""+Type+"\"", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String[]u = cursor.getString(4).split(",");
                byte [] im = cursor.getBlob(2);
                // Bitmap img = BitmapFactory.decodeByteArray(im,0,im.length);
                Ingredient i = new Ingredient(cursor.getString(0),cursor.getString(1),im,cursor.getString(3),u,cursor.getString(5));
                in.add(i);
                cursor.moveToNext();
            }
        }
        return in;
    }
    static public Ingredient getIngredient (String NAME)
    {
        SQLiteDatabase db = Data.dbHelper.read();
        Cursor cursor = db.rawQuery("Select * from Ingredient where NAME = \""+NAME+"\"", null);
        if (cursor.moveToFirst()) {
                String[]u = cursor.getString(4).split(",");
                byte [] im = cursor.getBlob(2);
                // Bitmap img = BitmapFactory.decodeByteArray(im,0,im.length);
                Ingredient i = new Ingredient(cursor.getString(0),cursor.getString(1),im,cursor.getString(3),u,cursor.getString(5));
                return i;
        }
        return null;
    }

    static public ArrayList<String> getIngridientUnits (String NAME)
    {
        SQLiteDatabase db = Data.dbHelper.read();
        Cursor cursor = db.rawQuery("Select UNIT from Ingredient where NAME = \""+NAME+"\"", null);
        if (cursor.moveToFirst()) {
            String[]u = cursor.getString(0).split(",");
            ArrayList<String> i  = new ArrayList<String>();
            for (String s: u) {
                i.add(s);
            }
             return i;
        }
        return null;
    }

    public String getUrdu_Name() {
        return Urdu_Name;
    }
}
