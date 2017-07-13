package com.example.romana.grm;

import java.io.Serializable;

/**
 * Created by Wahlah on 3/23/2017.
 */
public class ChildRow implements Serializable {
    private String engName;
    private String urduName;
    private int img;
    private String cholestrol;
    private String Vitamin;

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getUrduName() {
        return urduName;
    }

    public void setUrduName(String urduName) {
        this.urduName = urduName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCholestrol() {
        return cholestrol;
    }

    public void setCholestrol(String cholestrol) {
        this.cholestrol = cholestrol;
    }

    public String getVitamin() {
        return Vitamin;
    }

    public void setVitamin(String vitamin) {
        Vitamin = vitamin;
    }

    public ChildRow(String engName, String cholesterol, String Vitamin, int image, String urduName) {

        this.engName = engName;
        this.urduName = urduName;
        this.img = image;
        this.cholestrol = cholesterol;
        this.Vitamin = Vitamin;
    }
}
