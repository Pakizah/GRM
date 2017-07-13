package com.example.romana.grm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

        import java.util.ArrayList;

public class Adaptor_Notes extends ArrayAdapter {
    private Context context;
    private ArrayList<String> data;

    public Adaptor_Notes(Context context, int textViewResourceId, ArrayList<String> array) {
        super(context, textViewResourceId, array);

        this.context = context;
        data = array;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            if (view == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.adaptor_notes_item, null, true);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.txt = (TextView)view.findViewById(R.id.MP_Notes);
                viewHolder.trash = (ImageButton) view.findViewById(R.id.trash);
                viewHolder.edit = (ImageButton) view.findViewById(R.id.edit);
                view.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
            String name = data.get(position);
            if (name != null){
                holder.txt.setText(name);
                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.dialog_edit_note, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptsView);
                    String j = data.get(position);
                    final EditText note = (EditText) promptsView.findViewById(R.id.Notes);
                    note.setText(j);
                    TextView Ok = (TextView) promptsView.findViewById(R.id.Ok);
                    TextView Cancel = (TextView) promptsView.findViewById(R.id.Cancel);
                    // create alert dialog
                    final AlertDialog alertDialog = alertDialogBuilder.create();
                    Ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                String text = note.getText().toString();
                                data.set(position,text);
                                notifyDataSetChanged();
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
                holder.trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you want to delete note from List?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            data.remove(position);
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;

    }


    static class ViewHolder {
        public TextView txt;
        public ImageButton edit;
        public ImageButton trash;
    }
}
