package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

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
        return null;
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

}
