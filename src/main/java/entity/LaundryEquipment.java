package entity;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class LaundryEquipment {

    private String machineId;
    private String machineType;
    private String status;
    private LocalDate nextRepairDate;

}
