package activity;

import enums.AddUserStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SeanceTest {

    private int size = 3;
    private Seance seance;

    @Before
    public void setUp() {
        LocalDate today = LocalDate.now();
        seance = new Seance(new ActivityDefinition("肩颈平衡", "放松肩背肌肉，缓解肩颈疼痛"), today, size);
    }

    @Test
    public void addUser() {
        AddUserStatus status = seance.addUser("Jianfei");
        int nb = seance.getUsers().size();
        assertEquals(status, AddUserStatus.success);
        Assert.assertEquals(nb,1);

        status = seance.addUser("Jianfei");
        nb = seance.getUsers().size();
        assertEquals(status, AddUserStatus.failed_duplicated);
        Assert.assertEquals(nb,1);

        status = seance.addUser("Yaya");
        nb = seance.getUsers().size();
        assertEquals(status, AddUserStatus.success);
        Assert.assertEquals(nb,2);

        status = seance.addUser("Xiaoxiao");
        nb = seance.getUsers().size();
        assertEquals(status, AddUserStatus.success);
        Assert.assertEquals(nb,3);

        status = seance.addUser("Yiqiao");
        assertEquals(status, AddUserStatus.failed_full);
        Assert.assertEquals(nb,3);
    }
}