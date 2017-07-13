package com.example.romana.grm;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pakizah Fatima on 1/12/2017.
 */
public class Adaptor_Grocery_Item extends ArrayAdapter {

    private Context context;
    private ArrayList<Item> data;
    private ArrayList<Item> StrokedItems;



    @SuppressWarnings("unchecked")
    public Adaptor_Grocery_Item(Context context, int textViewResourceId, ArrayList<Item> array) {
        super(context, textViewResourceId, array);
        StrokedItems = new ArrayList<Item>();
        this.context = context;
        data = array;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            if (view == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.adaptor_grocery_item, null, true);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.check = (CheckedTextView)view.findViewById(R.id.text1);
                viewHolder.name = (TextView)view.findViewById(R.id.g_name);
                viewHolder.rl = (RelativeLayout)view.findViewById(R.id.rl);
                viewHolder.quantity = (TextView)view.findViewById(R.id.g_quantity);
                viewHolder.unit = (TextView)view.findViewById(R.id.g_unit);
                viewHolder.edit = (ImageView)view.findViewById(R.id.edit);

                viewHolder.trash = (ImageView)view.findViewById(R.id.trash);

                view.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
            final Item i = data.get(position);
            if (i != null){
                holder.name.setText(i.getName());
                if ( i.purchased == true) {
                    holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.name.setPaintFlags(holder.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                holder.quantity.setText(String.valueOf(i.getQuantity()));
                holder.unit.setText(i.getUnit());
                holder.rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView) v.findViewById(R.id.g_name);
                        Item i = data.get(position);
                        if (i.purchased == true) {
                            tv.setPaintFlags(tv.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                            i.purchased = false;
                            StrokedItems.remove(i);

                        } else {
                            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            i.purchased = true;
                            StrokedItems.add(i);
                        }


                    }
                });
                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LayoutInflater li = LayoutInflater.from(context);
                        View dialogView = li.inflate(R.layout.dialog_edit_ingredient, null);
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                        dialogBuilder.setView(dialogView);
                        final Item j = data.get(position);
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
                        Item i = data.get(position);
                        StrokedItems.remove(i);
                        data.remove(position);
                        notifyDataSetChanged();
                    }});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;


    }

    public void hideStrokedItems()
    {
        data.removeAll(StrokedItems);
        notifyDataSetChanged();

    }
    public void unhingeStrokedItems()
    {

        data.removeAll(StrokedItems);
        data.addAll(StrokedItems);
        notifyDataSetChanged();
    }

    static class ViewHolder implements Serializable{
        public TextView name;
        public TextView quantity;
        public TextView unit;
        public ImageView edit;
        public ImageView trash;
        public CheckedTextView check;
        public Button hide;
        public RelativeLayout rl;
    }

}