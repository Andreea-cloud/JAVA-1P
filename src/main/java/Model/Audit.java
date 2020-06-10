package Model;

import java.sql.Time;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Audit {

    String username;
    String action;
    Time timestamp;
}
