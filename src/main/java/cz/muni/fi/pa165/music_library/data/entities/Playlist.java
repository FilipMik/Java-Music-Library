package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long playlistId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date dateCreated;

    @ManyToMany
    private List<Song> songList = new ArrayList<>();

    @ManyToOne
    private User user;

    public Playlist() {
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) throw new IllegalArgumentException("Playlist title can not be null!");
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        if (dateCreated == null) throw new IllegalArgumentException("Creation date of playlist cannot be null!");
        this.dateCreated = dateCreated;
    }

    public List<Song> getSongList() {
        return Collections.unmodifiableList(songList);
    }

    public void addSong(Song song) {
        if (song == null) throw new IllegalArgumentException("Added song cannot be null!");
        songList.add(song);
        song.addPlaylist(this);
    }

    public void removeSong(Song song) {
        songList.remove(song);
        song.removePlaylist(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;

        Playlist playlist = (Playlist) o;

        if (getTitle() != null ? !getTitle().equals(playlist.getTitle()) : playlist.getTitle() != null) return false;
        return getDateCreated() != null ? getDateCreated().equals(playlist.getDateCreated()) : playlist.getDateCreated() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        return result;
    }
}
