package BLL.util;

import BE.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSearcher {

    //This is basicly just checking if the playlist is eligible to be added to the lists of playlists.
    public List<Playlist> search(List<Playlist> searchBase, String query) {
        List<Playlist> searchResult = new ArrayList<>();

        for (Playlist playlist : searchBase) {
            if(compareToPlaylistTitle(query, playlist) || compareToPlaylistTime(query, playlist))
            {
                searchResult.add(playlist);
            }
        }

        return searchResult;
    }

    // Compares the playlist time to the given string
    private boolean compareToPlaylistTime(String query, Playlist playlist) {
        return playlist.getTime().toString().contains(query);
    }

    // Compares the playlist title to the given string.
    private boolean compareToPlaylistTitle(String query, Playlist playlist) {
        return playlist.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
