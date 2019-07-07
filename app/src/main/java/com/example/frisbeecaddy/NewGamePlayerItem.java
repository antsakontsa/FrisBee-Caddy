package com.example.frisbeecaddy;

public class NewGamePlayerItem {
    private boolean mCheckBox;
    private String mText;

    public NewGamePlayerItem(boolean checkBox, String text) {
        mCheckBox = checkBox;
        mText = text;
    }

    public boolean getCheckBox() {
        return mCheckBox;
    }

    public String getmText() {
        return mText;
    }
}
