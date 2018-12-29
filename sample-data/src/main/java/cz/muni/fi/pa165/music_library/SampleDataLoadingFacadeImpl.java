package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.data.entities.*;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    User admin;
    User basic;
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
            admin();
            basic();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
        admin.addPlaylist(playlist);
        playlist.setUser(admin);
        userService.updateUser(admin);
        playlist.addSong(song);
        playlistService.updatePlaylist(playlist);
    }

    private User admin() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        admin = new User();
        admin.setUsername("Filip");
        admin.setEmail("s@s.cz");
        admin.setDateCreated(new Date());
        admin.setUserLevel(UserLevel.Admin);
        userService.registerUser(admin, "aaaa");
        return userService.findUserByEmail("s@s.cz");
    }

    private User basic() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        basic = new User();
        basic.setUsername("Klara");
        basic.setEmail("k@k.cz");
        basic.setDateCreated(new Date());
        basic.setUserLevel(UserLevel.BasicUser);
        userService.registerUser(basic, "aaaa");
        return userService.findUserByEmail("k@k.cz");
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
        playlist.setTitle("My favourites");
        playlist.setDateCreated(new Date());
        playlistService.createPlaylist(playlist);
        return playlistService.getPlaylistsByTitle("My favourites").get(0);
    }

    private Album album() {
        album = new Album();
        album.setTitle("albuum");
        album.setReleaseDate(new Date());
        albumService.createAlbum(album);
        return albumService.getAlbumsByTitle("albuum").get(0);
    }
}
