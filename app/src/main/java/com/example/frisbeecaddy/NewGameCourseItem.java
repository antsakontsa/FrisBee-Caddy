package com.example.frisbeecaddy;

public class NewGameCourseItem {

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

    public void setSelected(boolean isSelected) {
        mRadioButton = isSelected;
    }
}