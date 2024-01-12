package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import db.DBConnection;
import dto.ItemDTO;
import dto.tm.CartTM;
import entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException {
        return itemDAO.save(new Item(dto.getItemId(),dto.getName(),dto.getDescription(),dto.getQty(),dto.getUnitPrice(),dto.getOrderId()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getItemId(),dto.getName(),dto.getDescription(),dto.getQty(),dto.getUnitPrice(),dto.getOrderId()));
    }

    @Override
    public boolean removeItem(String id) throws SQLException {
        return itemDAO.remove(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemId(),item.getName(),item.getDescription(),item.getQty(),item.getUnitPrice(),item.getOrderId());
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException {
        List<ItemDTO>itemDTOS = new ArrayList<>();
        List<Item>items = itemDAO.getAll();
        for (Item item:items
        ) {itemDTOS.add(new ItemDTO(item.getItemId(),item.getName(),item.getDescription(),item.getQty(),item.getUnitPrice(),item.getOrderId()));

        }
        return itemDTOS;
    }

    public boolean updateItem(List<CartTM> cartTmList) throws SQLException {
        for(CartTM tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItemId(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE item SET quantity = quantity - ? WHERE ItemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }

}
