package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.service.AlbumService;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import cz.muni.fi.pa165.music_library.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
@Transactional
public class AlbumFacadeImpl implements AlbumFacade{

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return (albums == null) ? null : beanMappingService.mapTo(albums,AlbumDto.class);
    }

    @Override
    public AlbumDto findAlbumById(Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        return (album == null) ? null : beanMappingService.mapTo(album,AlbumDto.class);
    }

    @Override
    public List<ArtistDto> findAlbumArtists(Long albumId) {
        List<Artist> artists = albumService.getAlbumArtists(albumId);
        return (artists.isEmpty()) ? null : beanMappingService.mapTo(artists,ArtistDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByTitle(String title) {
        List<Album> albums = albumService.getAlbumsByTitle(title);
        return (albums.isEmpty()) ? null : beanMappingService.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByArtist(String artistName) {
        List<Album> albums = albumService.getAlbumsByArtistName(artistName);
        return (albums.isEmpty()) ? null : beanMappingService.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> findAlbumsByArtistId(Long artistId) {
        List<Album> albums = albumService.getAlbumsByArtist(artistId);
        return (albums.isEmpty()) ? null : beanMappingService.mapTo(albums,AlbumDto.class);
    }

    @Override
    public List<AlbumDto> getLastWeekAlbums() {
        List<Album> albums = albumService.getLastWeekAlbums();
        return (albums.isEmpty()) ? null : beanMappingService.mapTo(albums,AlbumDto.class);
    }

    @Override
    public void createAlbum(AlbumDto album) {
        if (album == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Album albumCreate = beanMappingService.mapTo(album,Album.class);
        albumCreate.setReleaseDate(timeService.getCurrentDate());
        albumService.createAlbum(albumCreate);
    }

    @Override
    public void updateAlbum(AlbumDto album) {
        if (album == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Album albumUpdate = beanMappingService.mapTo(album,Album.class);
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
    public void deleteAlbum(AlbumDto album) {
        albumService.deleteAlbum(beanMappingService.mapTo(album, Album.class));
    }
}
