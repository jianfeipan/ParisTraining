package activity;

import activityApi.IActivityDefinition;
import activityApi.ISeance;
import enums.AddUserStatus;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seance implements ISeance {
    private LocalDate date;
    private int size;
    private List<String> users;
    private IActivityDefinition activityDefinition;

    public Seance(IActivityDefinition aClass, LocalDate date, int size) {
        this.activityDefinition = aClass;
        this.date = date;
        this.size = size;
        this.users = new ArrayList<String>(size);
    }

    public LocalDate date() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int size(){
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public IActivityDefinition getActivityDefinition() {
        return activityDefinition;
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
