package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<Song> arrSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();
    }

    public void setListView() {
        lv = (ListView) findViewById(R.id.list);
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

        SongAdapter adapter = new SongAdapter(MainActivity.this, R.layout.list_music, arrSong);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("musicFile", adapter.getItem(position).toString());
                startActivity(intent);
            }
        });
    }
}