package com.example.frisbeecaddy;

import java.io.Serializable;

public class CoursesItem implements Serializable {
    private String mCourseName, mHolesTxt, mHolesNm, mParTxt, mParNm;
    private int mDelete;

    public CoursesItem(String courseName, String holesTxt, String holesNm, String parTxt, String parNm, int delete) {
        mCourseName = courseName;
        mHolesTxt = holesTxt;
        mHolesNm = holesNm;
        mParTxt = parTxt;
        mParNm = parNm;
        mDelete = delete;
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

    public int getDelete() {
        return mDelete;
    }
}