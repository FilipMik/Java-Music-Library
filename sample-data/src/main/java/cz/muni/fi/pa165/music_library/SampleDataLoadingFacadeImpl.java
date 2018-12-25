package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.data.entities.*;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * @author Filip Mik on 17. 12. 2018
 */

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    UserService userService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

    User user;
    Playlist playlist;
    Album album;
    Song song;
    Artist artist;

    @Override
    public void loadData() {
        playlist();
        artist();
        album();
        song();
        try {
            user();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private User user() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        user = new User();
        user.setUsername("Filip");
        user.setEmail("s@s.cz");
        user.setDateCreated(new Date());
        userService.registerUser(user, "aaaa");
        return userService.findUserByEmail("s@s.cz");
    }

    private Artist artist() {
        artist = new Artist();
        artist.setName("Halalajka");
        artist.setBirthDate(new Date());
        artist.setArtistInfo("Best of the best of the best of the gypsy best");
        artistService.createArtist(artist);
        return artistService.getArtistsByName("Halalajka").get(0);
    }

    private Song song() {
        song = new Song();
        song.setTitle("song1");
        song.setArtist(artist);
        song.setAlbum(album);
        songService.createSong(song);
        return songService.getSongsByTitle("song1").get(0);
    }

    private Playlist playlist() {
        playlist = new Playlist();
        playlist.setTitle("playlist1");
        playlist.setDateCreated(new Date());
        playlistService.createPlaylist(playlist);
        return playlistService.getPlaylistsByTitle("playlist1").get(0);
    }

    private Album album() {
        album = new Album();
        album.setTitle("albuum");
        album.setReleaseDate(new Date());
        albumService.createAlbum(album);
        return albumService.getAlbumsByTitle("albuum").get(0);
    }

}
