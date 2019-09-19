package activityApi;

import enums.AddUserStatus;

import java.time.LocalDate;
import java.util.List;

public interface ISeance {

    public LocalDate date();

    public void setDate(LocalDate date);

    public int size();

    public void setSize(int size) ;

    public IActivityDefinition getActivityDefinition();

    public List<String> getUsers();

    public AddUserStatus addUser(String newUser);
}
