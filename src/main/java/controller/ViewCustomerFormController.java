package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDTO;
import dto.tm.CustomerTM;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.CustomerDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {
    public TableColumn colNic;
    public TableColumn colTelNumber;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableView<CustomerTM> tblCustomer;
    public AnchorPane viewCustomerPane;
    public JFXButton btnBack;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    private void getAll() {
        try {
            List<CustomerDTO> customerTMS = customerBO.getAllCustomer();
            ObservableList<CustomerTM> list = FXCollections.observableArrayList();
            for (Customer customerTM :customerTMS){
                list.add(
                        new CustomerTM(
                                customerTM.getCustomerId(),
                                customerTM.getName(),
                                customerTM.getEmail(),
                                customerTM.getAddress(),
                                customerTM.getTelNum(),
                                customerTM.getNic()
                        ));
            }
            tblCustomer.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelNumber.setCellValueFactory(new PropertyValueFactory<>("telNum"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        viewCustomerPane.getChildren().clear();
        viewCustomerPane.getChildren().add(load);

    }
}
