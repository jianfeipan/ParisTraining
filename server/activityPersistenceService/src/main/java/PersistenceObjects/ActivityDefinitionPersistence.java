package PersistenceObjects;

import activity.ActivityDefinition;
import activityApi.IActivityDefinition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table
public class ActivityDefinitionPersistence implements IActivityDefinition, Serializable {
    private ActivityDefinition bo;

    @Id
    @GeneratedValue
    private long id;
    @Override
    public String getName() {
        return bo.getName();
    }

    @Override
    public void setName(String name) {
        bo.setName(name);
    }

    @Override
    public String getDescription() {
        return bo.getDescription();
    }

    @Override
    public void setDescription(String description) {
        bo.setDescription(description);
    }

    @Override
    public String toString() {
        return "ActivityDefinition [id=" + id + ", name=" + getName() + ", description=\""
                + getDescription() + "\"]";
    }
}
