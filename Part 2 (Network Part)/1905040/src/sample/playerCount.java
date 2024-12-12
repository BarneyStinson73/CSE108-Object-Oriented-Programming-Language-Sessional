package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class playerCount {
    @FXML
    private TableView tableView1;
    @FXML
    private List<Country> c;

    ObservableList<Country> data;

    private boolean init = true;
    private Main main;

    private void initializeColumns() {
        TableColumn<Country, String> NameCol = new TableColumn<>("Country's Name");
        NameCol.setMinWidth(80);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Country, Integer> countCol = new TableColumn<>("Count");
        countCol.setMinWidth(80);
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));


        data = FXCollections.observableArrayList();

        tableView1.getColumns().addAll(NameCol, countCol);
    }

    public void load() {
        if (init) {
            initializeColumns();
            init = false;
        }
        data.addAll(c);
        tableView1.setEditable(true);
        tableView1.setItems(data);
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
    public void setlist(List<Country> m){
        c=m;
    }
}
