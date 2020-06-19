package Controller;

import Model.Users;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Session {
    private static Session SINGLETON;
    //info about authenticated user
    private String authUserName;
    private String authEmail;

    public static final Session getInstance(){
        if(SINGLETON == null){
            SINGLETON = new Session();
        }
        return SINGLETON;
    }
}
