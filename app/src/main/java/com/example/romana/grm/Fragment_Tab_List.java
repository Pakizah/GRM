package com.example.romana.grm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Fragment_Tab_List extends Fragment{

    private Context context;
    String[] MSG;
    private Adaptor_Food_Item adaptor_food_item;
    ArrayList<Item> IngredientItem;
    Adaptor_Recipe_Item adaptorRecipeItem;
    private ArrayList<Food> foodItem;
    private ArrayList<Recipe> recipeItem;
    Adaptor_Add_Food_Item adaptorAddFoodItem;
    private ArrayList<Item> Selecteditem;
    private Button AddMorebtn;
    ListView list;
    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_list, container, false);
        context = view.getContext();
        String msg = getArguments().getString("Msg");
        MSG = msg.split("_");
        Selecteditem = (ArrayList<Item>) getArguments().getSerializable("Selected_List");
        list = (ListView) view.findViewById(R.id.lv_values);
       // InitializeData();
        if(MSG[0].equals("AddFoodTab"))
        {
            if(MSG[1].equals("0"))
            {
                final ArrayList<String> ml = new ArrayList<String>();
                Object[] ob = Data.Meal_Plan_Table.keySet().toArray();
                for (Object s: ob) {
                    ml.add(s.toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,ml);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                        final String s = ml.get(position);
                        dialogBuilder.setMessage("Do you want to add "+s+" meal Plan ?");
                        dialogBuilder.setCancelable(true);
                        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MealPlan m = Data.Meal_Plan_Table.get(s);
                                Intent intent = new Intent();
                                intent.putExtra("Add_MealPlan", m);
                                getActivity().setResult(-1, intent);
                                getActivity().finish();
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
            else
            {
                int  i = Integer.parseInt(MSG[1]);
                foodItem = (ArrayList<Food>) getArguments().getSerializable("List");
                adaptorAddFoodItem = new Adaptor_Add_Food_Item(getActivity(), R.layout.adaptor_add_food_item,foodItem ,MSG[1]);
                list.setAdapter(adaptorAddFoodItem);
                adaptorAddFoodItem.notifyDataSetChanged();
                AddFoodItem();
            }
        }
        else if( MSG[0].equals("AddIngredientTab"))
        {

            foodItem = new ArrayList<Food> ();
            int  i = Integer.parseInt(MSG[1]);
            GroupRow r  = Data.Ingredients.get(i);
            foodItem = new ArrayList<Food>();
            foodItem.addAll(r.getChildList());

            adaptorAddFoodItem = new Adaptor_Add_Food_Item(getActivity(), R.layout.adaptor_add_food_item,foodItem ,MSG[1]);
            list.setAdapter(adaptorAddFoodItem);
            adaptorAddFoodItem.notifyDataSetChanged();
            AddFoodItem();

        }
        else if (MSG[0].equals("RecipeTab"))
        {
            if(MSG[1].equals("0")) {
                ArrayList<Recipe> l = (ArrayList<Recipe>) getArguments().getSerializable("List");
                recipeItem = new ArrayList<Recipe>();
                recipeItem.addAll(l);
            }
            else
            {
                recipeItem = (ArrayList<Recipe>) getArguments().getSerializable("List");
            }
            adaptorRecipeItem = new Adaptor_Recipe_Item(getActivity(),R.layout.adaptor_recipe_item, recipeItem ,MSG[1]);
            list.setAdapter(adaptorRecipeItem);
            adaptorRecipeItem.notifyDataSetChanged();
            openRecipeDetail();
        }
        else
        {
            AddMorebtn = (Button)  view.findViewById(R.id.add);
            AddMorebtn.setVisibility(View.VISIBLE);
            IngredientItem = (ArrayList<Item> )  getArguments().getSerializable("List");
            adaptor_food_item = new Adaptor_Food_Item(getActivity(),R.layout.adaptor_food_item,IngredientItem);
            list.setAdapter(adaptor_food_item);

            AddMorebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,Activity_Tab_View.class);
                    i.putExtra("Activity","Add_Ingredient");
                    startActivityForResult(i, 200);

                }
            });
        }
     return view;

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == -1) {
            if (requestCode == 200) {
                ArrayList<Item> item = (ArrayList<Item>) data.getSerializableExtra("Add_Food_Item");
                IngredientItem.addAll(item);

            }
        }
    }

    public boolean Filter_Data (String query) {
        if(MSG.length ==2)
        {
            if ( MSG[0].equals("AddIngredientTab")) {
                adaptorAddFoodItem.filterIngredients(query);
                adaptorAddFoodItem.notifyDataSetChanged();
            }
            else if (MSG[0].equals("AddFoodTab"))
            {
               // adaptorAddFoodItem.filterFood(query);
                adaptorAddFoodItem.notifyDataSetChanged();
            }
            else if (MSG[0].equals("RecipeTab"))
            {
                adaptorRecipeItem.filterData(query);
                adaptorRecipeItem.notifyDataSetChanged();
            }
        }
        return false;
    }

    public void updateIngredientQuantity(double s)
    {
        int size = IngredientItem.size();
        for(int i= 0;i<size ; i++)
        {
            Item f = IngredientItem.get(i);
            f.setQuantity(s*f.getQuantity());
        }
        adaptor_food_item.notifyDataSetChanged();
    }

    private void AddFoodItem()
    {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater li = LayoutInflater.from(context);
                final Food j = foodItem.get(position);
                View promptsView;
                String s = j.getClass().getName();
                if (s.contains("Recipe")) {
                    promptsView = li.inflate(R.layout.dialog_edit_recipe, null);
                } else {
                    promptsView = li.inflate(R.layout.dialog_edit_ingredient, null);
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText name = (EditText) promptsView.findViewById(R.id.food_name);
                name.setText(j.Name);
                name.setFocusable(false);
                final EditText servings = (EditText) promptsView.findViewById(R.id.food_quantity);
                servings.setText(Double.toString(1.0));
                TextView Add = (TextView) promptsView.findViewById(R.id.OK);
                TextView Cancel = (TextView) promptsView.findViewById(R.id.Cancel);
                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();

                if (!s.contains("Recipe")) {
                    final Spinner serving_size = (Spinner) promptsView.findViewById(R.id.food_unit);
                    ArrayList<String> units = Ingredient.getIngridientUnits(j.getName());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, units);
                    serving_size.setAdapter(adapter);
                    Add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Validation validate = new Validation();
                            double d = Double.parseDouble(servings.getText().toString());
                            String u = serving_size.getSelectedItem().toString();
                            Item i = new Item(j.getName(), d, u, "Ingredient");
                            Selecteditem.add(i);
                            Intent intent = new Intent();
                            intent.putExtra("Add_Food_Item", Selecteditem);
                            getActivity().setResult(-1, intent);
                            Toast.makeText(context, "Item is added to list", Toast.LENGTH_SHORT).show();
                            alertDialog.cancel();
                        }
                    });
                } else {
                    Add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double d = Double.parseDouble(servings.getText().toString());
                            String u = "serving";
                            Item i = new Item(j.getName(), d, u, "Recipe");
                            Selecteditem.add(i);
                            Intent intent = new Intent();
                            intent.putExtra("Add_Food_Item", Selecteditem);
                            getActivity().setResult(-1, intent);
                            Toast.makeText(context, "Item is added to list", Toast.LENGTH_SHORT).show();
                            alertDialog.cancel();
                        }
                    });
                }

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
    }
    private void openRecipeDetail ()
    {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Recipe r  = (Recipe)recipeItem.get(position);
                if(r.areRecipeDetailsPresent()) {
                    Intent i = new Intent(context, Activity_Tab_View.class);
                    i.putExtra("Activity", "Recipe_Details");
                    i.putExtra("Recipe", r);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(context,"No Recipe Details are Present",Toast.LENGTH_SHORT).show();
                }

            }

        });
    }


}