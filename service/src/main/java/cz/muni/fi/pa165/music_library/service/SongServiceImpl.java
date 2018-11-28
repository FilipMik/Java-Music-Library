package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Mik on 22. 11. 2018
 */

@Service
public class SongServiceImpl implements SongService {

    private final SongDao songDao;

    @Autowired
    public SongServiceImpl(SongDao songDao) {
        this.songDao = songDao;
    }

    @Override
    public List<Song> getAllSongs() {
        return songDao.getAllSongs();
    }

    @Override
    public List<Song> getSongsByTitle(String title) {
        return songDao.getSongsByTitle(title);
    }

    @Override
    public List<Song> getSongsByArtist(String artistName) {
        return songDao.getSongsByArtist(artistName);
    }

    @Override
    public List<Song> getSongsByGenre(Genre genre) {
        return songDao.getSongsByGenre(genre);
    }

    @Override
    public List<Song> getSongsByAlbum(String albumTitle) {
        return songDao.getSongsByAlbum(albumTitle);
    }

    @Override
    public List<Song> getAllTimeTopSongs(Integer numberOfSongs, Genre genre) {
        List<Song> list = songDao.getAllSongsByRating(genre);
        if (numberOfSongs > list.size()) {
            return list;
        } else {
            return list.subList(0, numberOfSongs);
        }
    }

    @Override
    public Song getSongById(Long songId) {
        return songDao.getSongById(songId);
    }

    @Override
    public void createSong(Song song) {
        songDao.createSong(song);
    }

    @Override
    public void deleteSong(Song song) {
        songDao.deleteSong(song);
    }

    @Override
    public void updateSong(Song song) {
        songDao.updateSong(song);
    }
}
