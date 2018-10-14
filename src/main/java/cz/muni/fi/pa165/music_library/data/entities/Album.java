package cz.muni.fi.pa165.music_library.data.entities;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class Album {
    private Integer albumId;
    private Date releaseDate;
    private String title;
    private String commentary;
    private List<Song> songList;
    private Integer songsCount;
    private Image albumArt;

}
