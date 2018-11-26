package cz.muni.fi.pa165.music_library.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Ficko
 */

public class ArtistDto {

    private Long artistId;
    private String name;
    private Date birthDate;
    private String artistInfo;
    private List<SongDto> songList = new ArrayList<>();

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

    public List<SongDto> getSongList() {
        return songList;
    }

    public void setSongList(List<SongDto> songList) {
        this.songList = songList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtistDto)) return false;

        ArtistDto artist = (ArtistDto) o;

        if (!getName().equals(artist.getName())) return false;
        if (!getBirthDate().equals(artist.getBirthDate())) return false;
        return getArtistInfo().equals(artist.getArtistInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthDate(), getArtistInfo());
    }

}
