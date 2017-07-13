package com.example.romana.grm;

/**
 * Created by Namra on 3/29/2017.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentNutrition extends Fragment {

    TextView v ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_fragment, container, false);
        return view;
    }
}
