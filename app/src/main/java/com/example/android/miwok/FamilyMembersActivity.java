package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an ArrayList of words
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("әpә","father", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("әṭa","mother", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("angsi","son", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("tune","daughter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("taachi","older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("chalitti","younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("teṭe","older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("kolliti","younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("ama","grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("paapa","grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter =
                new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = (Word)parent.getAdapter().getItem(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, currentWord.getAudioResourceId());
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                mMediaPlayer.start();
            }
        });

        listView.setAdapter(adapter);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
