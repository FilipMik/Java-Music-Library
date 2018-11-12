package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Filip Mik on 16. 10. 2018
 */

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songId;

    @Column(nullable = false, updatable = false)
    private String title;

    private Integer bitRate;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    private Integer albumPosition;

    private String commentary;

    private Genre genre;

    @ManyToMany(mappedBy = "songList")
    private List<Playlist> playlists = new ArrayList<>();

    public Song() {
    }

    public Song(String title, Integer bitRate, Artist artist, Album album, Integer albumPosition, String commentary, Genre genre, List<Playlist> playlists) {
        setTitle(title);
        setBitRate(bitRate);
        setArtist(artist);
        setAlbum(album);
        setAlbumPosition(albumPosition);
        setCommentary(commentary);
        setGenre(genre);
        setPlaylists(playlists);
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getAlbumPosition() {
        return albumPosition;
    }

    public void setAlbumPosition(Integer albumPosition) {
        this.albumPosition = albumPosition;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        this.playlists.remove(playlist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (!getTitle().equals(song.getTitle())) return false;
        if (!getBitRate().equals(song.getBitRate())) return false;
        if (!getAlbumPosition().equals(song.getAlbumPosition())) return false;
        if (!getCommentary().equals(song.getCommentary())) return false;
        return getGenre().equals(song.getGenre());
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getBitRate() != null ? getBitRate().hashCode() : 0);
        result = 31 * result + (getAlbumPosition() != null ? getAlbumPosition().hashCode() : 0);
        result = 31 * result + (getCommentary() != null ? getCommentary().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }
}
