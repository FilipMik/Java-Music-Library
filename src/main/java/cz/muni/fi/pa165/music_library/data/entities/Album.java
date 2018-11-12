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

    @OneToMany(mappedBy = "album")
    private List<Song> songList = new ArrayList<>();

    public Album() {
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
        if (releaseDate == null) throw new IllegalArgumentException("Album title cannot be null!");
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

    public List<Song> getSongList() {
        return Collections.unmodifiableList(songList);
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

        if (getReleaseDate() != null ? !getReleaseDate().equals(album.getReleaseDate()) : album.getReleaseDate() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(album.getTitle()) : album.getTitle() != null) return false;
        if (getCommentary() != null ? !getCommentary().equals(album.getCommentary()) : album.getCommentary() != null)
            return false;
        return getAlbumArtUrl() != null ? getAlbumArtUrl().equals(album.getAlbumArtUrl()) : album.getAlbumArtUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getReleaseDate() != null ? getReleaseDate().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getCommentary() != null ? getCommentary().hashCode() : 0);
        result = 31 * result + (getAlbumArtUrl() != null ? getAlbumArtUrl().hashCode() : 0);
        return result;
    }
}

