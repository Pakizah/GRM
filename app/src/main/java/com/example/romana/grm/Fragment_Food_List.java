package com.example.romana.grm;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

        import java.util.ArrayList;

public class Fragment_Food_List extends Fragment {
    private Adaptor_Food_Item adapter;
    private ArrayList<Item> listData;
    Context context;
    private ListView list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.list, container, false);
        list = (ListView)view.findViewById(R.id.lv);
        Bundle bundle = this.getArguments();
        listData  = (ArrayList<Item>) bundle.getSerializable("Food_List");
        context=view.getContext();
        adapter = new Adaptor_Food_Item(view.getContext(), R.layout.adaptor_food_item, listData);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "bbk";
            }
        });
        return view;
    }

    public void notifyFragment()
    {
        adapter.notifyDataSetChanged();

    }

}
