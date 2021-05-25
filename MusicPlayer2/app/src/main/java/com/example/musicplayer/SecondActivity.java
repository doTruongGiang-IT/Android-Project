package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    TextView title, timeStart, timeEnd;
    SeekBar skSong;
    ImageButton btnPrev, btnPlay, btnStop, btnNext;
    ArrayList<Song> arrSong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView disk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initSong();
        addSong();
        animation = AnimationUtils.loadAnimation(this, R.anim.disk);
        initMediaPlayer();

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.icon_play);
                mediaPlayer = MediaPlayer.create(SecondActivity.this, arrSong.get(position).getFile());
                title.setText(arrSong.get(position).getTitle());
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.icon_play);

                }else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.icon_pause);
                }
                setTimeSong();
                updateTimeSong();
                disk.startAnimation(animation);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                position ++;
                if(position > arrSong.size() - 1) {
                    position = 0;
                }
                mediaPlayer = MediaPlayer.create(SecondActivity.this, arrSong.get(position).getFile());
                title.setText(arrSong.get(position).getTitle());
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.icon_pause);
                setTimeSong();
                updateTimeSong();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                position --;
                if(position < 0) {
                    position = arrSong.size() - 1;
                }
                mediaPlayer = MediaPlayer.create(SecondActivity.this, arrSong.get(position).getFile());
                title.setText(arrSong.get(position).getTitle());
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.icon_pause);
                setTimeSong();
                updateTimeSong();
            }
        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    public void initSong() {
        title = (TextView) findViewById(R.id.title);
        timeStart = (TextView) findViewById(R.id.start);
        timeEnd = (TextView) findViewById(R.id.end);
        skSong =  (SeekBar) findViewById(R.id.seekBar);
        btnPrev = (ImageButton) findViewById(R.id.prevButton);
        btnPlay = (ImageButton) findViewById(R.id.playButton);
        btnStop = (ImageButton) findViewById(R.id.stopButton);
        btnNext = (ImageButton) findViewById(R.id.nextButton);
        disk = (ImageView) findViewById(R.id.disk);
    }

    public void setTimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        timeEnd.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skSong.setMax(mediaPlayer.getDuration());
    }

    public void updateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                timeStart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        position ++;
                        if(position > arrSong.size() - 1) {
                            position = 0;
                        }
                        initMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.icon_pause);
                        setTimeSong();
                        updateTimeSong();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    public void addSong() {
        arrSong = new ArrayList<>();
        arrSong.add(new Song("All of the star", R.raw.all_of_the_stars));
        arrSong.add(new Song("Apologise", R.raw.apologize));
        arrSong.add(new Song("Dancing on my own", R.raw.dacing_on_my_own));
        arrSong.add(new Song("Dollhouse", R.raw.dollhouse));
        arrSong.add(new Song("Heroes Tonight", R.raw.heroes_tonight));
        arrSong.add(new Song("How to save a live", R.raw.how_to_save_a_live));
        arrSong.add(new Song("Jealous", R.raw.jealouss));
        arrSong.add(new Song("Monody", R.raw.monody));
        arrSong.add(new Song("No matter what", R.raw.no_matter_what));
        arrSong.add(new Song("Take me home country road", R.raw.take_me_home_country_road));
        arrSong.add(new Song("Queen's Moon", R.raw.tan_thoi_minh_nguyet));
        arrSong.add(new Song("The sound of silence", R.raw.the_sound_of_silence));
    }

    public void initMediaPlayer() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String musicFile = bundle.getString("musicFile");
            musicFile = musicFile.substring(1, musicFile.length()-1);
            String[] details = musicFile.split("=");
            for (String info : details) {
                if(isNumeric(info) == false) {
                    title.setText(info);
                    for(int i = 0; i < arrSong.size() - 1; i++) {
                        if(info.contains(arrSong.get(i).getTitle())) {
                            position = i;
                            mediaPlayer = MediaPlayer.create(SecondActivity.this, arrSong.get(position).getFile());
                            mediaPlayer.start();
                            btnPlay.setImageResource(R.drawable.icon_pause);
                            setTimeSong();
                            updateTimeSong();
                            disk.startAnimation(animation);
                        }
                    }
                }
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}