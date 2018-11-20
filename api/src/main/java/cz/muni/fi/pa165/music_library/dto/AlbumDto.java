package cz.muni.fi.pa165.music_library.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AlbumDto {

    private Long albumId;
    private Date releaseDate;
    private String title;
    private String commentary;
    private String albumArtUrl;
    private List<SongDto> songList = new ArrayList<>();

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

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    public List<SongDto> getSongList() {
        return songList;
    }

    public void setSongList(List<SongDto> songList) {
        this.songList = songList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumDto)) return false;

        AlbumDto album = (AlbumDto) o;

        if (!getReleaseDate().equals(album.getReleaseDate())) return false;
        if (!getTitle().equals(album.getTitle())) return false;
        if (!getCommentary().equals(album.getCommentary())) return false;
        return getAlbumArtUrl().equals(album.getAlbumArtUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReleaseDate(), getTitle(), getCommentary(), getAlbumArtUrl());
    }
}
