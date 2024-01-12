package controller;

import bo.BOFactory;
import bo.custom.LaundryItemBO;
import com.jfoenix.controls.JFXButton;
import dto.LaundryItemDTO;
import entity.LaundryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewLaundryItemFormController implements Initializable {

    public AnchorPane viewLaundryItemPane;
    public TableView<LaundryItem> tblLaundryItem;
    public TableColumn colLaundryItemID;
    public TableColumn colName;
    public TableColumn colQtyAvailable;
    public TableColumn colDesc;
    public TableColumn colUnitPrice;
    public TableColumn colItemId;
    public JFXButton btnBack;

    LaundryItemBO laundryItemBO = (LaundryItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LAUNDRY_ITEM);
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/laundryItemForm.fxml"));
        viewLaundryItemPane.getChildren().clear();
        viewLaundryItemPane.getChildren().add(load);
    }

    private void getAll() {
        try {
            List<LaundryItemDTO> laundryItemTMS = laundryItemBO.getAllLaundryItem();
            ObservableList<LaundryItem> list = FXCollections.observableArrayList();
            for (LaundryItemDTO laundryItemTM :laundryItemTMS){
                list.add(
                        new LaundryItem(
                            laundryItemTM.getLaundryItemId(),
                                laundryItemTM.getName(),
                                laundryItemTM.getQtyAvailable(),
                                laundryItemTM.getDesc(),
                                laundryItemTM.getUnitePrice(),
                                laundryItemTM.getItemId()
                        ));
            }
            tblLaundryItem.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colLaundryItemID.setCellValueFactory(new PropertyValueFactory<>("laundryItemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qtyAvailable"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
}
