package bo.custom.impl;

import bo.custom.LaundryItemBO;
import dao.DAOFactory;
import dao.custom.LaundryEquipmentDAO;
import dao.custom.LaundryItemDAO;
import dto.LaundryEquipmentDTO;
import dto.LaundryItemDTO;
import entity.LaundryEquipment;
import entity.LaundryItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaundryItemBOImpl implements LaundryItemBO {
    LaundryItemDAO laundryItemDAO= (LaundryItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LAUNDRY_ITEM);
    @Override
    public boolean saveLaundryItem(LaundryItemDTO dto) throws SQLException {
        return laundryItemDAO.save(new LaundryItem(dto.getLaundryItemId(),dto.getName(),dto.getQtyAvailable(),dto.getDesc(),dto.getUnitePrice(),dto.getItemId()));
    }

    @Override
    public boolean updateLaundryItem(LaundryItemDTO dto) throws SQLException {
        return laundryItemDAO.update(new LaundryItem(dto.getLaundryItemId(),dto.getName(),dto.getQtyAvailable(),dto.getDesc(),dto.getUnitePrice(),dto.getItemId()));
    }

    @Override
    public boolean removeLaundryItem(String id) throws SQLException {
        return laundryItemDAO.remove(id);
    }

    @Override
    public LaundryItemDTO searchLaundryItem(String id) throws SQLException {
        LaundryItem laundryItem = laundryItemDAO.search(id);
        return new LaundryItemDTO(laundryItem.getLaundryItemId(),laundryItem.getName(),laundryItem.getQtyAvailable(),laundryItem.getDesc(),laundryItem.getUnitePrice(),laundryItem.getItemId());
    }

    @Override
    public List<LaundryItemDTO> getAllLaundryItem() throws SQLException {
        List<LaundryItemDTO>laundryItemDTOS = new ArrayList<>();
        List<LaundryItem>laundryItems = laundryItemDAO.getAll();
        for (LaundryItem laundryItem:laundryItems
        ) {laundryItemDTOS.add(new LaundryItemDTO(laundryItem.getLaundryItemId(),laundryItem.getName(),laundryItem.getQtyAvailable(),laundryItem.getDesc(),laundryItem.getUnitePrice(),laundryItem.getItemId()));

        }
        return laundryItemDTOS;
    }
}
