package com.example.romana.grm;

import java.util.ArrayList;

/**
 * Created by Wahlah on 3/23/2017.
 */
public class GroupRow {
    private String name;
    private ArrayList<Ingredient> childList = new ArrayList<Ingredient>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<Ingredient> childList) {
        this.childList = childList;
    }

    public GroupRow(String name, ArrayList<Ingredient> childList) {

        super();
        this.name = name;
        this.childList = childList;
    }
}
