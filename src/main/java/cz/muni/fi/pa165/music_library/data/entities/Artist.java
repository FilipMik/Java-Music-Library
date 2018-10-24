package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Ficko
 */
@Entity
public class Artist {

    @Id
    private Long artistId;

    private String name;

    private Date birthDate;

    private String artistInfo;

    @OneToMany()
    private List<Song> songList;

    public Artist() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (getName() != null ? !getName().equals(artist.getName()) : artist.getName() != null) return false;
        if (getBirthDate() != null ? !getBirthDate().equals(artist.getBirthDate()) : artist.getBirthDate() != null)
            return false;
        return getArtistInfo() != null ? getArtistInfo().equals(artist.getArtistInfo()) : artist.getArtistInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getArtistInfo() != null ? getArtistInfo().hashCode() : 0);
        return result;
    }
}
