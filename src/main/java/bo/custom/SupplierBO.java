package bo.custom;

import bo.SuperBO;
import dto.StaffDTO;
import dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(SupplierDTO dto) throws SQLException;
    boolean updateSupplier(SupplierDTO dto) throws SQLException;
    boolean removeSupplier(String id) throws SQLException;
    SupplierDTO searchSupplier(String id) throws SQLException;
    List<SupplierDTO> getAllSupplier() throws SQLException;
}
