package com.example.romana.grm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Romana on 08/05/2017.
 */
public class Data {
    static DatabaseAccess dbHelper;
    static final String[] Recipe_Tabs = {"Recipes","My Cook Book","My Favourite"};
    static final String[] Add_Food_Tabs = {"Recipes","My Cook Book","My Favourite","Fruits","Vegetables","Dairy and Egg Products","Spice Products"};
    static final String[] Recipe_Details_Tabs = {"Overview","Ingredients","Method"};
  //  static final String[] Add_Ingredient_Tabs = {"Fruits","Vegetables","Dairy and Egg Products","Spice"};
    static  ArrayList<Recipe> Standard_Recipe;
    static ArrayList<Recipe> Favourite_Recipe ;
    static ArrayList<Recipe> Cook_Book_Recipe ;/*
    static final ArrayList<Ingredient> Fruits = new ArrayList<Ingredient>();
    static final ArrayList<Ingredient> Vegetables = new ArrayList<Ingredient>();
    static final ArrayList<Ingredient> Dairy = new ArrayList<Ingredient>();
    static final ArrayList<Ingredient> Spice = new ArrayList<Ingredient>();*/
    static final ArrayList<GroupRow> Ingredients = new ArrayList<GroupRow>();
    static final HashMap<String,MealPlan> Meal_Plan_Table = new HashMap<String,MealPlan>();
    static final HashMap<String,ArrayList<Item>> Daily_Grocery = new HashMap<String,ArrayList<Item>>();
    static final HashMap<String,ArrayList<Item>> Weekly_Grocery = new HashMap<>();
    private Data ()
    {
    }
/*
    public static void  UpdateData ()
    {
        String[] f ={"Apple","Banana"};
        String [] s ={"Black Pepper","Water","Salt","Oil","Honey","Tea","Coffee","Sugar","Crushed Ice","Rice","Soda Drink"};
        String[] p ={"Egg"};
        String [] v ={"Lemon","Mint"};
        String [] d = {"Milk"};
        String[] uf ={"Gram","Gram"};
        String [] us ={"Gram","Milliliter","Gram","Milliliter","Gram","Gram","Gram","Milliliter","Gram","Gram","Milliliter", "Gram","Milliliter"};
        String[] up ={"Milliliter","Milligram"};
        String [] uv ={"Milliliter","Gram"};
        String [] ud = {"Milliliter"};
        for (int i = 0;i<f.length;i++) {
            String[] u = {uf[i]};
            Fruits.add(new Ingredient (f[i],"Fruit","kaila"));
        }
        for (int i = 0;i<v.length;i++) {
            String[] u = {uv[i]};
            Vegetables.add(new Ingredient(v[i],"Vegetables","kaila"));
        }
        for (int i = 0;i<s.length;i++) {
            String[] u = {us[i]};
            Spice.add(new Ingredient(s[i],"Spice","kaila"));
        }
        for (int i = 0;i<d.length;i++) {
            String[] u = {ud[i]};
            Dairy.add(new Ingredient(d[i],"Dairy","kaila"));
        }
        Standard_Recipe = Recipe.getAllRecipes(0);
/*
        String[] arr = {"kg", "g"};
        String str = "%1 Take the instant coffee powder and sugar in a cup.%2 Add a tsp of milk and mix the coffee powder and sugar.%3Whisk the mixture rigorously with the help of spoon until it becomes light brown in color and creamy.%4Heat the milk and let it comes to boil.%5Pour the hot milk into coffee cream we prepare earlier. Mix with the help of spoon.%6Mix the milk & coffee cream by transferring it between two cups.%7Once done, serve the coffee hot.";
        ArrayList<Item>  in = new ArrayList<Item>();
        Ingredient i = Spice.get(6);
        in.add(new Item(i.getName(),5.0,"Gram","Ingredient"));
        i = Dairy.get(0);
        in.add(new Item(i.getName(),225.0,"Milliliter","Ingredient"));
        i = Spice.get(7);
        in.add(new Item(i.getName(),1.0,"Gram","Ingredient"));
        Standard_Recipe.add(new Recipe ("Coffee",R.drawable.coffee,"Recipe","Insta Food","200",3,5,in,str,false));
        Cook_Book_Recipe.add(new Recipe ("Coffee",R.drawable.coffee,"Recipe","Insta Food","200",3,5,in,str,false));


    }
    public static void getFoodLists ()
    {
        /*
        int[] Image = {R.drawable.img1,R.drawable.steak,R.drawable.lava,R.drawable.lasagna,R.drawable.mango};
        String[] Title ={"Chicken Chowmen","Chicken Steak","Lava Cake","Lasagana","Mango Shake"};
        String[] arr = {"1 kg", "0.5 kg"};
        String str = "1 - Marinate the chicken: Combine the 2 teaspoons soy sauce, rice vinegar, and sesame oil in a small bowl. Add the sliced chicken, and toss to completely coat. Set aside while you cook the noodles.\n\n %2 - Cook the chow mein noodles according to package directions, drain well, and set aside.\n\n %3 - Heat half of the oil (1 tablespoon) in a very large skillet or wok. When is is very hot, but not smoking, add the chicken mixture, and stir fry until the chicken is cooked through. Remove the chicken to a plate, set aside and keep warm.\n\n %4 - Add the rest of the oil to the skillet, then add the cabbage, bok choy, water chestnuts and garlic; stir fry for a couple of minutes until the vegetables begin to wilt. Add the noodles, and continue to cook until the noodles are hot, and well combined with the vegetables.\n\n %5 - Add the soy sauce and oyster sauce, toss to combine. Add the chicken, toss to combine.\n\n %6 - Transfer the chow mein to a serving platter, and top with the chopped green onions. Serve immediately.\n\n %4 - Add the rest of the oil to the skillet, then add the cabbage, bok choy, water chestnuts and garlic; stir fry for a couple of minutes until the vegetables begin to wilt. Add the noodles, and continue to cook until the noodles are hot, and well combined with the vegetables.\n\n %5 - Add the soy sauce and oyster sauce, toss to combine. Add the chicken, toss to combine.\n\n %6 - Transfer the chow mein to a serving platter, and top with the chopped green onions. Serve immediately.#";
        ArrayList <Item> in = new ArrayList<Item>();
        Ingredient ing = new Ingredient("Butter ","Diary","Makan");
        in.add(new Item(ing.getName(),1,"kg", ing.getCategory()));
        ing = new Ingredient ("Egg ","Spice","Anda");
        in.add(new Item(ing.getName(),1,"kg", ing.getCategory()));
        for(int i = 0; i<Title.length ; i++) {
            Standard_Recipe.add(new Recipe(Title[i], Image[i], "Recipe", "Restaurant Food", "2000", 60, 30, in, str, false));
            Cook_Book_Recipe.add(new Recipe(Title[i], Image[i], "UserRecipe", "Restaurant Food", "2000", 60, 30, in, str, false));

        }
        UpdateData ();

    }*/

    public static String downloadRecipeDetails(Recipe r)
    {
        String[] arr = {"1 kg", "0.5 kg"};
        ArrayList <Item> in = new ArrayList<Item>();
        Ingredient ing = new Ingredient("Butter ","Diary","Makan");
        in.add(new Item(ing.getName(),1,(ing.getUnit())[0], ing.getCategory()));
        ing = new Ingredient ("Egg ","Spice","Anda");
        in.add(new Item(ing.getName(),1,(ing.getUnit())[0], ing.getCategory()));
        r.setRecipeDetails(in,"Nothing To show");
        return "Recipe downloading Completed";
    }
    public  static String deleteRecipeDetails(Recipe r)
    {
        r.setRecipeDetails(null,null);
        return "Recipe Details are removed";
    }
    public static ArrayList<Item> getDailyGrocery (String date)
    {
        ArrayList<Item>  Grocery_List = Data.Daily_Grocery.get(date);
         if(Grocery_List==null){
            MealPlan m = Data.Meal_Plan_Table.get(date);
             if(m!=null)
             {
            Grocery_List = new ArrayList<Item>();
            for (Item i: m .getFood_List()) {
                ArrayList<Item> ingredients = new ArrayList<Item>();
               if( i.getCategory().contains("Recipe")) {
                   String name = i.getName();
                   boolean b = false;
                   for (Recipe r: Cook_Book_Recipe) {
                       if(r.getName().equals(name))
                       {
                           ingredients.addAll( r.getIngredients());
                           b= true;
                           break;
                       }

                   }
                   if(b==false)
                   {
                       for (Recipe r: Standard_Recipe) {
                           if(r.getName().equals(name))
                           {
                               ingredients.addAll( r.getIngredients());
                               b = true;
                               break;
                           }

                       }
                   }

               }
                else if(i.getCategory().contains("Ingredient"))
               {
                   ingredients.add(i);
               }

                   for (Item j: ingredients) {
                       boolean flag = false;
                       for (Item k:Grocery_List) {
                           String j_name =j.getName().toLowerCase();
                           String k_name = k.getName().toLowerCase();
                           if(j_name.equals(k_name))
                           {
                               flag = true;
                               k.setQuantity(k.getQuantity() + ConvertUnit2Standard(j.getQuantity(),j.getUnit()));
                           }
                       }
                       if(!flag)
                       {
                           Grocery_List.add(new Item(j.getName(),j.getQuantity(),j.getUnit(),j.getCategory()));
                       }

                   }


            }
                 Daily_Grocery.put(date,Grocery_List);
             }

         }
        return Grocery_List;
    }
    public static ArrayList<Item> getDailyGrocery (MealPlan m,String date)
    {
        ArrayList<Item>  Grocery_List = null;
            if(m!=null)
            {
                Grocery_List = new ArrayList<Item>();
                for (Item i: m.getFood_List()) {
                    ArrayList<Item> ingredients = new ArrayList<Item>();
                    if( i.getCategory().contains("Recipe")) {
                       // Recipe r = (Recipe) i.getFood_item();
                       // ingredients.addAll( r.getIngredients());
                    }
                    else if(i.getCategory().contains("Ingredient"))
                    {
                        ingredients.add(i);
                    }

                    for (Item j: ingredients) {
                        boolean flag = false;
                        for (Item k:Grocery_List) {
                            String j_name =j.getName().toLowerCase();
                            String k_name = k.getName().toLowerCase();
                            if(j_name.equals(k_name))
                            {
                                flag = true;
                                k.setQuantity(k.getQuantity() + ConvertUnit2Standard(j.getQuantity(),j.getUnit()));
                            }
                        }
                        if(!flag)
                        {
                            Grocery_List.add(new Item(j.getName(),j.getQuantity(),j.getUnit(),j.getCategory()));
                        }

                    }


                }

                Daily_Grocery.put(date,Grocery_List);
    }
        return Grocery_List;
    }

    public static double  ConvertUnit2Standard (double q,String Unit)
    {
        return q;
    }
}
