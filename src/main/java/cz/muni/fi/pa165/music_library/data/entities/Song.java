package cz.muni.fi.pa165.music_library.data.entities;

/**
 * @author Filip Mik on 16. 10. 2018
 */

public class Song {
    private Long songId;
    private String title;
    private Integer bitRate;
    private Integer albumPosition;
    private String commentary;
    private Genre genre;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (songId != null ? !songId.equals(song.songId) : song.songId != null) return false;
        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (bitRate != null ? !bitRate.equals(song.bitRate) : song.bitRate != null) return false;
        if (albumPosition != null ? !albumPosition.equals(song.albumPosition) : song.albumPosition != null)
            return false;
        if (commentary != null ? !commentary.equals(song.commentary) : song.commentary != null) return false;
        return genre == song.genre;
    }

    @Override
    public int hashCode() {
        int result = songId != null ? songId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (bitRate != null ? bitRate.hashCode() : 0);
        result = 31 * result + (albumPosition != null ? albumPosition.hashCode() : 0);
        result = 31 * result + (commentary != null ? commentary.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
