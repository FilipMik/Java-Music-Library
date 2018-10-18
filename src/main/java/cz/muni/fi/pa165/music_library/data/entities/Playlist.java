package cz.muni.fi.pa165.music_library.data.entities;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */
public class Playlist {

    private Integer playlistId;
    private String title;
    private Date dateCreated;
    private Integer songsCount;
    private List<Song> songList;

    public Playlist() {
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getSongsCount() {
        return songsCount;
    }

    public void setSongsCount(Integer songsCount) {
        this.songsCount = songsCount;
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
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        if (playlistId != null ? !playlistId.equals(playlist.playlistId) : playlist.playlistId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(playlist.dateCreated) : playlist.dateCreated != null) return false;
        if (title != null ? !title.equals(playlist.title) : playlist.title != null) return false;
        if (songList != null ? !songList.equals(playlist.songList) : playlist.songList != null) return false;
        return songsCount != null ? songsCount.equals(playlist.songsCount) : playlist.songsCount == null;
    }

    @Override
    public int hashCode() {
        int result = playlistId != null ? playlistId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (songsCount != null ? songsCount.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (songList != null ? songList.hashCode() : 0);
        return result;
    }
}
