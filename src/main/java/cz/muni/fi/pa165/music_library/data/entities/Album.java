package cz.muni.fi.pa165.music_library.data.entities;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class Album {
    private Integer albumId;
    private Date releaseDate;
    private String title;
    private String commentary;
    private List<Song> songList;
    private Integer songsCount;
    private Image albumArt;

    public Album(Integer albumId, Date releaseDate, String title, String commentary, List<Song> songList, Image albumArt) {
        this.albumId = albumId;
        this.releaseDate = releaseDate;
        this.title = title;
        this.commentary = commentary;
        this.songList = songList;
        this.songsCount = songList.size();
        this.albumArt = albumArt;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getCommentary() {
        return commentary;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Integer getSongsCount() {
        return songsCount;
    }

    public Image getAlbumArt() {
        return albumArt;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
