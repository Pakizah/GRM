package com.example.romana.grm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Namra on 1/6/2017.
 */

public class Adaptor_Page extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private String MSG;
    ArrayList<Item> Selected_food;
    private Fragment[] fragmentsArray;
    private Recipe obj ;
    static public ArrayList <ArrayList<Food>> add_food_item;

    public Adaptor_Page(FragmentManager fm, int NumOfTabs,String msg) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        MSG = msg;
        fragmentsArray = new Fragment[mNumOfTabs];
        if(add_food_item== null)
            setAddFoodList ();
    }

    public Adaptor_Page(FragmentManager fm, int NumOfTabs,String msg,Recipe r) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        MSG = msg;
        obj = r;
        fragmentsArray = new Fragment[mNumOfTabs];
        if(add_food_item== null)
            setAddFoodList ();
    }
    public Adaptor_Page(FragmentManager fm, int NumOfTabs, ArrayList<Item> array, String msg) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        Selected_food = array;
        MSG = msg;
        if(add_food_item== null)
            setAddFoodList ();
        fragmentsArray = new Fragment_Tab_List[mNumOfTabs];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //if(fragmentsArray[position]!=null)
          //  return fragmentsArray[position];
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentsArray[position] = fragment;
        return fragment;
    }
    @Override

    public Fragment getItem(int position) {
      //  if(fragmentsArray[position]!=null)
        //   return fragmentsArray[position];

        Bundle bundle = new Bundle();
        if(MSG.equals("Recipe_Details")){
            switch (position)
            {
            case 0:
                Fragment_Recipe_Overview tab1 = new Fragment_Recipe_Overview();
                bundle.putByteArray("Recipe_img", obj.getImage());
                bundle.putInt("prep",obj.getPrep_Time());
                bundle.putInt("cook",obj.getCook_Time());
                tab1.setArguments(bundle);
                fragmentsArray[position] =  tab1;
                return tab1;
            case 1:
                Fragment_Tab_List tab2 = new Fragment_Tab_List();
                bundle.putString("Msg", "Ingredient_List");
                bundle.putSerializable("List", obj.getIngredients());
                tab2.setArguments(bundle);
                fragmentsArray[position] =  tab2;
                return tab2;
            case 2:
                Fragment_Recipe_Method tab3 = new Fragment_Recipe_Method();
                bundle.putString("Method", obj.getMethod());
                tab3.setArguments(bundle);
                fragmentsArray[position] =  tab3;
                return tab3;
            }

        }

        else if(MSG.equals("Recipe_Lists"))
        {
            bundle.putString("Msg", "RecipeTab_" + position);
            setRecipeListsBundle(bundle,position);

        }
        else if (MSG.equals("Add_Food"))
        {
            bundle.putString("Msg", "AddFoodTab_" + position);
            bundle.putSerializable("List", add_food_item.get(position));
            bundle.putSerializable("Selected_List", Selected_food);
        }
        else if (MSG.equals("Add_Ingredient"))
        {
            bundle.putString("Msg", "AddIngredientTab_" + position);
            bundle.putSerializable("Selected_List", Selected_food);

        }

        Fragment_Tab_List CurrentTab = new Fragment_Tab_List();
        CurrentTab.setArguments(bundle);
        fragmentsArray[position] =  CurrentTab;
        return CurrentTab;
    }
    //@Override
  //  public void destroyItem(ViewGroup container, int position, Object object) {}
    @Override
  public int getItemPosition(Object object) {return POSITION_NONE;}
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    private void setRecipeListsBundle (Bundle b,int p)
    {
        switch(p)
        {
            case 0:
                b.putSerializable("List", Data.Standard_Recipe);
                break;
            case 1:
                b.putSerializable("List", Data.Cook_Book_Recipe);
                break;
            case 2:
                b.putSerializable("List", Data.Favourite_Recipe);
                break;
        }

    }

    private void setAddFoodBundle (Bundle b , int p){}
    private void setAddFoodList ()
    {
        add_food_item  = new ArrayList<ArrayList<Food>>();
        add_food_item.add(new ArrayList<Food>());
        ArrayList<Food> f = new ArrayList<Food>();
        f.addAll(Data.Standard_Recipe);
        add_food_item.add(f);
        f = new ArrayList<Food>();
        f.addAll(Data.Cook_Book_Recipe);
        add_food_item.add(f);
        f = new ArrayList<Food>();
        f.addAll(Data.Favourite_Recipe);
        add_food_item.add(f);
        for (GroupRow r:Data.Ingredients) {
            f = new ArrayList<Food>();
            f.addAll(r.getChildList());
            add_food_item.add(f);
        }

    }

    private void setAddIngredientBundle (Bundle b , int p)
    {

        switch(p)
        {
            /*
            case 0:
                b.putSerializable("List", Data.Fruits);
                break;
            case 1:
                b.putSerializable("List", Data.Vegetables);
                break;
            case 2:
                b.putSerializable("List",Data.Dairy);
                break;
            case 3:
                b.putSerializable("List",Data.Spice);
                break;
*/
        }
    }

    private void setIngredientBundle (Bundle b, int p )
    {
        ArrayList l = new ArrayList<Item>();
        String[] arr = {"1 kg","0.5 kg"};
        for(int i = 0; i < 10; i++)
        {
            Item item = new Item("Red Chilli",2.0,"Tsp","Ingredient");
            l.add(item);
        }

        b.putSerializable("List", l);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == -1) {
            if (requestCode == 300) {
                Fragment_Tab_List f = (Fragment_Tab_List) getItem(1);
                f.adaptorRecipeItem.notifyDataSetChanged();
            }
        }
    }
}
