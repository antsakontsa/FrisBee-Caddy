package com.example.frisbeecaddy;

import java.io.Serializable;
import java.util.ArrayList;

public class NewGameCourseItem implements Serializable {

    private boolean mRadioButton;
    private ArrayList<String> mParNumbersIndividually;
    private String mCourseName, mHolesTxt, mHolesNm, mParTxt, mParNm;

    public NewGameCourseItem(ArrayList<String> parNumbersIndividually,boolean radioButton, String courseName, String holesTxt, String holesNm, String parTxt, String parNm) {
        mParNumbersIndividually = parNumbersIndividually;
        mRadioButton = radioButton;
        mCourseName = courseName;
        mHolesTxt = holesTxt;
        mHolesNm = holesNm;
        mParTxt = parTxt;
        mParNm = parNm;
    }

    public ArrayList<String> getParNumbersIndividually() {
        return mParNumbersIndividually;
    }

    public boolean getRadioButton() {
        return mRadioButton;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public String getHolesTxt() {
        return mHolesTxt;
    }

    public String getHolesNm() {
        return mHolesNm;
    }

    public String getParTxt() {
        return mParTxt;
    }

    public String getParNm() {
        return mParNm;
    }
}