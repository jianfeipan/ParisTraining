import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;

import java.util.Date;
import java.util.Optional;

public interface ITrainingClassService {
    public AddClassDefinitionStatus addClassDefinition(TrainingClassDefinition newClassDef);
    public AddSeanceStatus addSeance(TrainingClassDefinition classDefinition, Date date, int size);

    public Optional<TrainingClassDefinition> findClassDeinition(String name);
    public Optional<Seance> findNextSeance(TrainingClassDefinition classDef);
}
