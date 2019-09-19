package daoAPI;

import activity.ActivityDefinition;
import activityApi.IActivityDefinition;

import java.util.List;
import java.util.Optional;

public interface IActivityDefinitionDAO {
    Optional<IActivityDefinition> get(long id);
    List<IActivityDefinition> getAll();

    void save(IActivityDefinition activityDef);

    void update(IActivityDefinition activityDef, String name, String description);

    void delete(IActivityDefinition activityDef);
}
