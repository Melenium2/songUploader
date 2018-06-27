package com.uploader.Model;

public class SongModel
{
    private String songArtist;
    private String sontTitle;
    private Long songDuration;
    private String songPath;
    private String songPicture;

    public SongModel(String songArtist, String sontTitle, Long songDuration, String songPath, String songPicture)
    {
        this.songArtist = songArtist;
        this.sontTitle = sontTitle;
        this.songDuration = songDuration;
        this.songPath = songPath;
        this.songPicture = songPicture;
    }

    public SongModel() {
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSontTitle() {
        return sontTitle;
    }

    public void setSontTitle(String sontTitle) {
        this.sontTitle = sontTitle;
    }

    public Long getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Long songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getSongPicture() {
        return songPicture;
    }

    public void setSongPicture(String songPicture) {
        this.songPicture = songPicture;
    }
}
