package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Supplier {

    private String supplierId;
    private String name;
    private String email;
    private int telnum;
    private String address;
}
