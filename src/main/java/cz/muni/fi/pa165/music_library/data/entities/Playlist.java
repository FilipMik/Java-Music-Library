package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Entity
public class Playlist {

    @Id
    private Long playlistId;

    private String title;

    private Date dateCreated;

    @OneToMany
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
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
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
