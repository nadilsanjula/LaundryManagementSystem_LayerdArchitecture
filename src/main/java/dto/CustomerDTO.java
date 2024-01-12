package dto;

import entity.Customer;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO extends Customer {
    private String customerId;
    private String name;
    private String email;
    private String address;
    private int telNum;
    private String nic;
}
