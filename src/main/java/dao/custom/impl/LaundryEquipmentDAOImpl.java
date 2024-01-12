package dao.custom.impl;

import dao.custom.LaundryEquipmentDAO;
import dto.LaundryEquipmentDTO;
import dto.tm.LaundryEquipmentTM;
import dao.SQLUtil;
import entity.LaundryEquipment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaundryEquipmentDAOImpl implements LaundryEquipmentDAO {
    @Override
    public boolean save(LaundryEquipment dto) throws SQLException {
        String sql = "INSERT INTO laundryEquipment(machineId,machineType,status,nextRepairDate) VALUES(?,?,?,?)";
        boolean isSaved = SQLUtil.execute(sql, dto.getMachineId(),dto.getMachineType(),dto.getStatus(),dto.getNextRepairDate());
        return isSaved;
    }

    @Override
    public boolean update(LaundryEquipment dto) throws SQLException {
        String sql = "UPDATE laundryEquipment set machineType=?,status=?,nextRepairDate=? WHERE machineId = ?";
        return SQLUtil.execute(sql,dto.getMachineType(),dto.getStatus(),dto.getNextRepairDate(),dto.getMachineId());
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "DELETE FROM laundryEquipment WHERE machineId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public LaundryEquipment search(String id) throws SQLException {
        String sql = "SELECT * FROM laundryEquipment where machineId = ?";

        ResultSet resultSet = SQLUtil.execute(sql, id);

        if (resultSet.next()){
            LaundryEquipment laundryEquipmentDTO= new LaundryEquipment();
            laundryEquipmentDTO.setMachineId(resultSet.getString(1));
            laundryEquipmentDTO.setMachineType(resultSet.getString(2));
            laundryEquipmentDTO.setStatus(resultSet.getString(3));
            laundryEquipmentDTO.setNextRepairDate(resultSet.getDate(4).toLocalDate());

            return laundryEquipmentDTO;
        }
        return null;
    }

    @Override
    public List<LaundryEquipment> getAll() throws SQLException {
        String sql = "SELECT * FROM laundryEquipment";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<LaundryEquipment> data = new ArrayList<>();
        while (resultSet.next()) {
            LaundryEquipment laundryEquipmentTM = new LaundryEquipment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate()

            );
            data.add(laundryEquipmentTM);
        }
        return data;
    }

}
