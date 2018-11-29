package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.service.AlbumService;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

public class AlbumFacadeImpl implements AlbumFacade{

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return (albums == null) ? null : mapper.mapTo(albums,AlbumDto.class);
    }

    @Override
    public AlbumDto findAlbumById(Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        return (album == null) ? null : mapper.mapTo(album,AlbumDto.class);
    }

    @Override
    public ArtistDto findAlbumArtist(Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        Artist artist = album.getArtist();
        return (artist == null) ? null : mapper.mapTo(artist,ArtistDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByTitle(String title) {
        List<Album> albums = albumService.getAlbumsByTitle(title);
        return (albums == null) ? null : mapper.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByArtist(String artistName) {
        List<Album> albums = albumService.getAlbumsByArtistName(artistName);
        return (albums == null) ? null : mapper.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByArtistId(Long artistId) {
        List<Album> albums = albumService.getAlbumsByArtist(artistId);
        return (albums == null) ? null : mapper.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> getLastWeekAlbums() {
        List<Album> albums = albumService.getLastWeekAlbums();
        return (albums == null) ? null : mapper.mapTo(albums,AlbumDto.class);
    }

    @Override
    public void createAlbum(AlbumDto album) {
        if (album == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Album albumUpdate = mapper.mapTo(album,Album.class);
        albumService.createAlbum(albumUpdate);
    }

    @Override
    public void updateAlbum(AlbumDto album) {
        if (album == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Album albumUpdate = mapper.mapTo(album,Album.class);
        albumService.updateAlbum(albumUpdate);
    }

    @Override
    public void addSongs(Long albumId, List<Long> songIds) {
        albumService.addSongs(albumId,songIds);
    }

    @Override
    public void addSong(Long albumId, Long songId) {
        albumService.addSong(albumId,songId);
    }

    @Override
    public void removeSong(Long albumId, Long songId) {
        albumService.removeSong(albumId,songId);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        albumService.deleteAlbum(album);
    }
}
