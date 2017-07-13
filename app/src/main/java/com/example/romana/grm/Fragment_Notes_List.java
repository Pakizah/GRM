package com.example.romana.grm;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment_Notes_List extends Fragment {
    private Adaptor_Notes NoteAdapter;
    ListView Noteslist;
    private Context context ;
    private ArrayList<String> listData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list, container, false);
        context = view.getContext();
        Bundle bundle = this.getArguments();
        listData  = (ArrayList<String>) bundle.getSerializable("Notes_List");
        Noteslist = (ListView) view.findViewById(R.id.lv);
        Noteslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Note");
                String j = listData.get(position);
                builder.setMessage(j);
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        NoteAdapter = new Adaptor_Notes(view.getContext(), R.layout.adaptor_notes_item,listData);
        Noteslist.setAdapter(NoteAdapter);
        NoteAdapter.notifyDataSetChanged();
        return view;
    }
    public void notifyFragment()
    {
        NoteAdapter.notifyDataSetChanged();
    }
}
