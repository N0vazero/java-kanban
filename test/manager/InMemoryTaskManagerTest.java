package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    static TaskManager manager = Managers.getDefalut();
    static Epic epic1 = new Epic("e1");
    static SubTask subTask1;
    static SubTask subTask2;

    @Test
    public void cantAddEpicToEpicAsSubtask() {
        /*
        Добавить эпик в эпик невозможно, т.к у него нет поля idEpic, с которым работает метод addSubtask
        Предполагается, что уже на этапе создания SubTask известно, какому эпику он будет принадлежать,
        поэтому контструктор требует передать idEpic первым аргументом
         */
    }

    @Test
    public void cantAddSubtaskToSubtaskAsEpic() {
        manager.addEpic(epic1);
        subTask1 = new SubTask(epic1.getId(), "s1");
        manager.addSubtask(subTask1);
        subTask2 = new SubTask(subTask1.getId(), "s2");
        assertFalse(manager.addSubtask(subTask2));
    }

    @Test
    public void addedTasksReallyAddAreAdded() {
        Task task = new Task("t1");
        manager.addTask(task);
        assertNotNull(manager.getTask(task.getId()));

        Epic epic = new Epic("e2");
        manager.addEpic(epic);
        assertNotNull(manager.getEpic(epic.getId()));

        SubTask subTask = new SubTask(epic.getId(), "s3");
        manager.addSubtask(subTask);
        assertNotNull(manager.getSubtask(subTask.getId()));
    }

    @Test
    public void addedTasksWithGivenIdNotConflictWithOtherTasks() {
        Task task1 = manager.getTasks().getFirst();
        Task task2 = new Task("t2", null, Status.NEW, task1.getId());
        manager.addTask(task2);
        assertNotEquals(task1.getId(), task2.getId());
    }

    @Test
    public void tasksFieldDontChangingAfterAdd() {
        Task task = new Task("t4", "123", Status.DONE);
        String taskName = task.getName();
        String taskDescription = task.getDescription();
        Status taskStatus = task.getStatus();

        manager.addTask(task);

        assertEquals(taskName, task.getName());
        assertEquals(taskDescription, task.getDescription());
        assertEquals(taskStatus, task.getStatus());
    }
}