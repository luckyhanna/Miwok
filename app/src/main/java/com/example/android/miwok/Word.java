package com.example.android.miwok;

/**
 * Created by hanna on 4/29/2018.
 */

public class Word {

    private String mMiwokTranslation;

    private String mDefaultTranslation;

    public Word(String mMiwokTranslation, String mDefaultTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
}
