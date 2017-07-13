package com.example.romana.grm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Submit_Recipe extends AppCompatActivity implements View.OnClickListener  {
    private static final int SELECT_PICTURE = 100;
    private static final int SELECT_INGREDIENT = 200;
    private ImageView imgView;
    private Button recipe_select_image;
    private EditText recipe_name;
    private Spinner recipe_type;
    private EditText recipe_Prep_min;
    private Button recipe_h_inc_btn;
    private Button recipe_h_dec_btn;
    private EditText recipe_cooking_min;
    private Button recipe_m_inc_btn;
    private Button recipe_m_dec_btn;
    private EditText recipe_servings;
    private Button recipe_s_inc_btn;
    private Button recipe_s_dec_btn;
    private TextView recipe_ingredient;
    private Button recipe_add_ingredient;
    private EditText recipe_method;
    private Button recipe_submit_btn;
    private Validation validate;
    private Context context;
    public ArrayList<Item> Food_List;
    Fragment_Food_List frag;

    private int ADD_FOOD = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validate = new Validation();
        Food_List = new ArrayList<Item>();
        setContentView(R.layout.activity_submit_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // initialize variables from xml
        findViewByIds(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Food_List", Food_List);
        frag =new Fragment_Food_List();
        frag.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment4, frag);
        fragmentTransaction.commit();
        // setting on Click Listners
       setOnClickListners();

    }

    void  findViewByIds(Context c)
    {
        recipe_select_image = (Button) findViewById(R.id.btnSelectImage);
        imgView = (ImageView) findViewById(R.id.imgView);
        recipe_name = (EditText) findViewById(R.id.RecipeName);
        recipe_type = (Spinner) findViewById(R.id.FoodType);
        recipe_Prep_min = (EditText) findViewById(R.id.Prep_min);
        recipe_Prep_min.setFocusable(false);
        recipe_h_inc_btn = (Button) findViewById(R.id.h_inc);
        recipe_h_dec_btn = (Button) findViewById(R.id.h_dec);
        recipe_cooking_min =(EditText) findViewById(R.id.Cook_min);
        recipe_cooking_min.setFocusable(false);
        recipe_m_inc_btn = (Button) findViewById(R.id.m_inc);
        recipe_m_dec_btn = (Button) findViewById(R.id.m_dec);
        recipe_servings = (EditText) findViewById(R.id.Servings);
        recipe_servings.setFocusable(false);
        recipe_s_inc_btn = (Button) findViewById(R.id.s_inc);
        recipe_s_dec_btn = (Button) findViewById(R.id.s_dec);
        recipe_method =(EditText) findViewById(R.id.method);
        recipe_ingredient = (TextView) findViewById(R.id.Ingredient);
        recipe_add_ingredient = (Button) findViewById(R.id.AddIngredient);
        recipe_submit_btn = (Button) findViewById(R.id.submit);

        context = c;
    }
    void setOnClickListners()
    {
        recipe_add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Tab_View.class);
                i.putExtra("Activity","Add_Ingredient");
                startActivityForResult(i, ADD_FOOD);
            }
        });
        recipe_h_dec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int val = Integer.parseInt(recipe_Prep_min.getText().toString());
                if(val>0 && val<=360) {
                    val = val - 1;
                }
                else
                {
                    val = 0;
                }
                recipe_Prep_min.setText(String.valueOf(val));
            }
        });
        recipe_h_inc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(recipe_Prep_min.getText().toString());
                if(val>=0 && val< 360) {
                    val = val + 1;
                }
                else
                {
                    val = 360;
                }
                recipe_Prep_min.setText(String.valueOf(val));
            }
        });
        recipe_m_dec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(recipe_cooking_min.getText().toString());
                if(val>0 && val<=360) {
                    val = val - 1;
                }
                else
                {
                    val = 0;
                }
                recipe_cooking_min.setText(String.valueOf(val));
            }
        });
        recipe_m_inc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(recipe_cooking_min.getText().toString());
                if(val>=0 && val< 360) {
                    val = val + 1;
                }
                else
                {
                    val =360;
                }
                recipe_cooking_min.setText(String.valueOf(val));
            }
        });
        recipe_s_dec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(recipe_servings.getText().toString());
                if(val>1 && val<=20) {
                    val = val - 1;
                }
                else
                {
                    val = 1;
                }
                recipe_servings.setText(String.valueOf(val));
            }
        });
        recipe_s_inc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(recipe_servings.getText().toString());
                if (val >= 1 && val <= 19) {
                    val = val + 1;
                } else {
                    val = 20;
                }
                recipe_servings.setText(String.valueOf(val));
            }
        });
        recipe_select_image.setOnClickListener(this);
        recipe_submit_btn.setOnClickListener(this);
    }
    /* Choose an image from Gallery */
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    imgView.setImageURI(selectedImageUri);
                }
            }
            if (requestCode == ADD_FOOD) {
                ArrayList<Item> item = (ArrayList<Item>) data.getSerializableExtra("Add_Food_Item");
                Toast.makeText(Activity_Submit_Recipe.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
                Food_List.addAll(item);
                frag.notifyFragment();
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onClick(View v)
    {
        String s;
        switch (v.getId())
        {
            case R.id.btnSelectImage:
                openImageChooser();
                break;

            case R.id.submit:
               // SubmitValidations();
                String Name = recipe_name.getText().toString();
                String Type = recipe_type.getSelectedItem().toString();
                String Category = "User_Recipe";
                boolean Modified = true;
                String Image = "Still To be select";
                String Nutritional_Facts = null;
                 int Prep_Time = Integer.parseInt(recipe_Prep_min.getText().toString());
                int Cook_Time = Integer.parseInt(recipe_cooking_min.getText().toString());
                 ArrayList<Item> Ingredients = Food_List;
                String Method = recipe_method.getText().toString();
                 boolean favourite = false;
                Recipe  r = new Recipe(Name,R.drawable.steak,Type,Category,Nutritional_Facts,Prep_Time,Cook_Time,Ingredients,Method,favourite);
                Data.Cook_Book_Recipe.add(r);
                Toast.makeText(this,"Your Recipe is added in Cook Book." ,Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

    }

    void SubmitValidations(){
        String s;
        //Recipe Method
        validate.setError(recipe_method , validate.ValidateMethod(recipe_method));
        // Recipe Ingredient
        int  i = Food_List.size();
       /* if(i<3)
            validate.setError(recipe_ingredient,"Please add atleast 3 ingredients");
        else if (i>50)
            validate.setError(recipe_ingredient,"Please add less than 50 ingredients");*/
        //Recipe Servings
        validate.setError(recipe_servings, validate.ValidateServings(recipe_servings));
        //Recipe Name
        validate.setError(recipe_name, validate.ValidateRecipeName(recipe_name));
    }

}
