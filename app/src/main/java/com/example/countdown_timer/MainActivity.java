package com.example.countdown_timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer timer;
    private SeekBar seekbar;
    private TextView txt;

    public void playmusic() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.timersound);
        mp.start();
    }

    public void countdown(long msecs) {
        timer = new CountDownTimer(seekbar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                final int totalSecs = (int) l / 1000;
                final String mins = Integer.toString(totalSecs / 60);
                final String secs = Integer.toString(totalSecs % 60);
                txt.setText(mins + ':' + secs);
            }

            @Override
            public void onFinish() {
                playmusic();
                seekbar.setEnabled(true);
            }
        };
        timer.start();
    }

    public void start(View view) {
        Button strt = findViewById(R.id.button);
        strt.setVisibility(View.GONE);
        Button stop = findViewById(R.id.button2);
        stop.setVisibility(View.VISIBLE);
        long msecs = seekbar.getProgress();
        seekbar.setEnabled(false);
        countdown(msecs);
    }

    public void stop(View view) {
        Button strt = findViewById(R.id.button);
        strt.setVisibility(View.VISIBLE);
        Button stp = findViewById(R.id.button2);
        stp.setVisibility(View.GONE);
        timer.cancel();
        seekbar.setEnabled(true);
        seekbar.setProgress(600);
        txt.setText(10 + ":" + 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = findViewById(R.id.seekbar);
        seekbar.setProgress(600);
        txt = findViewById(R.id.text1);
        txt.setText(10 + ":" + 0);
        seekbar.setMax(1800);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                final int mins = i / 60;
                final int secs = i % 60;
                txt.setText(mins + ":" + secs);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}