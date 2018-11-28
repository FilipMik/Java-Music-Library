package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private TimeService timeService;

    @Override
    public Album getAlbumById(Long albumId) {
        return albumDao.getAlbumById(albumId);
    }

    @Override
    public Artist getAlbumArtist(Long albumId) {
        // TODO
        return new Artist();
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return albumDao.getAlbumByTitle(title);
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistName) {
        // TODO
        return null;
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
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = calendar.getTime();
        return albumDao.getAllAlbumsBetween(lastWeek, timeService.getCurrentDate());
    }

    @Override
    public Artist getAlbumArtist(Album album) {
        return albumDao.getAlbumById(album.getAlbumId()).getArtist();
    }

    @Override
    public List<Album> getAlbumsByArtist(Artist artist) {
        return artistDao.getArtistById(artist.getArtistId()).getAlbumList();
    }
}
