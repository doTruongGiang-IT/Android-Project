package com.example.musicplayer;

public class Song {

    private String title;
    private int File;

    public Song(String title, int file) {
        this.title = title;
        File = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }
}
