package com.lfy.lfy202120201124.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomVideoView extends VideoView {

    private MediaPlayer mediaPlayer;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        setOnPreparedListener(mp -> {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.setDisplay(getHolder());
                this.mediaPlayer.start();
            }
        });
    }
}
