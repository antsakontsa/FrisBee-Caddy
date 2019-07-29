package com.example.frisbeecaddy;

import java.io.Serializable;

public class NewGameCourseItem implements Serializable {

    private boolean mRadioButton;
    private String mCourseName, mHolesTxt, mHolesNm, mParTxt, mParNm;

    public NewGameCourseItem(boolean radioButton, String courseName, String holesTxt, String holesNm, String parTxt, String parNm) {
        mRadioButton = radioButton;
        mCourseName = courseName;
        mHolesTxt = holesTxt;
        mHolesNm = holesNm;
        mParTxt = parTxt;
        mParNm = parNm;
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