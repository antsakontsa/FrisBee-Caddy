package com.example.frisbeecaddy;

import android.widget.TextView;

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

    public int getImageMinus() {
        return mImageMinus;
    }

    public int getImagePlus() {
        return mImagePlus;
    }
}
