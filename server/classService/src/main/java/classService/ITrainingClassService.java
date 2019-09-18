package classService;

import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;
import jdk.Exported;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Exported
public interface ITrainingClassService {
    public AddClassDefinitionStatus addClassDefinition(TrainingClassDefinition newClassDef);
    public AddSeanceStatus addSeance(String className, LocalDate date, int size);

    public Optional<TrainingClassDefinition> findClassDeinition(String name);
    public Optional<Seance> findNextSeance(String classDef);
    public Map<String, Seance> findSeances();
}
