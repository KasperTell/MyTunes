package GUI.Model;

import BE.Playlist;
import BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistModel {
    private ObservableList<Playlist> playlistsToBeViewed;

    private PlaylistManager playlistManager;

    // Constructor for the model
    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    // Returns the playlist
    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistsToBeViewed;
    }

    // Searches for playlists in the DB
    public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = playlistManager.searchPlaylists();
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(searchResults);
    }

    // Creates a new playlist
    public void createNewPlaylist(String title, int duration, int totalSongs, int id) throws Exception {
        Playlist p = playlistManager.createNewPlaylist(title, totalSongs, duration, id);
        System.out.println(p.toString());
    }

    // Updates the playlist
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        //Call BLL layer
        //Update the playlist in the DB
        playlistManager.updatePlaylist(updatedPlaylist);

        //Update listview
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    //Deletes the selected playlist
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistManager.deletePlaylist(deletedPlaylist);

        playlistsToBeViewed.remove(deletedPlaylist);
    }

}