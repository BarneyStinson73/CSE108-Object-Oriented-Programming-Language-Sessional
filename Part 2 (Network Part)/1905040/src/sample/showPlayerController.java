package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class showPlayerController {
    private List<Player> playerslist;
    private Main main;
    @FXML
    private ListView<String> listView;

    public void setPlayerslist(List<Player> p) {
        playerslist = p;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void buttonAction(ActionEvent event) {
        if (playerslist.get(0)==null ) {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Player Search Unsuccessful");
            a.setContentText("Try Again With a Valid Name!!!");
            a.show();
        } else {
            ObservableList names = FXCollections.observableArrayList();
            for (var i : playerslist) {
                names.add(i.getName());
            }

            listView.setItems(names);

            listView.getSelectionModel().selectedItemProperty().addListener(
                    (observableValue, oldValue, newValue) -> {
                        Player pp = null;
                        for (var i : playerslist) {
                            if (i.getName().equalsIgnoreCase(newValue)) {
                                pp = i;
                                break;
                            }
                        }
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Player");
                        a.setHeaderText(pp.getName());
                        String s = "Country:  " + pp.getCountry() + "\n";
                        s += "Club: " + pp.getClub() + "\n";
                        s += "Position: " + pp.getPosition() + "\n";
                        s += "Age: " + pp.getAge() + "\n";
                        s += "Height: " + pp.getHeight() + "\n";
                        s += "Salary: " + pp.getSalary() + "\n";
                        s += "Number: " + pp.getNumber() + "\n";
                        a.setContentText(s);
                        a.showAndWait();
                    }
            );
        }
    }

    public void previousFromPlayerNameSearch(ActionEvent actionEvent) throws IOException {
        main.showPlayerNameBox();
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.showOptionChoicePage();
    }
}
