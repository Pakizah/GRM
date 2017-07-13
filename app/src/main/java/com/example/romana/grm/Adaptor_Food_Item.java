package com.example.romana.grm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptor_Food_Item extends ArrayAdapter {
    private Context context;
    private ArrayList<Item> data;
    private ArrayList<Item> ingredientItems;
    private String MSG;

    public Adaptor_Food_Item(Context context, int textViewResourceId, ArrayList<Item> array) {
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
                view = inflater.inflate(R.layout.adaptor_food_item, null, true);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.name = (TextView)view.findViewById(R.id.Name);
                viewHolder.quantity = (TextView) view.findViewById(R.id.Quantity);
                viewHolder.unit = (TextView) view.findViewById(R.id.Unit);
                viewHolder.trash = (ImageButton) view.findViewById(R.id.trash);
                viewHolder.edit = (ImageButton) view.findViewById(R.id.edit);
                view.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
             Item obj = data.get(position);

            if (obj != null){
                holder.name.setText(obj.getName());
                holder.quantity.setText(Double.toString(obj.getQuantity()));
                holder.unit.setText(obj.getUnit());
                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater li = LayoutInflater.from(context);
                        View dialogView = li.inflate(R.layout.dialog_edit_ingredient, null);
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                        dialogBuilder.setView(dialogView);
                        final Item j=data.get(position);
                        final EditText name = (EditText) dialogView.findViewById(R.id.food_name);
                        name.setText(j.getName());
                        name.setFocusable(false);
                        final Spinner unit = (Spinner) dialogView.findViewById(R.id.food_unit);
                        final EditText servings = (EditText) dialogView.findViewById(R.id.food_quantity);
                        servings.setText(Double.toString(j.getQuantity()));
                        TextView update = (TextView) dialogView.findViewById(R.id.OK);
                        update.setText("Update");
                        TextView Cancel = (TextView) dialogView.findViewById(R.id.Cancel);
                        // create alert dialog
                        final AlertDialog alertDialog = dialogBuilder.create();
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                    String q = servings.getText().toString();
                                    j.setQuantity(Double.parseDouble(q));
                                    j.setUnit(unit.getSelectedItem().toString());
                                    data.set(position, j);
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
                        Item j = data.get(position);

                        builder.setMessage("Do you want to delete " + j.getName() + " from List?");
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
        public TextView name;
        public TextView quantity;
        public TextView unit;
        public ImageButton edit;
        public ImageButton trash;

    }
}

