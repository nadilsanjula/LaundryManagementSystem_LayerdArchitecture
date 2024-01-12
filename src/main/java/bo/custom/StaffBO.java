package bo.custom;

import bo.SuperBO;
import dto.PaymentDTO;
import dto.StaffDTO;

import java.sql.SQLException;
import java.util.List;

public interface StaffBO extends SuperBO {


    boolean saveStaff(StaffDTO dto) throws SQLException;
    boolean updateStaff(StaffDTO dto) throws SQLException;
    boolean removeStaff(String id) throws SQLException;
    StaffDTO searchStaff(String id) throws SQLException;
    List<StaffDTO> getAllStaff() throws SQLException;
}
