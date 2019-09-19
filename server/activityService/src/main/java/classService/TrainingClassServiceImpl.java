package classService;

import activity.Seance;
import activityApi.IActivityDefinition;
import activityApi.ISeance;
import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;

import java.time.LocalDate;
import java.util.*;

public class TrainingClassServiceImpl implements ITrainingClassService {
    private Map<String, IActivityDefinition> activityDefinitions = new HashMap<String, IActivityDefinition>();
    private Map<String, ISeance> seances = new HashMap<String, ISeance>();

    @Override
    public AddClassDefinitionStatus addClassDefinition(IActivityDefinition newClassDef) {
        if(newClassDef.getName().isEmpty()) {
            return AddClassDefinitionStatus.failed_invalid_name;
        }else {
            activityDefinitions.put(newClassDef.getName(), newClassDef);
            return AddClassDefinitionStatus.success;
        }
    }

    @Override
    public AddSeanceStatus addSeance(String className, LocalDate date, int size) {
        if(activityDefinitions.containsKey(className)) {
            seances.put(className, new Seance(activityDefinitions.get(className), date, size));
            return AddSeanceStatus.success;
        }else{
            return AddSeanceStatus.failed_invalid_class;
        }
    }

    @Override
    public Optional<IActivityDefinition> findClassDefinition(String name) {
        if(activityDefinitions.containsKey(name))
            return Optional.of(activityDefinitions.get(name));
        return Optional.empty();
    }

    @Override
    public Optional<ISeance> findNextSeance(String className) {
        if(seances.containsKey(className)) {
            return Optional.of(seances.get(className));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, ISeance> findSeances() {
        return seances;
    }
}
