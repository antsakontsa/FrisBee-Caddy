package com.example.frisbeecaddy;

public class GameItem {
    private String mText1, mText2, mText3;
    private int mImageMinus, mImagePlus;

    public GameItem(String text1, String text2, int imageMinus, int imagePlus, String text3) {
        mText1 = text1;
        mText2 = text2;
        mImageMinus = imageMinus;
        mImagePlus = imagePlus;
        mText3 = text3;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
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

    public String getText3() {
        return mText3;
    }
}