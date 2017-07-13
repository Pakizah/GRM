package com.example.romana.grm;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Blob;


public class Food implements Serializable {
    public int ID;
    public String Name;
    public  String Type;
    public byte[] Image;
    private int picture;
    public String Category;
    public boolean Modified;


    public Food(String name, String type, String category) {
        this.ID = 0;
        Name = name;
        Type = type;
        Category = category;
    }

    public byte[] getImage() {
        return Image;
    }

    public Food(int ID, String name, String type, byte[] image) {
        this.ID = ID;
        Name = name;
        Type = type;
        Image = image;
        picture = 0;
        Category=null;
        Modified = false;
    }


    public Food(String name ,int i,String category,String type ) {
        Name = name;
        Type = type;
        picture = i;
        Category = category;
    }
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public void setImage(byte[] image) {
        Image = image;
    }



    public int getPicture() {
        return picture;
    }

    public String getCategory() {
        return Category;
    }

    public boolean isModified() {
        return Modified;
    }
}