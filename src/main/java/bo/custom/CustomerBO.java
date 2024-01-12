package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO dto) throws SQLException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException;
    boolean removeCustomer(String id) throws SQLException;
    CustomerDTO searchCustomer(String id) throws SQLException;
    List<CustomerDTO> getAllCustomer() throws SQLException;
}
