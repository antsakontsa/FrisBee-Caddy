package com.example.frisbeecaddy;

public class ResumeItem {
    private boolean mRadioButton;
    private String mCourseName, mDate;

    public ResumeItem(boolean radioButton, String courseName, String date) {
        mRadioButton = radioButton;
        mCourseName = courseName;
        mDate = date;
    }

    public boolean getRadioButton() {
        return mRadioButton;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public String getDate() {
        return mDate;
    }
}
