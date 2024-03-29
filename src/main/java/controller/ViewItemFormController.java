package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import dto.ItemDTO;
import dto.tm.ItemTM;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.ItemDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewItemFormController implements Initializable {
    public AnchorPane viewItemPane;
    public TableView <ItemTM> tblItem;
    public JFXButton btnBack;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colDesc;
    public TableColumn colUnitPrice;
    public TableColumn colOrderId;
    public TableColumn colQuantity;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);


    private void getAll() {
        try {
            List<ItemDTO> itemTMS = itemBO.getAllItem();
            ObservableList<ItemTM> list = FXCollections.observableArrayList();
            for (ItemDTO itemTM :itemTMS){
                list.add(
                        new ItemTM(
                                itemTM.getItemId(),
                                itemTM.getName(),
                                itemTM.getDescription(),
                                itemTM.getQty(),
                                itemTM.getUnitPrice(),
                                itemTM.getOrderId()
                        ));
            }
            tblItem.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
        viewItemPane.getChildren().clear();
        viewItemPane.getChildren().add(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();

    }
}
