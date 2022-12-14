package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Time;


public class EditSongController extends AbstractController {

    public TextField txtTitle;

    public TextField txtArtist;
    public ComboBox cboxCategory;
    public TextField txtUrl;
    public TextField txtTimer;
    public Button btnCancelSaveSong;
    public Button btnSaveSong;
    private SongModel songModel;
    private final String[] category = {
            "Pop",
            "Rock",
            "Blues",
            "Classical",
            "Country",
            "Folk",
            "Jazz",
            "Newage",
            "Reggae",
            "Soundtrack",
            "Electronic",
            "Funk/Soul",
            "Hip-Hop",
            "Religious",
            "Latin",
            "Non-Music",
            "Rap",
            "R&B",
            "International",
            "Dødstramp"
    };

    public EditSongController() {
    }

    public void handleCancelSaveSong(ActionEvent actionEvent) {
        //Close the window by clicking the cancel bottom.
        cancel(btnCancelSaveSong);
    }

    public void handleSaveSong(ActionEvent actionEvent) {
        //Getting all the information from the text-fields.
        try {
            String updatedTitle = txtTitle.getText();
            String updatedArtist = txtArtist.getText();
            Time updatedTimer = Time.valueOf(txtTimer.getText());
            String updatedGenre = (String) cboxCategory.getSelectionModel().getSelectedItem();
            String updatedMp3 = txtUrl.getText();
            Song updatedSong = new Song(songModel.getSelectedSong().getId(), updatedTitle, updatedArtist, updatedGenre, updatedTimer, updatedMp3);
            //Updating based on the new information.
            songModel.updateSong(updatedSong);

            cancel(btnSaveSong);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {
        //Getting the model
        songModel = getModel().getSongModel();
        //When the FXML file is opened the setup is set to run
        //So the already inserted information about a song is show and can be changes.
        txtTitle.setText(songModel.getSelectedSong().getTitle());
        txtArtist.setText(songModel.getSelectedSong().getArtist());
        cboxCategory.getSelectionModel().select(songModel.getSelectedSong().getGenre());
        txtTimer.setText(String.valueOf(songModel.getSelectedSong().getTimer()));
        txtUrl.setText(songModel.getSelectedSong().getURL());
        //Setting all our genres in the Combo box.
        cboxCategory.getItems().addAll(category);
    }

    //Pick a file with the filechooser
    public void handleFileChooser(ActionEvent actionEvent) {
        //create a new stage for picking files with the title "Pick a Song" and start in the applications dir
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a Song");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        //add which filetype is valid
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Sound Files (*.mp3, *.wav)","*.mp3","*.wav"),
                new FileChooser.ExtensionFilter("MP3 Files (*.mp3)", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV Files (*.wav)","*.wav")
        );
        //put selected file into the url textfield
        File selectedFile = fileChooser.showOpenDialog(stage);
        txtUrl.setText(selectedFile.getPath());
    }
}
