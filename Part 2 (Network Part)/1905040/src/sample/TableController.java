package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class TableController {

    @FXML
    private TableView tableView;
    @FXML
            private List<Player> l;

    ObservableList<Player> data;

    private boolean init = true;
    private Main main;

    private void initializeColumns() {
        TableColumn<Player, String> firstNameCol = new TableColumn<>("Name");
        firstNameCol.setMinWidth(80);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> lastNameCol = new TableColumn<>("Country");
        lastNameCol.setMinWidth(80);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> emailCol = new TableColumn<>("Club");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> posCol = new TableColumn<>("Position");
        posCol.setMinWidth(80);
        posCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(60);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, Double> heightCol = new TableColumn<>("Height");
        heightCol.setMinWidth(70);
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, Integer> numCol = new TableColumn<>("Number");
        numCol.setMinWidth(70);
        numCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Player, Double> salCol = new TableColumn<>("Salary");
        salCol.setMinWidth(80);
        salCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        data = FXCollections.observableArrayList();

        tableView.getColumns().addAll(firstNameCol, lastNameCol, emailCol, posCol,ageCol,heightCol,numCol,salCol);
    }

    public void load() {
        if (init) {
            initializeColumns();
            init = false;
        }
        data.addAll(l);
        tableView.setEditable(true);
        tableView.setItems(data);
    }

 /*   @FXML
    void buttonAction(ActionEvent event) {
        data.get(0).setFirstName("JacobN ");
        data.get(0).setLastName("SmithN");
        tableView.refresh();
    }*/
    void setMain(Main main){
        this.main=main;
    }
    public void setlist(List<Player> m){
        l=m;
    }

    public void goToOptionChoice(ActionEvent actionEvent) throws IOException {
        main.showOptionChoicePage();
    }
}
