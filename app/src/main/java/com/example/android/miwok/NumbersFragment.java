package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    MediaPlayer mMediaPlayer;

    AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    resume();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    pause();
                    break;
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
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

            abandonAudioFocus();
        }
    }

    private void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
        }
    }

    private void resume() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    private int requestAudioFocus() {
        return mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    private void abandonAudioFocus() {
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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

        WordAdapter adapter =
                new WordAdapter(getActivity(), words, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Word currentWord = (Word)listView.getItemAtPosition(position);
                Word currentWord = (Word)parent.getAdapter().getItem(position);
                releaseMediaPlayer();
                int result = requestAudioFocus();
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), currentWord.getAudioResourceId());
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.start();
                }
            }
        });

        listView.setAdapter(adapter);

        return rootView;
    }

}
