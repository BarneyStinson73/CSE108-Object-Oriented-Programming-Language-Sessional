package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.position_search;

public class Positionbox {
    @FXML
    public TextField playerpos;
    private Main main;
    @FXML
    List<Player> poslist;

    public void setMain(Main main) {
        this.main=main;
    }

    public void searchByPosition(ActionEvent actionEvent) throws IOException {
        poslist=new ArrayList<>();
        poslist=position_search(playerpos.getText());
        if(poslist==null){
            main.showAlert("No Player Containing This Position, Try Again");
        }
        else {
            main.showPlayersinaTable(poslist);
        }
    }

    public void backFromPosition(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayer();
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }
}
