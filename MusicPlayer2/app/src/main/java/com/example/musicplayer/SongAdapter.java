package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SongAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<Song> arrSong;

    public SongAdapter(Context context, int layout, List<Song> songList) {
        myContext = context;
        myLayout = layout;
        arrSong = songList;
    }

    @Override
    public int getCount() {
        return arrSong.size();
    }

    @Override
    public Object getItem(int position) {
        HashMap<String, Integer> file = new HashMap<>();
        file.put(arrSong.get(position).getTitle(), arrSong.get(position).getFile());
        return file;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(myLayout, null);
        TextView songName = (TextView) convertView.findViewById(R.id.name);
        songName.setText(arrSong.get(position).getTitle());
        return convertView;
    }
}

