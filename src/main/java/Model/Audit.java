package Model;

import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Audit {
    String username;
    String action;
    Timestamp timestamp;
}