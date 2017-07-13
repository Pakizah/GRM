package com.example.romana.grm;

/**
 * Created by Pakizah Fatima on 1/9/2017.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_Recipe_Overview extends Fragment {
    Button inc;
    ImageView v;
    TextView p;
    TextView c;
    Button dec;
    EditText ed;
    double val;
    Recipe r;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        v = (ImageView) getView().findViewById(R.id.im);
        p = (TextView)getView().findViewById(R.id.time_p);
        c = (TextView)getView().findViewById(R.id.time_c);
        ed = (EditText)getView().findViewById(R.id.persons);
        Bundle b = getArguments();
        byte[] im = b.getByteArray("Recipe_img");
        Bitmap img = BitmapFactory.decodeByteArray(im,0,im.length);
        v.setImageBitmap(img);

        p.setText(" : "+b.getInt("prep")+" m");
        c.setText(" : "+b.getInt("cook")+" m");
        ed.setFocusable(false);
        inc = (Button)getView().findViewById(R.id.inc);
        inc.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                val = Double.parseDouble(ed.getText().toString());
                double i = val;
                if(val>=1 && val<20) {
                    val = val + 1;
                }
                else
                {
                    val = 1;
                }
                ed.setText(String.valueOf(val));
                i = val/i;
                Activity_Tab_View a =(Activity_Tab_View) getActivity();
                Fragment_Tab_List currentTab = (Fragment_Tab_List) a.adapter.instantiateItem(a.viewPager,1);
                currentTab.updateIngredientQuantity(i);
            }



        });
        dec = (Button)getView().findViewById(R.id.dec);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                double i = val;
                val = Double.parseDouble(ed.getText().toString());
                if(val>1) {
                    val = val - 1;
                }

                else
                {
                    val = 20;
                }
                ed.setText(String.valueOf(val));
                i = val/i;
                Activity_Tab_View a =(Activity_Tab_View) getActivity();
                Fragment_Tab_List currentTab = (Fragment_Tab_List) a.adapter.instantiateItem(a.viewPager,1);
                currentTab.updateIngredientQuantity(i);

            }



        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_one, container, false);
    }
}