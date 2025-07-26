package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SubTaskTest {
    @Test
    public void tasksAreEqualsIfIdsEquals() {
        SubTask task1 = new SubTask(1, "t1", null, Status.NEW, 1);
        SubTask task2 = new SubTask(1, "t2", null, Status.NEW, 1);
        assertEquals(task2, task1);
    }

    @Test
    public void tasksAreNotEqualsIfIdsNotEquals() {
        SubTask task1 = new SubTask(1, "t1", null, Status.NEW, 1);
        SubTask task2 = new SubTask(2, "t1", null, Status.NEW, 3);
        assertNotEquals(task2, task1);
    }

}