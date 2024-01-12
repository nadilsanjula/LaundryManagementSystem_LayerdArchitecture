package dao.custom.impl;


import dao.custom.CustomerDAO;
import db.DBConnection;
import dto.CustomerDTO;
import dao.SQLUtil;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public boolean save(Customer dto) throws SQLException {
        String sql = "INSERT INTO customer(customerId,name,email,address,nic,telNum) VALUES(?,?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getCustomerId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getNic(),dto.getTelNum());
        return isSaved;
    }

    @Override
    public boolean update(Customer dto) throws SQLException {
        String sql = "UPDATE customer set name=?,address=?,email=?,nic=?,telNum=? WHERE customerId = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getAddress(),dto.getEmail(),dto.getNic(),dto.getTelNum(),dto.getCustomerId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE customerId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Customer search(String id) throws SQLException {
        String sql = "SELECT * FROM customer where customerId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            CustomerDTO customerDTO= new CustomerDTO();
            customerDTO.setCustomerId(resultSet.getString(1));
            customerDTO.setName(resultSet.getString(2));
            customerDTO.setEmail(resultSet.getString(3));
            customerDTO.setAddress(resultSet.getString(4));
            customerDTO.setTelNum(resultSet.getInt(5));
            customerDTO.setNic(resultSet.getString(6));

            return customerDTO;
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<Customer> data = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)

            );
            data.add(customer);
        }
        return data;
    }
}
