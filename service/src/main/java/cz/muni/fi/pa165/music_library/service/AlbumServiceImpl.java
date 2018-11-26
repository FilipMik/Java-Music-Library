package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
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
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private TimeService timeService;

    @Override
    public Album getAlbumById(Long albumId) {
        return albumDao.getAlbumById(albumId);
    }

    @Override
    public List<Album> getAlbumByTitle(String title) {
        return albumDao.getAlbumByTitle(title);
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
    public void createAlbum(Album album) {
        albumDao.createAlbum(album);
    }

    @Override
    public List<Album> getLastWeekAlbums() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeService.getCurrentDate());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = calendar.getTime();
        return albumDao.getAllAlbumsBetween(lastWeek,timeService.getCurrentDate());
    }
}
