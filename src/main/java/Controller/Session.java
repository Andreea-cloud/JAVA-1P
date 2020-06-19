package Controller;

import Model.Users;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<Users>  users = new ArrayList();
    private static Session SINGELTONE;

    private Session(){

    }

    public static final Session getInstance(){
        if(SINGELTONE == null){
            SINGELTONE = new Session();
        }
        return SINGELTONE;
    }
}
