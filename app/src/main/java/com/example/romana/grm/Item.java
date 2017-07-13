package com.example.romana.grm;

import java.io.Serializable;

public class Item implements Serializable {
    public String Name;
    public double Quantity;
    public String Unit;
    public String Category;
    public boolean purchased;

    public Item(String name, double quantity, String unit, String category) {
        this.Name = name;
        Quantity = quantity;
        Unit = unit;
        Category =category;
        purchased = false;
    }
    public Item copy()
    {
        return new  Item(Name,Quantity, Unit,Category);
    }


    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
