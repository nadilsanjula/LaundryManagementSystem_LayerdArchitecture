package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getCustomerId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getTelNum(),dto.getNic()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getCustomerId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getTelNum(),dto.getNic()));
    }

    @Override
    public boolean removeCustomer(String id) throws SQLException {
        return customerDAO.remove(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getEmail(),customer.getAddress(),customer.getTelNum(),customer.getNic());
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException {
        List<CustomerDTO>customerDTOS = new ArrayList<>();
        List<Customer>customers = customerDAO.getAll();
        for (Customer customer:customers
        ) {customerDTOS.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getEmail(),customer.getAddress(),customer.getTelNum(),customer.getNic()));

        }
        return customerDTOS;
    }
}
