package com.example.romana.grm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rp on 3/16/2016.
 */
public class Adaptor_Recipe_Item extends BaseAdapter {

    private String MSG;
    private Context context;
    private ArrayList<Recipe> recipe,originalRecipe;
    private ViewHolder viewHolder = null;

    int flag=0;

    public Adaptor_Recipe_Item(Context context, int textViewResourceId, ArrayList<Recipe> beans, String msg) {
        this.context = context;
        recipe = beans;
        MSG = msg;
        if(msg.equals("0"))
        {
            originalRecipe = Data.Standard_Recipe;
        }
        else {
            originalRecipe = new ArrayList<Recipe>();
            originalRecipe.addAll(recipe);
        }
    }

    @Override
    public int getCount() {
        return recipe.size() ;
    }

    @Override
    public Object getItem(int position) {
        return recipe.get(position);
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

            convertView = layoutInflater.inflate(R.layout.adaptor_recipe_item, null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.heart = (ImageButton) convertView.findViewById(R.id.heart);
            viewHolder.trash = (ImageButton) convertView.findViewById(R.id.trash);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        final Recipe item = (Recipe) getItem(position);
        if (!item.isFavourite()) {
            viewHolder.heart.setBackgroundResource(R.drawable.heart);
        }
        else
        {
            viewHolder.heart.setBackgroundResource(R.drawable.heartfilled);
        }
        if (!item.areRecipeDetailsPresent()) {
            viewHolder.trash.setBackgroundResource(R.drawable.download);
        }
        else
        {
            viewHolder.trash.setBackgroundResource(R.drawable.trash);
        }
        byte[] im = item.getImage();
        if(im!=null) {
            Bitmap img = BitmapFactory.decodeByteArray(im,0,im.length);
            viewHolder.image.setImageBitmap(img);
        }
        viewHolder.title.setText(item.getName());
            viewHolder.heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton i = (ImageButton) v;
                    if (!item.isFavourite()) {
                        i.setBackgroundResource(R.drawable.heartfilled);
                        item.setFavourite(true);
                        Data.Favourite_Recipe.add(item);

                    } else {
                        item.setFavourite(false);
                        i.setBackgroundResource(R.drawable.heart);
                        if(MSG.equals("2")) {
                            recipe.remove(item);
                            originalRecipe.remove(item);
                        }
                        Data.Favourite_Recipe.remove(item);
                        notifyDataSetChanged();
                    }
                }

            });

        if (MSG.equals("2"))
            viewHolder.trash.setVisibility(View.GONE);
        else if(MSG.equals("1"))
        {
            viewHolder.trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   final Recipe r = recipe.get(position);
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    dialogBuilder.setMessage("Do you want to delete "+r.getName()+" ?");
                    dialogBuilder.setCancelable(true);
                    dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            recipe.remove(position);
                            originalRecipe.remove(r);
                            Toast.makeText(context,r.getName()+" is removed from Cook Book.",Toast.LENGTH_SHORT ).show();
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });
                    dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert11 = dialogBuilder.create();
                    alert11.show();


                }
            });
        }
        else {
            viewHolder.trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ImageButton i = (ImageButton) v;
                    final Recipe r = (Recipe)getItem(position);
                    if (!r.areRecipeDetailsPresent()) {
                       String s = Data.downloadRecipeDetails(r);
                        Toast.makeText(context,s,Toast.LENGTH_SHORT ).show();
                        recipe.set(position,r);
                        i.setBackgroundResource(R.drawable.trash);
                    }
                    else {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                        dialogBuilder.setMessage("Do you want to delete Recipe Details.");
                        dialogBuilder.setCancelable(true);
                        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String s =Data.deleteRecipeDetails(r);
                                Toast.makeText(context,s,Toast.LENGTH_SHORT ).show();
                                recipe.set(position, r);
                                i.setBackgroundResource(R.drawable.download);
                                dialog.cancel();
                            }
                        });
                        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alert11 = dialogBuilder.create();
                        alert11.show();

                    }
                }
            });
        }
        return convertView;

    }
    public void filterData ( String query)
    {
        if(MSG.equals("2")) {
            originalRecipe.clear();
            originalRecipe.addAll(Data.Favourite_Recipe);
        }
        query = query.toLowerCase();
        recipe.clear();

        if (query.isEmpty())
        {
            recipe.addAll(originalRecipe);
        }
        else
        {
            for (Recipe it :originalRecipe)
            {
                if (it.getName().toLowerCase().contains(query))
                {
                    recipe.add(it);
                }

            }
        }
        notifyDataSetChanged();
    }


    private class ViewHolder{
        ImageView image;
        ImageView trash;
        TextView title;
        ImageButton heart;

    }

}
