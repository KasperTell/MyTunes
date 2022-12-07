package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.MyTunesModel;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController extends AbstractController {

    public ListView<Song> lstSong;
    public ImageView imgSearch;
    public ImageView imgUpArrow;
    public ImageView imgDownArrow;
    public ImageView imgLeftArrow;
    public ImageView imgBack;
    public ImageView imgPlay;
    public ImageView imgNext;
    public ImageView imgVolume;
    public Button btnClose;
    public ListView lstPlaylist;
    public ListView lstPlaylistSongs;
    public TextField txtFilter;
    public Text txtPlaying;
    public Button btnEditSong;
    public Slider sldrVolume;
    private SongModel songModel;
    private PlaylistModel playlistModel;

    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        playlistModel = getModel().getPlaylistModel();

        lstSong.setItems(songModel.getObservableSongs());
        lstPlaylist.setItems(playlistModel.getObservablePlaylists());
    }


    // Opens a new window to create a new playlist with title.
    public void handleNewPlaylist(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/PlaylistAddView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

            PlaylistAddController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(pane));
            stage.setTitle("Add Playlist");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    public void handleEditPlaylist(ActionEvent actionEvent) {
        try {
            Playlist selectedPlaylist = (Playlist) lstPlaylist.getSelectionModel().getSelectedItem();
            playlistModel.setSelectedPlaylist(selectedPlaylist);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditPlaylistView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

            EditPlaylistController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(pane));
            stage.setTitle("Edit Playlist");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    public void handleDelete(ActionEvent actionEvent) {
        try {
            Playlist selectedPlaylist = (Playlist) lstPlaylist.getSelectionModel().getSelectedItem();
            playlistModel.setSelectedPlaylist(selectedPlaylist);
            playlistModel.deletePlaylist(playlistModel.getSelectedPlaylist());
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleSongUp(ActionEvent actionEvent) {
    }

    public void handleSongDown(ActionEvent actionEvent) {
    }

    public void handlePlaylistDeleteSong(ActionEvent actionEvent) {
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleNewSong(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/SongAddView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();
            SongAddController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Add Song");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);
            dialogWindow.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }


    }

    public void handleEditSong(ActionEvent actionEvent) {
        try {
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
            songModel.setSelectedSong(selectedSong);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EditSongView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

            EditSongController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Song information");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);
            dialogWindow.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    public void handleDeleteSong(ActionEvent actionEvent) {
        try {
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
            songModel.setSelectedSong(selectedSong);
            songModel.deleteSong(songModel.getSelectedSong());
        } catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleClose(ActionEvent actionEvent) {
        cancel(btnClose);
    }

    public void handleSearch(ActionEvent actionEvent) throws Exception {
        songModel.searchSong(txtFilter.getText().toLowerCase());
    }

    public void handleBack(ActionEvent actionEvent) {
        // Play the previous song.
    }

    public void handlePlay(ActionEvent actionEvent) {

    }

    public void handleNext(ActionEvent actionEvent) {
        // Plays the next song.
    }

    public void handleVolume(MouseEvent mouseEvent) {
        double volume = sldrVolume.getValue();

    }
}
