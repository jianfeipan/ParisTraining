package daoAPI;

import activity.ActivityDefinition;

import java.util.List;
import java.util.Optional;

public interface ActivityDefinitionDAO {
    Optional<ActivityDefinition> get(long id);
    List<ActivityDefinition> getAll();

    void save(ActivityDefinition activityDef);

    void update(ActivityDefinition activityDef, String name, String description);

    void delete(ActivityDefinition activityDef);
}
