package com.example.frisbeecaddy;

public class NewCourseItem {
    private String mText1, mText2;
    private int mImageMinus, mImagePlus;

    public NewCourseItem(String text1, String text2, int imageMinus, int imagePlus) {
        mText1 = text1;
        mText2 = text2;
        mImageMinus = imageMinus;
        mImagePlus = imagePlus;
    }

    public String getText1 () {
        return mText1;
    }

    public String getText2 () {
        return mText2;
    }

    public void changeText2(String text) {
        mText2 = text;
    }

    public int getImageMinus() {
        return mImageMinus;
    }

    public int getImagePlus() {
        return mImagePlus;
    }
}
