package classService;

import activity.Seance;
import activityApi.IActivityDefinition;
import activityApi.ISeance;
import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;
import jdk.Exported;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Exported
public interface ITrainingClassService {
    public AddClassDefinitionStatus addClassDefinition(IActivityDefinition newClassDef);
    public AddSeanceStatus addSeance(String className, LocalDate date, int size);

    public Optional<IActivityDefinition> findClassDefinition(String name);
    public Optional<ISeance> findNextSeance(String classDef);
    public Map<String, ISeance> findSeances();
}
