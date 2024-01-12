package bo.custom.impl;

import bo.custom.LaundryEquipmentBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.LaundryEquipmentDAO;
import dto.CustomerDTO;
import dto.LaundryEquipmentDTO;
import entity.Customer;
import entity.LaundryEquipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaundryEquipmentBOImpl implements LaundryEquipmentBO {

    LaundryEquipmentDAO laundryEquipmentDAO= (LaundryEquipmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LAUNDRY_EQUIPMENT);
    @Override
    public boolean saveLaundryEquipment(LaundryEquipmentDTO dto) throws SQLException {
        return laundryEquipmentDAO.save(new LaundryEquipment(dto.getMachineId(),dto.getMachineType(),dto.getStatus(),dto.getNextRepairDate()));
    }

    @Override
    public boolean updateLaundryEquipment(LaundryEquipmentDTO dto) throws SQLException {
        return laundryEquipmentDAO.update(new LaundryEquipment(dto.getMachineId(),dto.getMachineType(),dto.getStatus(),dto.getNextRepairDate()));
    }

    @Override
    public boolean removeLaundryEquipment(String id) throws SQLException {
        return laundryEquipmentDAO.remove(id);
    }

    @Override
    public LaundryEquipmentDTO searchLaundryEquipment(String id) throws SQLException {
        LaundryEquipment laundryEquipment = laundryEquipmentDAO.search(id);
        return new LaundryEquipmentDTO(laundryEquipment.getMachineId(),laundryEquipment.getMachineType(),laundryEquipment.getStatus(),laundryEquipment.getNextRepairDate());
    }

    @Override
    public List<LaundryEquipmentDTO> getAllLaundryEquipment() throws SQLException {
        List<LaundryEquipmentDTO>laundryEquipmentDTOS = new ArrayList<>();
        List<LaundryEquipment>laundryEquipments = laundryEquipmentDAO.getAll();
        for (LaundryEquipment laundryEquipment:laundryEquipments
        ) {laundryEquipmentDTOS.add(new LaundryEquipmentDTO(laundryEquipment.getMachineId(),laundryEquipment.getMachineType(),laundryEquipment.getStatus(),laundryEquipment.getNextRepairDate()));

        }
        return laundryEquipmentDTOS;
    }
}
