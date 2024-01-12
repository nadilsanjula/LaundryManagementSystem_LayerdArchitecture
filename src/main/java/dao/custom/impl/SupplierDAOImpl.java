package dao.custom.impl;

import dao.custom.SupplierDAO;
import db.DBConnection;
import dto.SupplierDTO;
import dto.tm.SupplierTM;
import dao.SQLUtil;
import entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier dto) throws SQLException {
        String sql = "INSERT INTO supplier(supplierId,name,email,telNum,address) VALUES(?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getSupplierId(),dto.getName(),dto.getEmail(),dto.getTelnum(),dto.getAddress());
        return isSaved;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException {
        String sql = "UPDATE supplier set name=?,email=?,telNum=?,address=? WHERE supplierId = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getEmail(),dto.getTelnum(),dto.getAddress(),dto.getSupplierId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Supplier search(String id) throws SQLException {
        String sql = "SELECT * FROM supplier where supplierId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            Supplier supplierDTO= new Supplier();
            supplierDTO.setSupplierId(resultSet.getString(1));
            supplierDTO.setName(resultSet.getString(2));
            supplierDTO.setEmail(resultSet.getString(3));
            supplierDTO.setTelnum(resultSet.getInt(4));
            supplierDTO.setAddress(resultSet.getString(5));

            return supplierDTO;
        }
        return null;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<Supplier> data = new ArrayList<>();
        while (resultSet.next()) {
            Supplier supplierTM = new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            data.add(supplierTM);
        }
        return data;
    }
   /* public static boolean update(SupplierDTO supplierDTO) throws SQLException {
        String sql = "UPDATE supplier set name=?,email=?,telNum=?,address=? WHERE supplierId = ?";
        return SQLUtil.execute(sql,supplierDTO.getName(),supplierDTO.getEmail(),supplierDTO.getTelnum(),supplierDTO.getAddress(),supplierDTO.getSupplierId());
    }

    public static boolean save(SupplierDTO supplierDTO) throws SQLException {
        String sql = "INSERT INTO supplier(supplierId,name,email,telNum,address) VALUES(?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getEmail(),supplierDTO.getTelnum(),supplierDTO.getAddress());
        return isSaved;
    }

    public static boolean remove(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierId = ?";
        return SQLUtil.execute(sql,supplierId);
    }

    public static SupplierDTO search(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier where supplierId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, supplierId);

        if (resultSet.next()){
            SupplierDTO supplierDTO= new SupplierDTO();
            supplierDTO.setSupplierId(resultSet.getString(1));
            supplierDTO.setName(resultSet.getString(2));
            supplierDTO.setEmail(resultSet.getString(3));
            supplierDTO.setTelnum(resultSet.getInt(4));
            supplierDTO.setAddress(resultSet.getString(5));

            return supplierDTO;
        }
        return null;
    }

    public static List<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<SupplierTM> data = new ArrayList<>();
        while (resultSet.next()) {
            SupplierTM supplierTM = new SupplierTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            data.add(supplierTM);
        }
        return data;
    }*/



}
