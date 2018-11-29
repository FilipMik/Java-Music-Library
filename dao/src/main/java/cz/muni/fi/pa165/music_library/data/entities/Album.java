package cz.muni.fi.pa165.music_library.data.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author Filip Mik on 16. 10. 2018
 */

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long albumId;

    @Column(nullable = false)
    private Date releaseDate;

    @Column(nullable = false)
    private String title;

    private String commentary;

    private String albumArtUrl;

    @ManyToOne
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songList = new ArrayList<>();

    public Album() {
    }

    public Album(Date releaseDate, String title, String commentary, String albumArtUrl, Artist artist, List<Song> songList) {
        setReleaseDate(releaseDate);
        setTitle(title);
        setCommentary(commentary);
        setAlbumArtUrl(albumArtUrl);
        setArtist(artist);
        setSongList(songList);
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        if (releaseDate == null) throw new IllegalArgumentException("Release date cannot be null!");
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) throw new IllegalArgumentException("Album title cannot be null!");
        this.title = title;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArt) {
        this.albumArtUrl = albumArt;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getSongList() {
        return Collections.unmodifiableList(songList);
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void addSong(Song song) {
        if (song == null) throw new IllegalArgumentException("Cannot add null Song!");
        if (!this.songList.contains(song)) {
            this.songList.add(song);
        }
    }

    public void removeSong(Song song) {
        this.songList.remove(song);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (!getReleaseDate().equals(album.getReleaseDate())) return false;
        if (!getTitle().equals(album.getTitle())) return false;
        if (!getCommentary().equals(album.getCommentary())) return false;
        if (!getArtist().equals(album.getArtist())) return false;
        return getAlbumArtUrl().equals(album.getAlbumArtUrl());
    }

    @Override
    public int hashCode() {
        int result = getReleaseDate() != null ? getReleaseDate().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getCommentary() != null ? getCommentary().hashCode() : 0);
        result = 31 * result + (getAlbumArtUrl() != null ? getAlbumArtUrl().hashCode() : 0);
        result = 31 * result + (getArtist() != null ? getArtist().hashCode() : 0);
        return result;
    }
}

