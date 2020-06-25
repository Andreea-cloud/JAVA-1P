package Model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    int id;
    String username;
    String password;
    String email;
}