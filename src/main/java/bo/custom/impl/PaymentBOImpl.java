package bo.custom.impl;

import bo.custom.PaymentBO;
import dao.DAOFactory;
import dao.custom.LaundryItemDAO;
import dao.custom.PaymentDAO;
import dto.LaundryItemDTO;
import dto.PaymentDTO;
import entity.LaundryItem;
import entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException {
        return paymentDAO.save(new Payment(dto.getPaymentId(),dto.getAmount(),dto.getPaymentDate(),dto.getOrderId()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException {
        return paymentDAO.update(new Payment(dto.getPaymentId(),dto.getAmount(),dto.getPaymentDate(),dto.getOrderId()));
    }

    @Override
    public boolean removePayment(String id) throws SQLException {
        return paymentDAO.remove(id);
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException {
        Payment payment = paymentDAO.search(id);
        return new PaymentDTO(payment.getPaymentId(),payment.getAmount(),payment.getPaymentDate(),payment.getOrderId());
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        List<PaymentDTO>paymentDTOS = new ArrayList<>();
        List<Payment>payments = paymentDAO.getAll();
        for (Payment payment:payments
        ) {paymentDTOS.add(new PaymentDTO(payment.getPaymentId(),payment.getAmount(),payment.getPaymentDate(),payment.getOrderId()));

        }
        return paymentDTOS;
    }
}
