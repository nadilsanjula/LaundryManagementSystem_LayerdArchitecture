package controller;

import bo.BOFactory;
import bo.custom.SupplierBO;
import com.jfoenix.controls.JFXButton;
import dto.SupplierDTO;
import dto.tm.SupplierTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.SupplierDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSupplierFormController implements Initializable {

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    public AnchorPane viewSupplierPane;
    public TableView<SupplierTM> tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colTelNumber;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public JFXButton btnBack;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        viewSupplierPane.getChildren().clear();
        viewSupplierPane.getChildren().add(load);
    }

    private void setCellValueFactory() {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelNumber.setCellValueFactory(new PropertyValueFactory<>("telnum"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void getAll() {
        try {
            List<SupplierDTO> supplierTMS = supplierBO.getAllSupplier();
            ObservableList<SupplierTM> list = FXCollections.observableArrayList();
            for (SupplierDTO supplierTM :supplierTMS){
                list.add(
                        new SupplierTM(
                                supplierTM.getSupplierId(),
                                supplierTM.getName(),
                                supplierTM.getEmail(),
                                supplierTM.getTelnum(),
                                supplierTM.getAddress()
                        ));
            }
            tblSupplier.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
