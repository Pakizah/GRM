package com.example.romana.grm;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Activity_Ingredient_Glossary extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<GroupRow> grouptList = new ArrayList<GroupRow>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        context = this;

        //display the list
        displayList();
        //expand all Groups
        expandAll();





        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Ingredient ing = grouptList.get(groupPosition).getChildList().get(childPosition);

                Intent i = new Intent(context, Activity_Ingredient_Details.class);
                i.putExtra("ING", ing);
                //Toast.makeText(getApplicationContext(),n+u,Toast.LENGTH_LONG).show();
                startActivity(i);

                return false;
            }
        });
    }



    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }


  /*  public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
*/

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
    private void displayList() {
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(Activity_Ingredient_Glossary.this, grouptList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);
    }

    private void loadSomeData() {
/*
        ArrayList<ChildRow> childList = new ArrayList<ChildRow>();
        ChildRow child = new ChildRow("Banana","0%","2%",R.drawable
                .bananas,"کیلا");
        childList.add(child);
        child = new ChildRow("Orange","0%","4%",R.drawable.orange,"مالٹا");
        childList.add(child);
        child = new ChildRow("Grapes","0%","2%",R.drawable.grapes,"انگور");
        childList.add(child);

        GroupRow continent = new GroupRow("Fruits",childList);
        grouptList.add(continent);

        childList = new ArrayList<ChildRow>();
        child = new ChildRow("Cauliflower","0%","0%",R.drawable.cauliflower,"گوبھی");
        childList.add(child);
        child = new ChildRow("Potato","0%","0%",R.drawable.potato,"آلو");
        childList.add(child);
        child = new ChildRow("Lady Finger","0%","14%",R.drawable.ladyfinger,"بھنڈی");
        childList.add(child);

        continent = new GroupRow("Vegetables",childList);
        grouptList.add(continent);
        */
      /* ArrayList<String> types = Ingredient.getTypesOfIngredients();
        for (String s:types) {
            ArrayList<Ingredient> childList = Ingredient.getAllTypeIngredients(s);
            GroupRow continent = new GroupRow(s,childList);
            grouptList.add(continent);
        }*/
        grouptList = Data.Ingredients;


    }

}
