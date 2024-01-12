package bo.custom.impl;

import bo.custom.StaffBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.StaffDAO;
import dto.ItemDTO;
import dto.PaymentDTO;
import dto.StaffDTO;
import entity.Item;
import entity.Payment;
import entity.Staff;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffBOImpl implements StaffBO {
    StaffDAO staffDAO= (StaffDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STAFF);
    @Override
    public boolean saveStaff(StaffDTO dto) throws SQLException {
        return staffDAO.save(new Staff(dto.getStaffId(),dto.getName(),dto.getEmail(),dto.getTelNum(),dto.getJobRole()));
    }

    @Override
    public boolean updateStaff(StaffDTO dto) throws SQLException {
        return staffDAO.update(new Staff(dto.getStaffId(),dto.getName(),dto.getEmail(),dto.getTelNum(),dto.getJobRole()));
    }

    @Override
    public boolean removeStaff(String id) throws SQLException {
        return staffDAO.remove(id);
    }

    @Override
    public StaffDTO searchStaff(String id) throws SQLException {
        Staff staff = staffDAO.search(id);
        return new StaffDTO(staff.getStaffId(),staff.getName(),staff.getEmail(),staff.getTelNum(),staff.getJobRole());
    }

    @Override
    public List<StaffDTO> getAllStaff() throws SQLException {
        List<StaffDTO>staffDTOS = new ArrayList<>();
        List<Staff>staffs = staffDAO.getAll();
        for (Staff staff:staffs
        ) {staffDTOS.add(new StaffDTO(staff.getStaffId(),staff.getName(),staff.getEmail(),staff.getTelNum(),staff.getJobRole()));

        }
        return staffDTOS;
    }
}
