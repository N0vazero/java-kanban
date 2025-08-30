package task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EpicTest {
    static SubTask task1 = new SubTask(1, "t1", null, Status.NEW, 1);
    static SubTask task2 = new SubTask(1, "t2", null, Status.NEW, 2);
    static SubTask task3 = new SubTask(1, "t3", null, Status.NEW, 3);
    static ArrayList<Integer> temp = new ArrayList<>();
    static Epic epic1;
    static Epic epic2;
    static Epic epic3;
    static Epic epic4;

    @BeforeAll
    public static void preparate() {
        temp.add(task1.getId());
        temp.add(task2.getId());

        epic1 = new Epic("e1", null, 1, temp);
        epic2 = new Epic("e2", null, 1, temp);
        epic3 = new Epic("e2", null, 2, temp);

        temp.add(task3.getId());

        epic4 = new Epic("e3", null, 1, temp);
    }

    @Test
    public void epicsAreEqualsIfSubtasksArrayAndIdAreEquals() {
        assertEquals(epic1, epic2);
    }

    @Test
    public void epicsAreNotEqualsIfIdNotEquals() {
        assertNotEquals(epic1, epic3);
    }

    @Test
    public void epicsAreNotEqualsIfSubtasksArrayNotEquals() {
        assertNotEquals(epic1, epic4);
    }
}