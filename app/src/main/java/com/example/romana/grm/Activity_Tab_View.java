package com.example.romana.grm;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Tab_View extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{
    public ViewPager viewPager;
    public Adaptor_Page adapter;
    private  ArrayList<Item> Selected_food;
    private SearchView search;
   private String MSG;
    private TextView tv;
    private SearchManager searchManager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        search = (SearchView) findViewById(R.id.search_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        tv  = (TextView) toolbar.findViewById(R.id.recipe_list);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        MSG = getIntent().getStringExtra("Activity");
        setTabLayout();

    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        int t = viewPager.getCurrentItem();
        Fragment_Tab_List currentTab = (Fragment_Tab_List) adapter.instantiateItem(viewPager,t);
        currentTab.Filter_Data(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        int t = viewPager.getCurrentItem();
        Fragment_Tab_List currentTab = (Fragment_Tab_List) adapter.instantiateItem(viewPager,t);
        currentTab.Filter_Data(newText);
        return false;
    }
    @Override
    public boolean onClose() {
        int t = viewPager.getCurrentItem();
        Fragment_Tab_List currentTab = (Fragment_Tab_List) adapter.instantiateItem(viewPager,t);
        currentTab.Filter_Data("");
        return false;
    }
    private void setSearchView ()
    {
        searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
    }
    private void setTabLayout()
    {

        if(MSG.equals("Add_Food"))
        {
            tv.setText("ADD FOOD");
            setSearchView();
            Selected_food = new ArrayList<Item>();
            String m = getIntent().getStringExtra("MealPlan");
            if(m!=null)
            {
                tabLayout.addTab(tabLayout.newTab().setText("Meal Plans"));
            }
            int  size = Data.Recipe_Tabs.length;
            for(int i = 0 ; i<size;i++ ) {
                tabLayout.addTab(tabLayout.newTab().setText(Data.Recipe_Tabs[i]));
            }
            for(GroupRow r : Data.Ingredients ) {
                tabLayout.addTab(tabLayout.newTab().setText(r.getName()));
            }
            adapter = new Adaptor_Page(getSupportFragmentManager(), tabLayout.getTabCount(), Selected_food,MSG);
        }
        else if(MSG.equals("Recipe_Lists"))
        {
            tv.setText("RECIPE LIST");
            setSearchView();
            tabLayout.getLayoutParams().width = TabLayout.LayoutParams.MATCH_PARENT;
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            int  size = Data.Recipe_Tabs.length;
            for(int i = 0 ; i<size;i++ ) {
                tabLayout.addTab(tabLayout.newTab().setText(Data.Recipe_Tabs[i]));
            }
            adapter = new Adaptor_Page (getSupportFragmentManager(), tabLayout.getTabCount(),MSG);
        }
        else if(MSG.equals("Recipe_Details"))
        {
            tv.setText("RECIPE DETAILS");
            Recipe r = (Recipe) getIntent().getSerializableExtra("Recipe");
            search.setVisibility(View.GONE);
            tabLayout.getLayoutParams().width = TabLayout.LayoutParams.MATCH_PARENT;
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            int  size = Data.Recipe_Details_Tabs.length;
            for(int i = 0 ; i<size;i++ ) {
                tabLayout.addTab(tabLayout.newTab().setText(Data.Recipe_Details_Tabs[i]));
            }
            adapter = new Adaptor_Page (getSupportFragmentManager(),tabLayout.getTabCount(),MSG,r);
        }
        else if(MSG.equals("Add_Ingredient"))
        {
            tv.setText("ADD INGREDIENTS");
            setSearchView();
            Selected_food = new ArrayList<Item>();
            for(GroupRow r : Data.Ingredients ) {
                tabLayout.addTab(tabLayout.newTab().setText(r.getName()));
            }
            adapter = new Adaptor_Page(getSupportFragmentManager(), tabLayout.getTabCount(), Selected_food,MSG);
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                int p = tab.getPosition();
                if(p!=7) {
                    if (!MSG.equals("Recipe_Details")) {
                        String s = search.getQuery().toString();
                        onQueryTextChange(s);
                    } else {

                        int t = viewPager.getCurrentItem();
                        if (t == 1) {
                           // Fragment_Tab_List currentTab = (Fragment_Tab_List) adapter.instantiateItem(viewPager, t);
                            //Fragment_Recipe_Overview overview = (Fragment_Recipe_Overview) adapter.instantiateItem(viewPager, 0);
                            //double s = Double.parseDouble(overview.ed.getText().toString());
                            //currentTab.updateIngredientQuantity(s);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (!MSG.equals("Recipe_Details"))
                {
                    String s = search.getQuery().toString();
                    onQueryTextChange(s);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


}
