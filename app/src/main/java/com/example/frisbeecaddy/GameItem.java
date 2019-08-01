package com.example.frisbeecaddy;

public class GameItem {
    private String mText1, mText2, mText3, mText4;
    private int mImageMinus, mImagePlus;

    public GameItem(String text1, String text2, int imageMinus, int imagePlus, String text3, String text4) {
        mText1 = text1;
        mText2 = text2;
        mImageMinus = imageMinus;
        mImagePlus = imagePlus;
        mText3 = text3;
        mText4 = text4;
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

    public String getText4() {
        return mText4;
    }

    public void changeText4(String text) {
        mText4 = text;
    }
}