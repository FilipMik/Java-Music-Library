package cz.muni.fi.pa165.music_library.data.entities;

import java.util.Date;

public class Musician {
    private Integer musicianId;
    private String name;
    private Date birthDate;
    private Integer songsCreated;

    public Musician(Integer musicianId, String name, Date birthDate, Integer songsCreated) {
        this.musicianId = musicianId;
        this.name = name;
        this.birthDate = birthDate;
        this.songsCreated = songsCreated;
    }

    public void setSongsCreated(Integer songsCreated) {
        this.songsCreated = songsCreated;
    }

    public Integer getMusicianId() {
        return musicianId;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Integer getSongsCreated() {
        return songsCreated;
    }
}


