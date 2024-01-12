package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.StaffBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.StaffDTO;
import entity.Customer;
import entity.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.StaffDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderFormController {
    public JFXButton btnView;
    public JFXButton btnDelete;
    public JFXDatePicker PickupDate;
    public JFXDatePicker DeliveryDate;
    public JFXButton btnUpdate;
    public JFXButton btnSave;
    public JFXTextField txtTotalAmount;
    public JFXTextField txtOrderId;
    public AnchorPane orderPane;
    public JFXTextField txtStatus;
    public JFXComboBox comCustomerId;
    public JFXComboBox comStaffId;
    public JFXButton btnplaceOrder;

    private CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
    private StaffDAOImpl staffDAOImpl = new StaffDAOImpl();
    ObservableList<OrderDTO> observableList = FXCollections.observableArrayList();
    StaffBO staffBO = (StaffBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STAFF);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);




    public void btnSaveOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        LocalDate pickupDate = PickupDate.getValue();
        LocalDate deliveryDate = DeliveryDate.getValue();
        Double amount = Double.valueOf((txtTotalAmount.getText()));
        String customerId = (String) comCustomerId.getValue();
        String staffId = (String) comStaffId.getValue();


        try {
            boolean isSaved = OrderDAOImpl.save(new OrderDTO(orderId, pickupDate, deliveryDate,amount, customerId,staffId));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtOrderId.setText("");
                PickupDate.getValue();
                DeliveryDate.getValue();
                txtTotalAmount.setText("");
                comCustomerId.setValue("");
                comStaffId.setValue("");
                observableList.clear();

            } else {

                new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        LocalDate pickupDate = PickupDate.getValue();
        LocalDate deliveryDate = DeliveryDate.getValue();
        Double amount = Double.valueOf((txtTotalAmount.getText()));
        String customerId = (String) comCustomerId.getValue();
        String staffId = (String) comStaffId.getValue();

        boolean isUpdated = false;
        try {
            isUpdated = OrderDAOImpl.update(new OrderDTO(orderId,pickupDate,deliveryDate,amount,customerId,staffId));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtOrderId.setText("");
                PickupDate.getValue();
                DeliveryDate.getValue();
                txtTotalAmount.setText("");
                comCustomerId.setValue("");
                comStaffId.setValue("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();

        try {
            boolean isRemoved = OrderDAOImpl.remove(orderId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtOrderId.setText("");
                PickupDate.getValue();
                DeliveryDate.getValue();
                txtTotalAmount.setText("");
                comCustomerId.setValue("");
                comStaffId.setValue("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/viewOrderForm.fxml"));
        orderPane.getChildren().clear();
        orderPane.getChildren().add(load);
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String customerId = (String) comCustomerId.getValue();

        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<Customer> customerDTOS = customerDAOImpl.getAll();

            for (Customer dto : customerDTOS) {
                obList.add(dto.getCustomerId());
            }
            comCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadCustomerId();
        loadStaffId();
    }

    public void comStaffIdOnAction(ActionEvent actionEvent) {
        String staffId = (String) comStaffId.getValue();

        try {
            StaffDTO staffDTO = staffBO.searchStaff(staffId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStaffId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<Staff> staffTMS = staffDAOImpl.getAll();

            for (Staff dto : staffTMS) {
                obList.add(dto.getStaffId());
            }
            comStaffId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void idSearchOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();

        try {
            OrderDTO orderDTO= OrderDAOImpl.search(orderId);

            if (orderDTO != null) {
                txtOrderId.setText(orderDTO.getOrderId());
                PickupDate.setValue(orderDTO.getPickupDate());
                DeliveryDate.setValue(orderDTO.getDeliveryDate());
                txtTotalAmount.setText(String.valueOf(orderDTO.getAmount()));
                comCustomerId.setValue(orderDTO.getCustomerId());
                comStaffId.setValue(orderDTO.getStaffid());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/placeOrderForm.fxml"));
        orderPane.getChildren().clear();
        orderPane.getChildren().add(load);
    }
}
