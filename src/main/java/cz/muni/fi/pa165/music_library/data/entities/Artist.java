package cz.muni.fi.pa165.music_library.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jan Ficko
 */
@Entity
public class Artist {


    @Id
    private Long artistId;
    private String name;
    private Integer songCreated;
    private Date birthDate;
    private String artistInfo;

    public Artist() { }

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

    public Integer getSongCreated() {
        return songCreated;
    }

    public void setSongCreated(Integer songCreated) {
        this.songCreated = songCreated;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (artistId != null ? !artistId.equals(artist.artistId) : artist.artistId != null) return false;
        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
        if (songCreated != null ? !songCreated.equals(artist.songCreated) : artist.songCreated != null) return false;
        if (birthDate != null ? !birthDate.equals(artist.birthDate) : artist.birthDate != null) return false;
        return artistInfo != null ? artistInfo.equals(artist.artistInfo) : artist.artistInfo == null;
    }

    @Override
    public int hashCode() {
        int result = artistId != null ? artistId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (songCreated != null ? songCreated.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (artistInfo != null ? artistInfo.hashCode() : 0);
        return result;
    }
}
