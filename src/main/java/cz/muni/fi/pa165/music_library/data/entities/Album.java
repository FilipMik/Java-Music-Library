package cz.muni.fi.pa165.music_library.data.entities;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * @author Filip Mik on 16. 10. 2018
 */

public class Album {
    private Long albumId;
    private Date releaseDate;
    private String title;
    private String commentary;
    private List<Song> songList;
    private Integer songsCount;
    private Image albumArt;

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

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
        this.songsCount = songList.size();
    }

    public Integer getSongsCount() {
        return songsCount;
    }

    public Image getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(Image albumArt) {
        this.albumArt = albumArt;
    }

    public List<Song> getSongs() {
        return this.songList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (albumId != null ? !albumId.equals(album.albumId) : album.albumId != null) return false;
        if (releaseDate != null ? !releaseDate.equals(album.releaseDate) : album.releaseDate != null) return false;
        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (commentary != null ? !commentary.equals(album.commentary) : album.commentary != null) return false;
        if (songList != null ? !songList.equals(album.songList) : album.songList != null) return false;
        if (songsCount != null ? !songsCount.equals(album.songsCount) : album.songsCount != null) return false;
        return albumArt != null ? albumArt.equals(album.albumArt) : album.albumArt == null;
    }

    @Override
    public int hashCode() {
        int result = albumId != null ? albumId.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (commentary != null ? commentary.hashCode() : 0);
        result = 31 * result + (songList != null ? songList.hashCode() : 0);
        result = 31 * result + (songsCount != null ? songsCount.hashCode() : 0);
        result = 31 * result + (albumArt != null ? albumArt.hashCode() : 0);
        return result;
    }
}
