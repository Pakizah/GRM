package com.example.romana.grm;

/**
 * Created by Pakizah Fatima on 1/9/2017.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_Recipe_Method extends Fragment {
    TextView tv;
    String str;
    String[] rec;

/*
    public void onActivityCreated(Bundle savedInstanceState) {
        View linearLayout =  getView().findViewById(R.id.linear);
        str = getArguments().getString("Method");
        if(str!=null)
        {
            rec = str.split("%");
            for(int i = 0 ; i < rec.length ; i++)
            {
                TextView valueTV = new TextView(getContext());
                valueTV.setText(rec[i]);
                valueTV.setTextColor(Color.parseColor("#0c6a00"));
                valueTV.setId(i);
                valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                ((LinearLayout) linearLayout).addView(valueTV);
            }
            super.onActivityCreated(savedInstanceState);
        }
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_three, container, false);
        TextView tv  = (TextView) v.findViewById(R.id.method);
        str = getArguments().getString("Method");
        if(str!=null) {
            rec = str.split("%");
        }
        String m ="";
        for (String s:rec) {
            m = m +s+"\n\n";
        }
            tv.setText(m);
        return v;

    }
}