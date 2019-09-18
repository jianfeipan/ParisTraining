package classService;

import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;

import java.time.LocalDate;
import java.util.*;

public class TrainingClassServiceImpl implements ITrainingClassService {
    private Map<String , TrainingClassDefinition> classDefenitions = new HashMap<String, TrainingClassDefinition>();
    private Map<String, Seance> seances = new HashMap<String, Seance>();

    @Override
    public AddClassDefinitionStatus addClassDefinition(TrainingClassDefinition newClassDef) {
        if(newClassDef.getName().isEmpty()) {
            return AddClassDefinitionStatus.failed_invalid_name;
        }else {
            classDefenitions.put(newClassDef.getName(), newClassDef);
            return AddClassDefinitionStatus.success;
        }
    }

    @Override
    public AddSeanceStatus addSeance(String className, LocalDate date, int size) {
        if(classDefenitions.containsKey(className)) {
            seances.put(className, new Seance(classDefenitions.get(className), date, size));
            return AddSeanceStatus.success;
        }else{
            return AddSeanceStatus.failed_invalid_class;
        }
    }

    @Override
    public Optional<TrainingClassDefinition> findClassDeinition(String name) {
        if(classDefenitions.containsKey(name))
            return Optional.of(classDefenitions.get(name));
        return Optional.empty();
    }

    @Override
    public Optional<Seance> findNextSeance(String className) {
        if(seances.containsKey(className)) {
            return Optional.of(seances.get(className));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, Seance> findSeances() {
        return seances;
    }
}
