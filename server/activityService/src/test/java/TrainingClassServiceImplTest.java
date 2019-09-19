import activity.Seance;
import activity.ActivityDefinition;
import activityApi.IActivityDefinition;
import activityApi.ISeance;
import enums.AddClassDefinitionStatus;
import enums.AddSeanceStatus;
import org.junit.Before;
import org.junit.Test;
import classService.ITrainingClassService;
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
    private ActivityDefinition class1;
    private ActivityDefinition class2;

    @Before
    public void setUp() {
        service = new TrainingClassServiceImpl();
        class1 = new ActivityDefinition("肩颈平衡", "放松肩背肌肉，缓解肩颈疼痛");
        class2 = new ActivityDefinition("臀腿强化", "加强臀腿肌肉");
    }

    @Test
    public void testAddClassDefinition() {
        ActivityDefinition invalideClass =new ActivityDefinition("","");
        AddClassDefinitionStatus status = service.addClassDefinition(invalideClass);
        assertEquals(status, AddClassDefinitionStatus.failed_invalid_name);

        status = service.addClassDefinition(class1);
        assertEquals(status, AddClassDefinitionStatus.success);
        Optional<IActivityDefinition> findClass = service.findClassDefinition("A");
        assertEquals(findClass, Optional.empty());
        findClass = service.findClassDefinition("肩颈平衡");
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

        Optional<ISeance> nextSeanceClass1 = service.findNextSeance(class1.getName());
        Optional<ISeance> nextSeanceClass2 = service.findNextSeance(class2.getName());

        assertEquals(nextSeanceClass1.isPresent(), true);
        ISeance seance = nextSeanceClass1.get();
        assertEquals(seance.date(), nexSaturday);
        assertEquals(seance.size(), 3);
        assertEquals(seance.getActivityDefinition(), class1);

        assertEquals(nextSeanceClass2.isPresent(), false);
        Map<String, ISeance> seances = service.findSeances();
        assertEquals(seances.size(), 1);



        service.addClassDefinition(class2);
        addSeanceStatus = service.addSeance(class2.getName(), nexSaturday, 4);
        assertEquals(addSeanceStatus, AddSeanceStatus.success);
        nextSeanceClass2 = service.findNextSeance(class2.getName());
        assertEquals(nextSeanceClass2.isPresent(), true);
        seance = nextSeanceClass2.get();
        assertEquals(seance.date(), nexSaturday);
        assertEquals(seance.size(), 4);
        assertEquals(seance.getActivityDefinition(), class2);

        seances = service.findSeances();
        assertEquals(seances.size(), 2);
    }
}