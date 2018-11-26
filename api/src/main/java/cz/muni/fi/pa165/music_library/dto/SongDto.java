package cz.muni.fi.pa165.music_library.dto;

import java.util.*;

/**
 * @author Klara Minsterova
 */

public class SongDto {

    private Long songId;

    private String title;

    private Integer bitRate;

    private ArtistDto artist;

    private AlbumDto album;

    private Integer albumPosition;

    private String commentary;

    private Genre genre;

    private List<PlaylistDto> playlists = new ArrayList<>();

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

    public ArtistDto getArtist() {
        return artist;
    }

    public void setArtist(ArtistDto artist) {
        this.artist = artist;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
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

    public List<PlaylistDto> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDto> playlists) {
        this.playlists = playlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongDto)) return false;

        SongDto songDto = (SongDto) o;

        if (!getTitle().equals(songDto.getTitle())) return false;
        if (getBitRate() != null ? !getBitRate().equals(songDto.getBitRate()) : songDto.getBitRate() != null)
            return false;
        if (getAlbumPosition() != null ? !getAlbumPosition().equals(songDto.getAlbumPosition()) : songDto.getAlbumPosition() != null)
            return false;
        if (getCommentary() != null ? !getCommentary().equals(songDto.getCommentary()) : songDto.getCommentary() != null)
            return false;
        return getGenre() == songDto.getGenre();
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + (getBitRate() != null ? getBitRate().hashCode() : 0);
        result = 31 * result + (getAlbumPosition() != null ? getAlbumPosition().hashCode() : 0);
        result = 31 * result + (getCommentary() != null ? getCommentary().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }
}
