package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        words.add(new Word("lutti","one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko","two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu","three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa","four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka","five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka","six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku","seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta","eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo’e","nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na’aacha","ten", R.drawable.number_ten, R.raw.number_ten));

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        /*for (String word : words) {
            TextView wordView = new TextView(this);
            wordView.setText(word);
            rootView.addView(wordView);
        }*/

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter =
                new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Word currentWord = (Word)listView.getItemAtPosition(position);
                Word currentWord = (Word)parent.getAdapter().getItem(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, currentWord.getAudioResourceId());
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                mMediaPlayer.start();
            }
        });

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
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
