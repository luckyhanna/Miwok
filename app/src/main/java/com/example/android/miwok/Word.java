package com.example.android.miwok;

/**
 * Created by hanna on 4/29/2018.
 */

public class Word {

    private String mMiwokTranslation;

    private String mDefaultTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private int mAudioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String miwokTranslation, String defaultTranslation, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /*public Word(String miwokTranslation, String defaultTranslation, int imageResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceId = imageResourceId;
    }*/

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
