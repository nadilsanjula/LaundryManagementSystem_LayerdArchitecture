package dao;

import dao.custom.*;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, LAUNDRY_EQUIPMENT, LAUNDRY_ITEM, ORDER, PAYMENT,  PLACE_ORDER, STAFF, SUPPLIER
    }


    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();

            case ITEM:
                return new ItemDAOImpl();

            /*case LAUNDRY_EQUIPMENT:
                return new LaundryEquipmentModel();

            case LAUNDRY_ITEM:
                return new LaundryItemModel();

            case ORDER:
                return new OrderModel();

            case PAYMENT:
                return new PaymentModel();

            case PLACE_ORDER:
                return new PlaceOrderModel();

            case STAFF:
                return new StaffModel();

            case SUPPLIER:
                return new SupplierModel();*/

            default:
                return null;
        }
    }
}
