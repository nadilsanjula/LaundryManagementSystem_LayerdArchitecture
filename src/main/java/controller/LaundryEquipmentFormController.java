package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.LaundryEquipmentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.LaundryEquipmentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.LaundryEquipmentDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class LaundryEquipmentFormController {
    public AnchorPane laundryEquipmentPane;
    public JFXTextField txtMachineId;
    public JFXTextField txtStatus;
    public JFXTextField txtMachineType;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnView;
    public JFXDatePicker dateRepair;

    ObservableList<LaundryEquipmentDTO> observableList = FXCollections.observableArrayList();
    LaundryEquipmentBO laundryEquipmentBO = (LaundryEquipmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LAUNDRY_EQUIPMENT);

    public void IdSearchOnAction(ActionEvent actionEvent) {
        String machineId = txtMachineId.getText();

        try {
            LaundryEquipmentDTO laundryEquipmentDTO= laundryEquipmentBO.searchLaundryEquipment(machineId);

            if (laundryEquipmentDTO != null) {
                txtMachineId.setText(laundryEquipmentDTO.getMachineId());
                txtMachineType.setText(laundryEquipmentDTO.getMachineType());
                txtStatus.setText(laundryEquipmentDTO.getStatus());
                dateRepair.setValue(laundryEquipmentDTO.getNextRepairDate());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String machineId = txtMachineId.getText();
        String machineType = txtMachineType.getText();
        String status = txtStatus.getText();
        LocalDate repairDate = dateRepair.getValue();



        try {
            boolean isSaved = laundryEquipmentBO.saveLaundryEquipment(new LaundryEquipmentDTO(machineId, machineType, status, repairDate));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtMachineId.setText("");
                txtMachineType.setText("");
                txtStatus.setText("");
                dateRepair.getValue();
                observableList.clear();

            } else {

                new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String machineId = txtMachineId.getText();
        String machineType = txtMachineType.getText();
        String status = txtStatus.getText();
        LocalDate repairDate = dateRepair.getValue();

        boolean isUpdated = false;
        try {
            isUpdated = laundryEquipmentBO.updateLaundryEquipment(new LaundryEquipmentDTO(machineId, machineType, status, repairDate));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtMachineId.setText("");
                txtMachineType.setText("");
                txtStatus.setText("");
                dateRepair.getValue();
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String machineId = txtMachineId.getText();

        try {
            boolean isRemoved = laundryEquipmentBO.removeLaundryEquipment(machineId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtMachineId.setText("");
                txtMachineType.setText("");
                txtStatus.setText("");
                dateRepair.getValue();
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/viewLaundryEquipmentForm.fxml"));
        laundryEquipmentPane.getChildren().clear();
        laundryEquipmentPane.getChildren().add(load);
    }
}
