package com.example.frisbeecaddy;

import java.io.Serializable;

public class NameItem implements Serializable {
    private String mText1;

    public NameItem(String text1) {
        mText1 = text1;
    }

    public String getText1() {
        return mText1;
    }
}