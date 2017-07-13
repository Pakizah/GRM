package com.example.romana.grm;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Activity_Ingredient_Details extends AppCompatActivity {
    TextView eng;
    TextView urdu;
    ImageView img;
    TableLayout table;
    TextView Nutritional_heading;
    Ingredient c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Nutritional_heading = (TextView) findViewById(R.id.txt1);
        table = (TableLayout) findViewById(R.id.Nutritional_Table);
        Intent i = getIntent();
         c = (Ingredient)i.getExtras().getSerializable("ING");

        eng = (TextView) findViewById(R.id.eng);
        urdu = (TextView) findViewById(R.id.urdu);
        img = (ImageView) findViewById(R.id.image);
        eng.setText(c.getName());
        urdu.setText(c.getUrdu_Name());
        if(c.getImage()!=null) {
            Ingredient in = Ingredient.getIngredient(c.getName());
            c.setImage(in.getImage());
            c.setNutritional_Facts(in.getNutritional_Facts());
        }
        if(c.getImage()!=null)
        {
            Bitmap imge = BitmapFactory.decodeByteArray(c.getImage(), 0, c.getImage().length);
            img.setImageBitmap(imge);
            if(c.getNutritional_Facts()!=null)
            {
                Nutritional_heading.setVisibility(View.VISIBLE);
                table.setVisibility(View.VISIBLE);
                String [] s = c.getNutritional_Facts().split("\n");
                for (String str: s) {
                    String[] nf = str.split("_");
                        TableRow r = new TableRow (table.getContext());
                    r.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                        TextView c1 = new TextView(r.getContext());
                    c1.setText(nf[0]);
                    c1.setTextColor(Color.parseColor("#0c6a00"));
                        c1.setLayoutParams(new TableRow.LayoutParams(0));
                    TextView c2 = new TextView(r.getContext());
                    c2.setText(nf[1]);
                    c2.setTextColor(Color.parseColor("#0c6a00"));
                    c2.setLayoutParams(new TableRow.LayoutParams(1));
                    r.addView(c1);
                    r.addView(c2);
                    table.addView(r);

                }

            }
        }
        else
        {
            img.setVisibility(View.GONE);
            Nutritional_heading.setVisibility(View.VISIBLE);
            Nutritional_heading.setText("No details to show");
        }


    }

}

