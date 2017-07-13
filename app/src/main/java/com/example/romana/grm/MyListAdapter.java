package com.example.romana.grm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wahlah on 3/23/2017.
 */
public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupRow> groupList;
    private ArrayList<GroupRow> originalList;

    public MyListAdapter(Context context, ArrayList<GroupRow> groupList) {
        this.context = context;
        this.groupList = new ArrayList<GroupRow>();
        this.groupList.addAll(groupList);
        this.originalList = new ArrayList<GroupRow>();
        this.originalList.addAll(groupList);
    }
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Ingredient> childList = groupList.get(groupPosition).getChildList();
        return childList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Ingredient> childList = groupList.get(groupPosition).getChildList();
        return childList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupRow group = (GroupRow) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(group.getName().trim());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Ingredient child = (Ingredient) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView eName = (TextView) view.findViewById(R.id.eng_name);
        TextView uName = (TextView) view.findViewById(R.id.urdu_name);
        eName.setText(child.getName().trim());
        uName.setText(child.getUrdu_Name().trim());


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(groupList.size()));
        groupList.clear();

        if(query.isEmpty()){
            groupList.addAll(originalList);
        }
        else {

            for(GroupRow group: originalList){

                ArrayList<Ingredient> childList = group.getChildList();
                ArrayList<Ingredient> newList = new ArrayList<Ingredient>();
                for(Ingredient child: childList){
                    if(child.getName().toLowerCase().contains(query) ||
                            child.getUrdu_Name().toLowerCase().contains(query)){
                        newList.add(child);
                    }
                }
                if(newList.size() > 0){
                    GroupRow nGroup = new GroupRow(group.getName(),newList);
                    groupList.add(nGroup);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(groupList.size()));
        notifyDataSetChanged();

    }
}
