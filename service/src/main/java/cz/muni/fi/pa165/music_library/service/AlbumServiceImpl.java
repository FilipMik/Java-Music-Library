package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumDao albumDao;
    private ArtistDao artistDao;
    private SongDao songDao;
    private TimeService timeService;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao, ArtistDao artistDao, SongDao songDao, TimeService timeService) {
        this.albumDao = albumDao;
        this.artistDao = artistDao;
        this.songDao = songDao;
        this.timeService = timeService;
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return albumDao.getAlbumById(albumId);
    }

    @Override
    public Artist getAlbumArtist(Long albumId) {
        return albumDao.getAlbumById(albumId).getArtist();
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return albumDao.getAlbumByTitle(title);
    }

    @Override
    public List<Album> getAlbumsByArtist(Long artistId) {
        return artistDao.getArtistById(artistId).getAlbumList();
    }

    @Override
    public List<Album> getAlbumsByArtistName(String artistName) {
        List<Artist> artists = artistDao.getArtistsByName(artistName);
        List<Album> albums = new ArrayList<>();
        for (Artist artist : artists) {
            albums.addAll(artist.getAlbumList());
        }
        return albums;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumDao.getAllAlbums();
    }

    @Override
    public void deleteAlbum(Album album) {
        albumDao.deleteAlbum(album);
    }

    @Override
    public void updateAlbum(Album album) {
        albumDao.updateAlbum(album);
    }

    @Override
    public void addSongs(Long albumId, List<Long> songIds) {
        Album album = albumDao.getAlbumById(albumId);
        for (Long songId : songIds) {
            album.addSong(songDao.getSongById(songId));
        }
        albumDao.updateAlbum(album);
    }

    @Override
    public void addSong(Long albumId, Long songId) {
        Album album = albumDao.getAlbumById(albumId);
        album.addSong(songDao.getSongById(songId));
        albumDao.updateAlbum(album);
    }

    @Override
    public void removeSong(Long albumId, Long songId) {
        Album album = albumDao.getAlbumById(albumId);
        album.removeSong(songDao.getSongById(songId));
        albumDao.updateAlbum(album);
    }

    @Override
    public void createAlbum(Album album) {
        albumDao.createAlbum(album);
    }

    @Override
    public List<Album> getLastWeekAlbums() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeService.getCurrentDate());
        calendar.add(Calendar.DAY_OF_YEAR, -Calendar.DAY_OF_WEEK);
        Date lastWeek = calendar.getTime();
        return albumDao.getAllAlbumsBetween(lastWeek, timeService.getCurrentDate());
    }
}
