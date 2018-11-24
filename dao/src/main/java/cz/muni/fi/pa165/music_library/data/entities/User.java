package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.*;
import java.util.*;

/**
 * @author Klara Minsterova
 */

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date dateCreated;

    private UserLevel userLevel;

    @OneToMany(mappedBy = "user")
    List<Playlist> playlists = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password, Date dateCreated, UserLevel userLevel, List<Playlist> playlists) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setDateCreated(dateCreated);
        setUserLevel(userLevel);
        setPlaylists(playlists);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        if (userName == null) throw new IllegalArgumentException("Username can not be null!");
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("User email can not be null!");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("User password can not be null!");
        if (password.length() < 6)
            throw new IllegalArgumentException("User password must be at least 6 characters long");
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        if (dateCreated == null) throw new IllegalArgumentException("Date of creation can not be null!");
        this.dateCreated = dateCreated;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist == null) throw new IllegalArgumentException("Can not add null playlist!");
        if (!this.playlists.contains(playlist)) {
            this.playlists.add(playlist);
        }
    }

    public void removePlaylist(Playlist playlist) {
        this.playlists.remove(playlist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getDateCreated().equals(user.getDateCreated())) return false;
        return getUserLevel().equals(user.getUserLevel());
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        result = 31 * result + (getUserLevel() != null ? getUserLevel().hashCode() : 0);
        return result;
    }
}
