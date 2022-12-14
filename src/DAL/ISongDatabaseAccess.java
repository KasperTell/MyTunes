package DAL;

import BE.Song;

import java.net.URI;
import java.sql.Time;
import java.time.Duration;
import java.util.List;

public interface ISongDatabaseAccess {
    public List<Song> getAllSongs() throws Exception;

    public Song createSong(String title, String artist, String genre, Time timer, String url) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;

}
