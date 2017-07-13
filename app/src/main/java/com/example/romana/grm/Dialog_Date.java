package com.example.romana.grm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Romana on 07/05/2017.
 */
public class Dialog_Date extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    Activity_Meal_Plan MPA;
    Activity_Grocery AG;
    public Dialog_Date(Activity_Meal_Plan M){
        MPA = M;
        AG = null;
    }
    public Dialog_Date(Activity_Grocery M){
        MPA = null;
        AG= M;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }
    public void onDateSet (DatePicker view,int year, int month, int day)
    {
        String date = day+"-"+(month+1)+"-"+year;
        if(AG==null)
        MPA.loadMealPlan(date);
        else
        AG.LoadGrocery(date);
        Toast.makeText(view.getContext(), date , Toast.LENGTH_SHORT).show();
    }

}
