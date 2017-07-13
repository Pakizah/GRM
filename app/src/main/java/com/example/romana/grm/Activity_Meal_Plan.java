package com.example.romana.grm;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Meal_Plan extends AppCompatActivity {
    //Data Members
    private TextView Meal_Plan_date;
    private Button AddNotes;
    private Button AddFood;
    private ImageButton OpenCalendar;
    private Context context = this;
    private Fragment_Food_List f1;
    private Fragment_Notes_List f2;
    private int ADD_FOOD = 200;
    private String SelectedDate;
    private Activity_Meal_Plan MPA;
    private MealPlan mealPlan;
    private final  ArrayList<Item> Food_List = new ArrayList<Item>();
    private final ArrayList<String> Notes_List = new ArrayList<String>();

    //Functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        MPA = this;
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SelectedDate =day+"-"+(month+1)+"-"+year;
        Meal_Plan_date = (TextView) findViewById(R.id.Presentdate);
        Meal_Plan_date.setText(SelectedDate);
        mealPlan = MealPlan.getMealPlan(SelectedDate);
        if(mealPlan!=null) {
            Food_List.addAll(mealPlan.getFood_List());
            Notes_List.addAll(mealPlan.getNotes_List());
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Meal Plan");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        OpenCalendar = (ImageButton)toolbar.findViewById(R.id.open_calender);
        OpenCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Date dialog = new Dialog_Date(MPA);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft,"DatePicker");

            }
        });

        AddNotes = (Button) findViewById(R.id.NotesBtn);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Food_List", Food_List);
        bundle.putSerializable("Notes_List", (Serializable) Notes_List);
         f1 =  new Fragment_Food_List();
         f2 =  new Fragment_Notes_List();
        f1.setArguments(bundle);
        f2.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment1,f1);
        fragmentTransaction.replace(R.id.fragment2,f2);
        fragmentTransaction.commit();
        AddFood = (Button) findViewById(R.id.FoodBtn);
        AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Tab_View.class);
                i.putExtra("Activity", "Add_Food");
                i.putExtra("MealPlan","MealPlan");
                startActivityForResult(i, ADD_FOOD);
            }
        });
        AddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_edit_note, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText notes = (EditText) promptsView.findViewById(R.id.Notes);
                TextView Ok = (TextView) promptsView.findViewById(R.id.Ok);
                TextView Cancel = (TextView) promptsView.findViewById(R.id.Cancel);
                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();
                Ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = notes.getText().toString();
                        Notes_List.add(text);
                        alertDialog.cancel();
                    }
                });
                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                // show it
                alertDialog.show();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
           if (requestCode == ADD_FOOD) {
               ArrayList<Item> item = (ArrayList<Item>) data.getSerializableExtra("Add_Food_Item");
               if(item!= null) {
                   Toast.makeText(Activity_Meal_Plan.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
                   Food_List.addAll(item);
                   if(mealPlan==null)
                   {
                       mealPlan = new MealPlan(SelectedDate,Food_List);
                       MealPlan.AddMealPlan(mealPlan);
                   }
                   else
                   {
                       mealPlan.AddMoreFoodItem(item);
                   }

                   f1.notifyFragment();
               }
            }
        }
    }


    public   void loadMealPlan (String date)
    {
        Meal_Plan_date.setText(date);
        SelectedDate = date;
        mealPlan = MealPlan.getMealPlan(date);
        Food_List.clear();
        Notes_List.clear();
        if(mealPlan!=null) {
            Notes_List.addAll(mealPlan.getNotes_List());
            Food_List.addAll(mealPlan.getFood_List());
        }
        f1.notifyFragment();
        f2.notifyFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*MealPlan m1 =  Data.Meal_Plan_Table.get(SelectedDate);
        if(m1!=null) {
            m1.getFood_List().clear();
            m1.getNotes_List().clear();
        }
        else
        {
            m1 = new MealPlan();
        }
        m1.getFood_List().addAll(Food_List);
        m1.getNotes_List().addAll(Notes_List);
        Data.Meal_Plan_Table.put(SelectedDate,m1);
        ArrayList<Item> g = Data.getDailyGrocery(m1,SelectedDate);
        Data.Daily_Grocery.put(SelectedDate, g);*/
    }
}
