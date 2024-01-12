package bo.custom;

import bo.SuperBO;
import dto.LaundryItemDTO;
import dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    boolean savePayment(PaymentDTO dto) throws SQLException;
    boolean updatePayment(PaymentDTO dto) throws SQLException;
    boolean removePayment(String id) throws SQLException;
    PaymentDTO searchPayment(String id) throws SQLException;
    List<PaymentDTO> getAllPayment() throws SQLException;
}
