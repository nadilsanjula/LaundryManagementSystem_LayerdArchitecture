package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String address;
    private int telNum;
    private String nic;
}
