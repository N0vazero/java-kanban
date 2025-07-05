package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    public void tasksAreEqualsIfIdEquals() {
        Task task1 = new Task("t1", null, Status.NEW, 1);
        Task task2 = new Task("t2", null, Status.NEW, 1);
        assertEquals(task2, task1);
    }

    @Test
    public void tasksAreNotEqualsIfIdNotEquals() {
        Task task1 = new Task("t1", null, Status.NEW, 1);
        Task task2 = new Task("t1", null, Status.NEW, 2);
        assertNotEquals(task2, task1);
    }
}