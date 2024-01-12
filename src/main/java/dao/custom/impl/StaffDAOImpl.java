package dao.custom.impl;

import dao.custom.StaffDAO;
import dto.StaffDTO;
import dto.tm.StaffTM;
import dao.SQLUtil;
import entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    @Override
    public boolean save(Staff dto) throws SQLException {
        String sql = "INSERT INTO staff(staffId,name,email,telNum,role) VALUES(?,?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getStaffId(),dto.getName(),dto.getEmail(),dto.getTelNum(),dto.getJobRole());
        return isSaved;
    }

    @Override
    public boolean update(Staff dto) throws SQLException {
        String sql = "UPDATE staff set name=?,email=?,telNum=?,role=? WHERE staffId = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getEmail(),dto.getTelNum(),dto.getJobRole(),dto.getStaffId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM staff WHERE staffId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Staff search(String id) throws SQLException {
        String sql = "SELECT * FROM staff where staffId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            Staff staffDTO= new Staff();
            staffDTO.setStaffId(resultSet.getString(1));
            staffDTO.setName(resultSet.getString(2));
            staffDTO.setEmail(resultSet.getString(3));
            staffDTO.setTelNum(resultSet.getInt(4));
            staffDTO.setJobRole(resultSet.getString(5));

            return staffDTO;
        }
        return null;
    }

    @Override
    public List<Staff> getAll() throws SQLException {
        String sql = "SELECT * FROM staff";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<Staff> data = new ArrayList<>();
        while (resultSet.next()) {
            Staff staffTM = new Staff(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            data.add(staffTM);
        }
        return data;
    }

}
