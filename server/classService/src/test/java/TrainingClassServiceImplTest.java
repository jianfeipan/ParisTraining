import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;
import org.junit.Before;
import org.junit.Test;
import classService.ITrainingClassService;
import classService.Seance;
import classService.TrainingClassDefinition;
import classService.TrainingClassServiceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TrainingClassServiceImplTest{

    private int size = 3;
    private ITrainingClassService service;
    private TrainingClassDefinition class1;
    private TrainingClassDefinition class2;

    @Before
    public void setUp() {
        service = new TrainingClassServiceImpl();
        class1 = new TrainingClassDefinition("肩颈平衡", "放松肩背肌肉，缓解肩颈疼痛");
        class2 = new TrainingClassDefinition("臀腿强化", "加强臀腿肌肉");
    }

    @Test
    public void testAddClassDefinition() {
        TrainingClassDefinition invalideClass =new TrainingClassDefinition("","");
        AddClassDefinitionStatus status = service.addClassDefinition(invalideClass);
        assertEquals(status, AddClassDefinitionStatus.failed_invalid_name);

        status = service.addClassDefinition(class1);
        assertEquals(status, AddClassDefinitionStatus.success);
        Optional<TrainingClassDefinition> findClass = service.findClassDeinition("A");
        assertEquals(findClass, Optional.empty());
        findClass = service.findClassDeinition("肩颈平衡");
        assertEquals(findClass, Optional.of(class1));
    }

    @Test
    public void testAddSeance() {
        service.addClassDefinition(class1);
        LocalDate today = LocalDate.now();
        LocalDate nexSaturday = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        AddSeanceStatus addSeanceStatus = service.addSeance(class1.getName(), nexSaturday, 3);
        assertEquals(addSeanceStatus, AddSeanceStatus.success);
        addSeanceStatus = service.addSeance(class2.getName(), nexSaturday, 3);
        assertEquals(addSeanceStatus, AddSeanceStatus.failed_invalid_class);

        Optional<Seance> nextSeanceClass1 = service.findNextSeance(class1.getName());
        Optional<Seance> nextSeanceClass2 = service.findNextSeance(class2.getName());

        assertEquals(nextSeanceClass1.isPresent(), true);
        Seance seance = nextSeanceClass1.get();
        assertEquals(seance.getDate(), nexSaturday);
        assertEquals(seance.size(), 3);
        assertEquals(seance.getTrainingClassDefinition(), class1);

        assertEquals(nextSeanceClass2.isPresent(), false);
        Map<String, Seance> seances = service.findSeances();
        assertEquals(seances.size(), 1);



        service.addClassDefinition(class2);
        addSeanceStatus = service.addSeance(class2.getName(), nexSaturday, 4);
        assertEquals(addSeanceStatus, AddSeanceStatus.success);
        nextSeanceClass2 = service.findNextSeance(class2.getName());
        assertEquals(nextSeanceClass2.isPresent(), true);
        seance = nextSeanceClass2.get();
        assertEquals(seance.getDate(), nexSaturday);
        assertEquals(seance.size(), 4);
        assertEquals(seance.getTrainingClassDefinition(), class2);

        seances = service.findSeances();
        assertEquals(seances.size(), 2);
    }
}