package Controller;

import lombok.Getter;
import lombok.Setter;

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
