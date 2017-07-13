package com.example.romana.grm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptor_Add_Food_Item extends BaseAdapter {

    String MSG;
    Context context;
    ArrayList<Food> Search_list,Original_List;
    ViewHolder viewHolder = null;

    public Adaptor_Add_Food_Item(Context context, int textViewResourceId, ArrayList<Food> items, String msg) {
        this.context = context;
        Search_list =items;
        Original_List = new ArrayList<Food>();
        Original_List.addAll(Search_list);
        MSG = msg;
    }


    @Override
    public int getCount() {
        return Search_list.size() ;
    }

    @Override
    public Object getItem(int position){
        if(Search_list!=null)
        return Search_list.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adaptor_add_food_item, null);
            viewHolder.Title= (TextView) convertView.findViewById(R.id.title);
            viewHolder.Abouts = (TextView) convertView.findViewById(R.id.Abouts);
            viewHolder.Calories = (TextView) convertView.findViewById(R.id.Calories);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        Food FoodItem = (Food) getItem(position);
        if(FoodItem!=null) {

            viewHolder.Title.setText(FoodItem.Name);
        }
        return convertView;

    }

    public void filterIngredients(String query)
    {
        query = query.toLowerCase();
        Search_list.clear();

        if (query.isEmpty())
        {
            int  i = Integer.parseInt(MSG);
            GroupRow r  = Data.Ingredients.get(i);
            Search_list.addAll(r.getChildList());
        }
        else
        {
            Original_List.clear();
            int  i = Integer.parseInt(MSG);
            GroupRow r  = Data.Ingredients.get(i);
            Original_List.addAll(r.getChildList());
            for (Food it :Original_List)
            {
                if (it.Name.toLowerCase().contains(query))
                {
                    Search_list.add(it);
                }

            }
        }
        notifyDataSetChanged();
    }

    public void filterFood(String query)
    {
        query = query.toLowerCase();
        Search_list.clear();

        if (query.isEmpty())
        {
            int  i = Integer.parseInt(MSG);
            Search_list.addAll(Adaptor_Page.add_food_item.get(i));
        }
        else
        {
            Original_List.clear();
            int  i = Integer.parseInt(MSG);
            Original_List.addAll(Adaptor_Page.add_food_item.get(i));
            for (Food it :Original_List)
            {
                if (it.Name.toLowerCase().contains(query))
                {
                    Search_list.add(it);
                }

            }
        }
        notifyDataSetChanged();
    }


    private class ViewHolder{
        TextView Title;
        TextView Abouts;
        TextView Calories;

    }



}
