package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.tm.CartTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException;
    boolean updateItem(ItemDTO dto) throws SQLException;
    boolean removeItem(String id) throws SQLException;
    ItemDTO searchItem(String id) throws SQLException;
    List<ItemDTO> getAllItem() throws SQLException;

    boolean updateQty(String code, int qty) throws SQLException;
    boolean updateItem(List<CartTM> cartTmList) throws SQLException;
}
