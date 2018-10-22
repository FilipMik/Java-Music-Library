package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author Filip Mik on 16. 10. 2018
 */

@Entity
public class Song {

    @Id
    private Long songId;

    private String title;

    private Integer bitRate;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    private Integer albumPosition;

    private String commentary;

    private Genre genre;

    @ManyToMany
    private List<Playlist> playlists;

    public Song() {
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
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (getTitle() != null ? !getTitle().equals(song.getTitle()) : song.getTitle() != null) return false;
        if (getBitRate() != null ? !getBitRate().equals(song.getBitRate()) : song.getBitRate() != null) return false;
        if (getAlbumPosition() != null ? !getAlbumPosition().equals(song.getAlbumPosition()) : song.getAlbumPosition() != null)
            return false;
        if (getCommentary() != null ? !getCommentary().equals(song.getCommentary()) : song.getCommentary() != null)
            return false;
        return getGenre() == song.getGenre();
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
