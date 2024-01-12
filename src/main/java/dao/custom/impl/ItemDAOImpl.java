package dao.custom.impl;

import dao.custom.ItemDAO;
import db.DBConnection;
import dto.ItemDTO;
import dto.tm.CartTM;
import dto.tm.ItemTM;
import dao.SQLUtil;
import entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

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

    @Override
    public boolean save(Item dto) throws SQLException {
        String sql = "INSERT INTO item(itemId,name,quantity,unitPrice,description,orderId) VALUES(?,?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getItemId(),dto.getName(),dto.getQty(),dto.getUnitPrice(),dto.getDescription(),dto.getOrderId());
        return isSaved;
    }

    @Override
    public boolean update(Item dto) throws SQLException {
        String sql = "UPDATE item set name=?,description=?,quantity=?,unitPrice=?,orderId=? WHERE itemId = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getDescription(),dto.getQty(),dto.getUnitPrice(),dto.getOrderId(),dto.getItemId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE itemId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Item search(String id) throws SQLException {
        String sql = "SELECT * FROM item where itemId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            Item itemDTO= new Item();
            itemDTO.setItemId(resultSet.getString(1));
            itemDTO.setName(resultSet.getString(2));
            itemDTO.setDescription(resultSet.getString(3));
            itemDTO.setQty(resultSet.getInt(4));
            itemDTO.setUnitPrice(resultSet.getDouble(5));
            itemDTO.setOrderId(resultSet.getString(6));

            return itemDTO;
        }
        return null;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<Item> data = new ArrayList<>();
        while (resultSet.next()) {
            Item itemTM = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            );
            data.add(itemTM);
        }
        return data;
    }
}
