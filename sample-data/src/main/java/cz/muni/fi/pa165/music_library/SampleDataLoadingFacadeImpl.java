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
    Playlist playlist, playlist2;
    Album album, album2, album3;
    Song song, song2, song3, song4, song5;
    Artist artist, artist2, artist3;

    @Override
    public void loadData() {
        playlists();
        artists();
        albums();
        songs();

        try {
            admin();
            basic();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
        admin.addPlaylist(playlist);
        playlist.setUser(admin);
        userService.updateUser(admin);

        basic.addPlaylist(playlist2);
        playlist2.setUser(basic);
        userService.updateUser(basic);

        playlist.addSong(song4);
        playlist.addSong(song5);
        playlistService.updatePlaylist(playlist);

        playlist2.addSong(song2);
        playlist2.addSong(song3);
        playlistService.updatePlaylist(playlist2);
    }

    private User admin() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        admin = new User();
        admin.setUsername("Filip");
        admin.setEmail("s@s.cz");
        admin.setDateCreated(new Date());
        admin.setUserLevel(UserLevel.ADMIN);
        userService.registerUser(admin, "aaaa");
        return userService.findUserByEmail("s@s.cz");
    }

    private User basic() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        basic = new User();
        basic.setUsername("Klara");
        basic.setEmail("k@k.cz");
        basic.setDateCreated(new Date());
        basic.setUserLevel(UserLevel.BASIC_USER);
        userService.registerUser(basic, "aaaa");
        return userService.findUserByEmail("k@k.cz");
    }

    private void artists() {
        artist = new Artist();
        artist.setName("Halalajka");
        artist.setBirthDate(new Date());
        artist.setArtistInfo("Best of the best of the best of the gypsy best");
        artistService.createArtist(artist);

        artist2 = new Artist();
        artist2.setName("The Beatles");
        artist2.setBirthDate(new Date());
        artist2.setArtistInfo("Liverpool group");
        artistService.createArtist(artist2);

        artist3 = new Artist();
        artist3.setName("Eminem");
        artist3.setBirthDate(new Date());
        artist3.setArtistInfo("Number 1 rapper");
        artistService.createArtist(artist3);
    }

    private void songs() {
        song = new Song();
        song.setTitle("Yellow Submarine");
        song.setArtist(artist2);
        song.setAlbum(album);
        songService.createSong(song);

        song2 = new Song();
        song2.setTitle("Eleanor Rigby");
        song2.setArtist(artist2);
        song2.setAlbum(album);
        songService.createSong(song2);

        song3 = new Song();
        song3.setTitle("My Mom");
        song3.setArtist(artist3);
        song3.setAlbum(album2);
        songService.createSong(song3);

        song4 = new Song();
        song4.setTitle("Hello");
        song4.setArtist(artist3);
        song4.setAlbum(album2);
        songService.createSong(song4);

        song5 = new Song();
        song5.setTitle("Just Some Song");
        song5.setArtist(artist);
        song5.setAlbum(album3);
        songService.createSong(song5);
    }

    private void playlists() {
        playlist = new Playlist();
        playlist.setTitle("Party");
        playlist.setDateCreated(new Date());
        playlistService.createPlaylist(playlist);

        playlist2 = new Playlist();
        playlist2.setTitle("My favourites");
        playlist2.setDateCreated(new Date());
        playlistService.createPlaylist(playlist2);
    }

    private void albums() {
        album = new Album();
        album.setTitle("Revolver");
        album.setReleaseDate(new Date());
        albumService.createAlbum(album);

        album2 = new Album();
        album2.setTitle("Relapse");
        album2.setReleaseDate(new Date());
        albumService.createAlbum(album2);

        album3 = new Album();
        album3.setTitle("Halalajka's Best Of");
        album3.setReleaseDate(new Date());
        albumService.createAlbum(album3);
    }
}
