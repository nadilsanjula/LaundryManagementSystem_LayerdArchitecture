package entity;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Staff {

    private String staffId;
    private String name;
    private String email;
    private int telNum;
    private String jobRole;
}
