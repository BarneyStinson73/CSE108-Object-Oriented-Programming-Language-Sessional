package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class Market {

    public TableView playerTable;
    private Main main;
    public static List<Player> list;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPlayers(List<Player> p) {
        list = p;
        loadTableInfo();
    }

    public void setPlayers2(List<Player> p) {
        list = p;
        freshTableInfo();
    }

    public void loadTableInfo() {
        List<Player> newList = new ArrayList<>();
        for(var i : list) {
            newList.add(i);
        }
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(newList);

        TableColumn<Player, String> NameCol = new TableColumn<>("Name");
        NameCol.setMinWidth(200);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> positionCol = new TableColumn<>("Position");
        positionCol.setMinWidth(100);
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> valueCol = new TableColumn<>("Value");
        valueCol.setMinWidth(100);
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Player, String> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(100);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));


        playerTable.getColumns().addAll(NameCol, positionCol, ageCol,valueCol);
        playerTable.setEditable(true);
        playerTable.getItems().clear();
        playerTable.setItems(players);
        playerTable.refresh();
    }

    public void freshTableInfo() {
        List<Player> newList = new ArrayList<>();
        for(var i : list) {
            newList.add(new Player());
        }
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(newList);
        playerTable.getItems().clear();
        playerTable.setItems(players);
        playerTable.refresh();
    }

    public void goBack() throws Exception{
        main.showOptionChoicePage();
    }

    public void buyPlayer() throws Exception{
        Player pfs = (Player) playerTable.getSelectionModel().getSelectedItem();
        if(pfs==null) {
            showError("Player not selected");
        }
        boolean b = false;
        for(var i : main.players) {
            if(i.getName().equalsIgnoreCase(pfs.getName())) {
                b = true;
                break;
            }
        }
        if(b){
            showError("He might be on Your Teamsheet earlier,and you cannot retain your player");
        }
        else {
            for(var i : list) {
                if(pfs.getName().equalsIgnoreCase(i.getName())) {
                    main.nUtil.write(1);
                    main.nUtil.write(i);
                    break;
                }
            }
        }
    }

    public void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed");
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
