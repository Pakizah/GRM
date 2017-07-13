package com.example.romana.grm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Romana on 12/05/2017.
 */
public class MealPlan  implements Serializable {
    private String Date ;
    private ArrayList<Item> Food_List;
    private ArrayList<String> Notes_List;

    public MealPlan() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Date = day+"-"+(month+1)+"-"+year;
        Food_List = new ArrayList<Item>();
        Notes_List = new ArrayList<String>();
    }
public MealPlan (MealPlan m)    {
    Date = m.Date;
    Food_List = new ArrayList<Item>();
    Notes_List = new ArrayList<String>();
    Food_List.addAll(m.Food_List);
    Notes_List.addAll(m.Notes_List);

}
    public MealPlan (String Date)
    {
        this.Date = Date;
        Food_List = new ArrayList<Item>();
        Notes_List = new ArrayList<String>();
    }

    public ArrayList<String> getNotes_List() {
        return Notes_List;
    }

    public void setNotes_List(ArrayList<String> notes_List) {
        Notes_List = notes_List;
    }

    public ArrayList<Item> getFood_List() {

        return Food_List;
    }

    public void setFood_List(ArrayList<Item> food_List) {
        Food_List = food_List;
    }

    public static MealPlan getMealPlan (String date)
    {
        MealPlan m = Data.Meal_Plan_Table.get(date);
        return m;
    }

    public MealPlan(String date, ArrayList<Item> food_List) {
        Date = date;
        Food_List = new ArrayList<Item>();
        Notes_List = new ArrayList<String>();
       Food_List.addAll(food_List) ;
    }

    public MealPlan(ArrayList<String> notes_List,String date) {
        Date = date;
        Food_List = new ArrayList<Item>();
        Notes_List = new ArrayList<String>();
        Notes_List.addAll(notes_List);
    }

    public static void AddMealPlan(MealPlan m)
    {
        if(m!=null) {
            MealPlan ml = new MealPlan(m);
            Data.Meal_Plan_Table.put(m.Date, ml);
        }
    }

    public void AddMoreFoodItem (ArrayList<Item> items)
    {
        if(items!=null)
        Food_List.addAll(items);

    }

    public void AddMoreNotes (ArrayList<String> notes)
    {
        if(notes!= null)
            Notes_List.addAll(notes);
    }
}
