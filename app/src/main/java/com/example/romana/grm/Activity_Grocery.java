package com.example.romana.grm;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Grocery extends AppCompatActivity {
   private Adaptor_Grocery_Item adapter;
    private ArrayList<Item> Listdata = new ArrayList<Item>();
    private Context context;
    private ListView list;
    private Button AddMore;
    private Button hide;
    String SelectedDate;
    Intent intent;
    boolean flag ;
    ImageButton OpenCalendar;
    private static final String TAG = "Activity_Grocery";
    Activity_Grocery AG;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        Listdata = new ArrayList<Item>();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SelectedDate =day+"-"+(month+1)+"-"+year;
        ArrayList<Item> j = Data.getDailyGrocery(SelectedDate);
        if(j!=null)
        Listdata.addAll(j);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        list = (ListView) findViewById(R.id.g_list);
        AddMore = (Button) findViewById(R.id.AddMore);
        hide = (Button) findViewById(R.id.eye);
        OpenCalendar = (ImageButton)toolbar.findViewById(R.id.open_calender);
        OpenCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Date dialog = new Dialog_Date(AG);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft,"DatePicker");

            }
        });

        adapter = new Adaptor_Grocery_Item(Activity_Grocery.this, R.layout.adaptor_grocery_item, Listdata);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        AddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Tab_View.class);
                i.putExtra("Activity","Add_Ingredient");
                startActivityForResult(i, 200);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = hide.getText().toString().toLowerCase();
                if(msg.equals("hide marked items"))
                {
                    adapter.hideStrokedItems();
                    hide.setText("UNHIDE MARKED ITEMS");
                }
                else if(msg.equals("unhide marked items"))
                {
                    adapter.unhingeStrokedItems();
                    hide.setText("HIDE MARKED ITEMS");
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            if (requestCode == 200) {
                ArrayList<Item> item = (ArrayList<Item>) data.getSerializableExtra("Add_Food_Item");
                Toast.makeText(context, "Selected: " + item, Toast.LENGTH_SHORT).show();
                Listdata.addAll(item);
                adapter.notifyDataSetChanged();
            }
        }
    }
 /*   private void PopulateData() {
        String[] arr = {"Packet"};
        Ingredient ing = new Ingredient("Butter ","Ingredient" ,"Diary", "2000", arr, "Makan");
        Listdata.add(new Item(ing.getName(), 1.0,"packet","Ingredient"));
        ing = new Ingredient ("Egg ","Ingredient", "Spice","2000",arr,"Anda");
        Listdata.add(new Item(ing.getName(), 1.0, "packet","Ingredient"));
    }

*/
    public Activity_Grocery() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_grocery, container, false);
    }
    public void LoadGrocery(String date)
    {
       // Meal_Plan_date.setText(date);
        ArrayList<Item> m1 =  Data.Daily_Grocery.get(SelectedDate);
        if(m1!=null)
        {
            m1.clear();
        }
        else
        {
            m1 = new ArrayList<Item>();
        }
        m1.addAll(Listdata);
        SelectedDate = date;
        ArrayList<Item> m2 =  Data.Daily_Grocery.get(SelectedDate);
        Listdata.clear();
        if(m2!=null)
        Listdata.addAll(m2);
        adapter.notifyDataSetChanged();
    }
    public void onDestroy() {
        super.onDestroy();
        ArrayList<Item> m1 =  Data.Daily_Grocery.get(SelectedDate);
        if(m1!=null)
        {
            m1.clear();
        }
        else
        {
            m1 = new ArrayList<Item>();
        }
        m1.addAll(Listdata);
        Data.Daily_Grocery.put(SelectedDate, m1);
    }

}
