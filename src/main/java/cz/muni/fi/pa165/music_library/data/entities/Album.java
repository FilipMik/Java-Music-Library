package cz.muni.fi.pa165.music_library.data.entities;

import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Filip Mik on 16. 10. 2018
 */

@Entity
public class Album {

    @Id
    private Long albumId;

    private Date releaseDate;

    private String title;

    private String commentary;

    private Image albumArt;

    @OneToMany()
    private List<Song> songList;

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
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Image getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(Image albumArt) {
        this.albumArt = albumArt;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
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
        return getAlbumArt() != null ? getAlbumArt().equals(album.getAlbumArt()) : album.getAlbumArt() == null;
    }

    @Override
    public int hashCode() {
        int result = getReleaseDate() != null ? getReleaseDate().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getCommentary() != null ? getCommentary().hashCode() : 0);
        result = 31 * result + (getAlbumArt() != null ? getAlbumArt().hashCode() : 0);
        return result;
    }
}

