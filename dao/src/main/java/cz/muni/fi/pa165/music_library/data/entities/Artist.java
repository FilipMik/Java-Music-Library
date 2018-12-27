package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Ficko
 */
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    @Column(nullable = false)
    private String name;

    private Date birthDate;

    private String artistInfo;

    @OneToMany(mappedBy = "artist")
    private List<Song> songList = new ArrayList<>();

    public Artist() {
    }

    public Artist(String name, Date birthDate, String artistInfo, List<Song> songList) {
        setName(name);
        setBirthDate(birthDate);
        setArtistInfo(artistInfo);
        setSongList(songList);
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Artist name cannot be null!");
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void addSong(Song song) {
        if (song == null) throw new IllegalArgumentException("Cannot add null song!");
        if (!this.songList.contains(song)) {
            this.songList.add(song);
        }
    }

    public void removeSong(Song song) {
        this.songList.remove(song);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (!getName().equals(artist.getName())) return false;
        if (!getBirthDate().equals(artist.getBirthDate())) return false;
        return getArtistInfo().equals(artist.getArtistInfo());
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getArtistInfo() != null ? getArtistInfo().hashCode() : 0);
        return result;
    }
}
