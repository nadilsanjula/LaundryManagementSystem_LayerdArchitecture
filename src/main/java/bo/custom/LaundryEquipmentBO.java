package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.LaundryEquipmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface LaundryEquipmentBO extends SuperBO {
    boolean saveLaundryEquipment(LaundryEquipmentDTO dto) throws SQLException;
    boolean updateLaundryEquipment(LaundryEquipmentDTO dto) throws SQLException;
    boolean removeLaundryEquipment(String id) throws SQLException;
    LaundryEquipmentDTO searchLaundryEquipment(String id) throws SQLException;
    List<LaundryEquipmentDTO> getAllLaundryEquipment() throws SQLException;
}
