package com.example.frisbeecaddy;

public class CoursesItem {
    private String mCourseName, mHolesTxt, mHolesNm;
    private int mDelete;

    public CoursesItem(String courseName, String holesTxt, String holesNm, int delete) {
        mCourseName = courseName;
        mHolesTxt = holesTxt;
        mHolesNm = holesNm;
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

    public int getDelete() {
        return mDelete;
    }
}
