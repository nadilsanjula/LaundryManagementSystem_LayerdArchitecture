package dao.custom.impl;

import dao.custom.LaundryItemDAO;
import dto.LaundryItemDTO;
import dto.tm.LaundryItemTM;
import dao.SQLUtil;
import entity.LaundryItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaundryItemDAOImpl implements LaundryItemDAO {
    @Override
    public boolean save(LaundryItem dto) throws SQLException {
        String sql = "INSERT INTO laundryitem(laundryItemId,name,quantityAvailable,description,unitPrice,itemId) VALUES(?,?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getLaundryItemId(),dto.getName(),dto.getQtyAvailable(),dto.getDesc(),dto.getUnitePrice(),dto.getItemId());
        return isSaved;
    }

    @Override
    public boolean update(LaundryItem dto) throws SQLException {
        String sql = "UPDATE laundryitem set name=?,quantityAvailable =?,description=?,unitPrice=?,itemId=? where laundryItemId=?";
        return SQLUtil.execute(sql,dto.getName(),dto.getQtyAvailable(),dto.getDesc(),dto.getUnitePrice(),dto.getItemId(),dto.getLaundryItemId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM laundryitem WHERE laundryItemId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public LaundryItem search(String id) throws SQLException {
        String sql = "SELECT * FROM laundryitem where laundryItemId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            LaundryItem laundryItemDTO= new LaundryItem();
            laundryItemDTO.setLaundryItemId(resultSet.getString(1));
            laundryItemDTO.setName(resultSet.getString(2));
            laundryItemDTO.setQtyAvailable(resultSet.getInt(3));
            laundryItemDTO.setDesc(resultSet.getString(4));
            laundryItemDTO.setUnitePrice(resultSet.getDouble(5));
            laundryItemDTO.setItemId(resultSet.getString(6));
            return laundryItemDTO;
        }
        return null;
    }

    @Override
    public List<LaundryItem> getAll() throws SQLException {
        String sql = "SELECT * FROM laundryitem";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<LaundryItem> data = new ArrayList<>();
        while (resultSet.next()) {
            LaundryItem laundryItemTm = new LaundryItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            );
            data.add(laundryItemTm);
        }
        return data;
    }

}
