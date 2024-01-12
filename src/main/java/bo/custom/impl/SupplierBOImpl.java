package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.custom.StaffDAO;
import dao.custom.SupplierDAO;
import dto.StaffDTO;
import dto.SupplierDTO;
import entity.Staff;
import entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getName(),dto.getEmail(),dto.getTelnum(),dto.getAddress()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getName(),dto.getEmail(),dto.getTelnum(),dto.getAddress()));
    }

    @Override
    public boolean removeSupplier(String id) throws SQLException {
        return supplierDAO.remove(id);
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getEmail(),supplier.getTelnum(),supplier.getAddress());
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException {
        List<SupplierDTO>supplierDTOS = new ArrayList<>();
        List<Supplier>suppliers = supplierDAO.getAll();
        for (Supplier supplier:suppliers
        ) {supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getEmail(),supplier.getTelnum(),supplier.getAddress()));

        }
        return supplierDTOS;
    }
}
