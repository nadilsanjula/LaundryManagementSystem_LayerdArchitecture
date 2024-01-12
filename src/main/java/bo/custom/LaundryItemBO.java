package bo.custom;

import bo.SuperBO;
import dto.LaundryEquipmentDTO;
import dto.LaundryItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface LaundryItemBO extends SuperBO {

    boolean saveLaundryItem(LaundryItemDTO dto) throws SQLException;
    boolean updateLaundryItem(LaundryItemDTO dto) throws SQLException;
    boolean removeLaundryItem(String id) throws SQLException;
    LaundryItemDTO searchLaundryItem(String id) throws SQLException;
    List<LaundryItemDTO> getAllLaundryItem() throws SQLException;
}
