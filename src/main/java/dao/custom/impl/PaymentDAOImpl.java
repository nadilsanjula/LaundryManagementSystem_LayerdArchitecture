package dao.custom.impl;

import dao.custom.PaymentDAO;
import dto.PaymentDTO;
import dto.tm.PaymentTM;
import dao.SQLUtil;
import entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment dto) throws SQLException {
        String sql = "INSERT INTO payment(paymentId,amount,paymentDate,orderId) VALUES(?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getPaymentId(),dto.getAmount(),dto.getPaymentDate(),dto.getOrderId());
        return isSaved;
    }

    @Override
    public boolean update(Payment dto) throws SQLException {
        String sql = "UPDATE payment set amount=?,paymentDate =?,orderId=? where paymentId=?";
        return SQLUtil.execute(sql, dto.getAmount(),dto.getPaymentDate(),dto.getOrderId(),dto.getPaymentId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM payment WHERE paymentId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Payment search(String id) throws SQLException {
        String sql = "SELECT * FROM payment where paymentId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            Payment paymentDTO= new Payment();
            paymentDTO.setPaymentId(resultSet.getString(1));
            paymentDTO.setAmount(resultSet.getDouble(2));
            paymentDTO.setPaymentDate(resultSet.getDate(3).toLocalDate());
            paymentDTO.setOrderId(resultSet.getString(4));

            return paymentDTO;
        }
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<Payment> data = new ArrayList<>();
        while (resultSet.next()) {
            Payment paymentTM = new Payment(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getString(4)
            );
            data.add(paymentTM);
        }
        return data;
    }

}
