package util;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Status;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HistoryListTest {
    static TaskManager manager = Managers.getDefalut();
    static Task task1 = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
    static Task task2 = new Task("Отправиться в бар", null, Status.IN_PROGRESS);
    static Task task3 = new Task("Лечь спать");

    @BeforeAll
    public static void preparation() {
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
    }

    @Test
    public void listRemoveElements() {
        HistoryList taskList = new HistoryList();

        taskList.addLast(task1);
        taskList.addLast(task2);
        taskList.addLast(task3);

        Task deletedTask = task2;
        taskList.remove(deletedTask);

        for (Task task : taskList) {
            assertNotEquals(task, deletedTask);
        }
    }

    @Test
    public void listAddElements() {
        HistoryList taskList = new HistoryList();

        taskList.addLast(task1);
        taskList.addLast(task2);
        taskList.addLast(task3);

        Task addedTask = new Task("New");
        manager.addTask(addedTask);
        taskList.addLast(addedTask);

        assertEquals(taskList.getLast(), addedTask);
    }

    @Test
    public void firstAddedAreFirst() {
        HistoryList taskList = new HistoryList();

        taskList.addLast(task1);
        Task firstAdded = task1;
        taskList.addLast(task2);
        taskList.addLast(task3);

        assertEquals(taskList.getFirst(), firstAdded);
    }

    @Test
    public void secondAfterFirstRemovedBecomesFirst() {
        HistoryList taskList = new HistoryList();

        taskList.addLast(task1);
        Task firstAdded = task1;

        taskList.addLast(task2);
        Task secondAdded = task2;

        taskList.addLast(task3);

        taskList.remove(firstAdded);

        assertEquals(taskList.getFirst(), secondAdded);
    }
}