package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.LaundryItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.LaundryItemDAO;
import dto.ItemDTO;
import dto.LaundryItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.LaundryItemDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class LaundryItemFormController {
    public JFXButton btnView;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnSave;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantityAvailable;
    public JFXTextField txtDescription;
    public JFXTextField txtName;
    public JFXTextField txtLaundryItemId;
    public AnchorPane laundryItemPane;
    public JFXComboBox comItemId;

    ObservableList<LaundryItemDTO> observableList = FXCollections.observableArrayList();
    private ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    LaundryItemBO laundryItemBO = (LaundryItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LAUNDRY_ITEM);



    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isValead = validateItem();

        if (isValead) {
            String laundryItemId = txtLaundryItemId.getText();
            String name = txtName.getText();
            int qtyAvailble = Integer.parseInt(txtQuantityAvailable.getText());
            String desc = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            String itemId = (String) comItemId.getValue();
            try {
                boolean isSaved = laundryItemBO.saveLaundryItem(new LaundryItemDTO(laundryItemId, name, qtyAvailble, desc, unitPrice, itemId));


                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    txtLaundryItemId.setText("");
                    txtName.setText("");
                    txtQuantityAvailable.setText("");
                    txtDescription.setText("");
                    txtUnitPrice.setText("");
                    comItemId.setValue("");
                    observableList.clear();

                } else {

                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateItem() {
        String id = txtLaundryItemId.getText();
        boolean idMatch = Pattern.matches("[I]\\d{3,}", id);
        if (!idMatch) {
            new Alert(Alert.AlertType.ERROR, "invalid id").show();
            return false;
        }
        return true;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValead = validateItem();

        if (isValead) {
            String laundryItemId = txtLaundryItemId.getText();
            String name = txtName.getText();
            int qtyAvailble = Integer.parseInt(txtQuantityAvailable.getText());
            String desc = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            String itemId = (String) comItemId.getValue();

            boolean isUpdated = false;
            try {
                isUpdated = laundryItemBO.updateLaundryItem(new LaundryItemDTO(laundryItemId, name, qtyAvailble, desc, unitPrice, itemId));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                    txtLaundryItemId.setText("");
                    txtName.setText("");
                    txtQuantityAvailable.setText("");
                    txtDescription.setText("");
                    txtUnitPrice.setText("");
                    comItemId.setValue("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String laundryItemId = txtLaundryItemId.getText();

        try {
            boolean isRemoved = laundryItemBO.removeLaundryItem(laundryItemId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtLaundryItemId.setText("");
                txtName.setText("");
                txtQuantityAvailable.setText("");
                txtDescription.setText("");
                txtUnitPrice.setText("");
                comItemId.setValue("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/viewLaundryItemForm.fxml"));
        laundryItemPane.getChildren().clear();
        laundryItemPane.getChildren().add(load);
    }

    public void IdSearchOnAction(ActionEvent actionEvent) {
        String laundryItemId = txtLaundryItemId.getText();

        try {
            LaundryItemDTO laundryItemDTO= laundryItemBO.searchLaundryItem(laundryItemId);

            if (laundryItemDTO != null) {
                txtLaundryItemId.setText(laundryItemDTO.getLaundryItemId());
                txtName.setText(laundryItemDTO.getName());
                txtQuantityAvailable.setText(String.valueOf(laundryItemDTO.getQtyAvailable()));
                txtDescription.setText(laundryItemDTO.getDesc());
                txtUnitPrice.setText(String.valueOf(laundryItemDTO.getUnitePrice()));
                comItemId.setValue(laundryItemDTO.getItemId());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comItemIdOnAction(ActionEvent actionEvent) {
        String itemId = (String) comItemId.getValue();

        try {
            ItemDTO itemDTO = itemBO.searchItem(itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDTO> itemTMS = itemBO.getAllItem();

            for (ItemDTO dto : itemTMS) {
                obList.add(dto.getItemId());
            }
            comItemId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadItemId();
    }
}
