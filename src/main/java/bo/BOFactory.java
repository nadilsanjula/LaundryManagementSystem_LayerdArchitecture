package bo;

import bo.custom.LaundryItemBO;
import bo.custom.impl.*;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;}


    public enum BOTypes {
        CUSTOMER, ITEM, LAUNDRY_EQUIPMENT, LAUNDRY_ITEM, ORDER, PAYMENT,  PLACE_ORDER, STAFF, SUPPLIER
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();

            case ITEM:
                return new ItemBOImpl();

            case LAUNDRY_EQUIPMENT:
                return new LaundryItemBOImpl();

            case LAUNDRY_ITEM:
                return new LaundryItemBOImpl();

            //case ORDER:
              //  return new OrderModel();

            case PAYMENT:
                return new PaymentBOImpl();

          //  case PLACE_ORDER:
            //    return new PlaceOrderModel();

            case STAFF:
                return new StaffBOImpl();

            case SUPPLIER:
                return new SupplierBOImpl();

            default:
                return null;
        }
    }
}
