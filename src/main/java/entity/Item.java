package entity;


import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Item {
    private String itemId;
    private String name;
    private String description;
    private int qty;
    private double unitPrice;
    private String orderId;
}
