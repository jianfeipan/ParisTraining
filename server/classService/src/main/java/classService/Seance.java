package test;

import enums.AddUserStatus;
import test.TrainingClassDefinition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seance {
    private LocalDate date;
    private int size;
    private List<String> users;
    private TrainingClassDefinition trainingClassDefinition;

    public Seance(TrainingClassDefinition aClass, LocalDate date, int size) {
        this.trainingClassDefinition = aClass;
        this.date = date;
        this.size = size;
        this.users = new ArrayList<String>(size);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public int size(){
        return size;
    }

    public TrainingClassDefinition getTrainingClassDefinition() {
        return trainingClassDefinition;
    }
}
