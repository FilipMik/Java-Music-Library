package cz.muni.fi.pa165.music_library.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Ficko
 */

public class PlaylistDto {

    private Long playlistId;

    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    private Date dateCreated;

    private List<SongDto> songList = new ArrayList<>();

    private UserDto user;

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

    public List<SongDto> getSongList() {
        return songList;
    }

    public void setSongList(List<SongDto> songList) {
        this.songList = songList;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistDto)) return false;

        PlaylistDto playlist = (PlaylistDto) o;

        if (!getTitle().equals(playlist.getTitle())) return false;
        return getDateCreated().equals(playlist.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDateCreated());
    }
}
