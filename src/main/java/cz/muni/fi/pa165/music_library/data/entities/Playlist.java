package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.*;
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
    private List<Song> songList;

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
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Song> getSongList() {
        return Collections.unmodifiableList(songList);
    }

    public void addSong(Song song) {
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
