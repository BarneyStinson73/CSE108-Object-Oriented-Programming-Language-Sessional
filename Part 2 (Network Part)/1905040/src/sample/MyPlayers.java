package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class MyPlayers {

    public TableView Table;
    private Main main;
    public static List<Player> list;

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
        TableColumn<Player, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(100);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, String> statusCol = new TableColumn<>("Status");
        statusCol.setMinWidth(100);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));



        Table.getColumns().addAll(NameCol, positionCol,ageCol, statusCol);
        Table.getItems().clear();
        Table.setEditable(true);
        Table.setItems(players);
        Table.refresh();

    }

    public void refreshTableInfo() {
        List<Player> newList = new ArrayList<>();
        for(var i : list) {
            newList.add(new Player());
        }
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(newList);
        Table.getItems().clear();
        Table.setItems(players);
        Table.refresh();
    }

    public void goBack() throws Exception{
        main.showOptionChoicePage();
    }

    public void sellPlayer() throws Exception{
        Player ps = (Player) Table.getSelectionModel().getSelectedItem();
        System.out.println(ps.getName());
        if(ps==null) {
            showError("Player not selected");
        }
        else if(ps.status.equals("Out For Sale")){
            showError("Already on sale");
        }
        else {
            for(var i : Main.players) {
                if(ps.getName().equalsIgnoreCase(i.getName())) {
                    i.status = "Out For Sale";
                    ps.status = "Out For Sale";
                    Table.refresh();
                    Main.nUtil.write(0);
                    Main.nUtil.write(i);
                    break;
                }
            }
        }
    }

    public void setPlayers(List<Player> p) {
        list = p;
        loadTableInfo();
    }

    public void setPlayers2(List<Player> p) {
        list = p;
        refreshTableInfo();
    }

    public void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
