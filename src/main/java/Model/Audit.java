package Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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
