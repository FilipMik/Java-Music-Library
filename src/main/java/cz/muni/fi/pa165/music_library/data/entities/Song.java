package cz.muni.fi.pa165.music_library.data.entities;

public class Song {
    private Integer songId;
    private String title;
    private Integer bitRate;
    private Integer albumPosition;
    private String commentary;
    private Genre genre;

    public Song(Integer songId, String title, Integer bitRate, Integer albumPosition, String commentary, Genre genre) {
        this.songId = songId;
        this.title = title;
        this.bitRate = bitRate;
        this.albumPosition = albumPosition;
        this.commentary = commentary;
        this.genre = genre;
    }

    public Integer getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public Integer getAlbumPosition() {
        return albumPosition;
    }

    public String getCommentary() {
        return commentary;
    }

    public Genre getGenre() {
        return genre;
    }
}
