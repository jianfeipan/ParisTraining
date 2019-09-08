import enums.AddUserStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seance {
    private Date date;
    private int size;
    private List<String> users;
    private TrainingClassDefinition aClass;

    public Seance(TrainingClassDefinition aClass, Date date, int size) {
        this.aClass = aClass;
        this.date = date;
        this.size = size;
        this.users = new ArrayList<String>(size);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getUsers() {
        return users;
    }

    public AddUserStatus addUser(String newUser) {
        if(users.size() >= this.size){
            return AddUserStatus.failed_full;
        }else if(users.contains(newUser))
        {
            return AddUserStatus.failed_duplicated;
        }else{
            users.add(newUser);
            return AddUserStatus.success;
        }
    }
}
