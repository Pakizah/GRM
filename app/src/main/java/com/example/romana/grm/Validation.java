package com.example.romana.grm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    static public boolean validateNumbers (String s)
    {
        Pattern pattern = Pattern.compile("\\d{1,2}");
        Matcher matcher = pattern.matcher(s);
        return  matcher.matches();
    }
    static public boolean validateAlphabets(String p) {
        Pattern pattern = Pattern.compile("/^[a-zA-Z]+$/");
        Matcher matcher = pattern.matcher(p);
        return matcher.matches();
    }
    public String ValidateIngredientQuantity (EditText e)
    {
        if(validateNumbers(e.getText().toString())== false)
            return "Please Enter only digits";
        return null;

    }
    public void setError (EditText e, String s )
    {
        if(s!= null)
        {
            e.setError(s);
            e.requestFocus();
        }
    }
    public String ValidateMethod(EditText e)
    {
        if (e.getText().length()==0)
            return "Please Enter Method of Recipe";
        return null;
    }

    public String ValidateHrs (EditText e)
    {
        if (e.getText().length() == 0)
            return "Please Enter Hours";
        else
        {
            if(validateNumbers(e.getText().toString())== false)
                return "Please Enter only digits";
            else
            {
                if (Integer.parseInt(e.getText().toString()) > 99)
                    return "Please Enter hours less than 99";
            }
        }
        return null;

    }

    static public String ValidateRecipeName(EditText e) {
        String s = "";
        if (e.getText().length() == 0)
            return "Please Enter Your Recipe Name";
        else {
            if (e.getText().length() < 3 || e.getText().length() > 30)
                s = "Enter 4 to 30 characters. ";
            if ((!validateAlphabets(e.getText().toString())))
                s.concat("only alphabets are allowed");
            return s;
        }

    }
    static public String ValidateServings(EditText e) {
        String s = "";
        if (e.getText().length() == 0)
            return "Please Enter Your Servings";
        else {
            if(validateNumbers(e.getText().toString())== false)
                return "Please Enter only digits";
            else
            {
                if (Double.parseDouble(e.getText().toString()) > 25)
                    return "Please Enter quantity in 1-25 range.";
            }
        }
        return null;

    }

}