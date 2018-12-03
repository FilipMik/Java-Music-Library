package cz.muni.fi.pa165.music_library.dto;

import java.util.*;

/**
 * @author Klara Minsterova
 */

public class UserDto {

    private Long userId;

    private String username;

    private String email;

    private String password;

    private Date dateCreated;

    private UserLevelDto userLevel;

    List<PlaylistDto> playlists = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserLevelDto getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevelDto userLevel) {
        this.userLevel = userLevel;
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
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (!getUsername().equals(userDto.getUsername())) return false;
        if (!getEmail().equals(userDto.getEmail())) return false;
        if (!getDateCreated().equals(userDto.getDateCreated())) return false;
        return getUserLevel() == userDto.getUserLevel();
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getUserLevel().hashCode();
        return result;
    }
}
