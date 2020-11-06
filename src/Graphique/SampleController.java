package Graphique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SampleController {
    @FXML
    private PacManView pacManView;
    @FXML
    private Button play, quit;

    private Main main;

    public SampleController() {}

    @FXML
    private void initialize(){}

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void play() throws IOException {
        main.Play();
    }

    public void quit() {

    }
}
