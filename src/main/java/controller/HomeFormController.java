package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.SupplierModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {
    public Label lblCustomerCount;
    public Label lblSupplierCount;

    CustomerDAOImpl cusModel = new CustomerDAOImpl();
    SupplierModel suppModel = new SupplierModel();

    public void CustomerCountOnAction() {
        /*try {
            String count = Integer.toString(cusModel.getCustomerCount());
            lblCustomerCount.setText(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void SupplierCountOnAction() {
        /*try {
            String count = Integer.toString(suppModel.getSupplierCount());
            lblSupplierCount.setText(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCountOnAction();
        SupplierCountOnAction();
    }
}
