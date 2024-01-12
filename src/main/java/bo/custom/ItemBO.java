package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException;
    boolean updateItem(ItemDTO dto) throws SQLException;
    boolean removeItem(String id) throws SQLException;
    ItemDTO searchItem(String id) throws SQLException;
    List<ItemDTO> getAllItem() throws SQLException;
}
