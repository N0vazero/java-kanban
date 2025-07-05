package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;



class ManagersTest {

    @Test
    public void managersReturnReadyToUseTaskManager() {
        TaskManager manager = Managers.getDefalut();
        Task task1 = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
        Task task2 = new Task("Отправиться в бар", null, Status.IN_PROGRESS);
        Task task3 = new Task("Лечь спать");
        assert manager.addTask(task1);
        assert manager.addTask(task2);
        assert manager.addTask(task3);

        Epic epic = new Epic("Планы на выходные");
        assert manager.addEpic(epic);

        SubTask subtask1 = new SubTask(epic.getId(), "Встать пораньше, понаблюдать за птицами");
        SubTask subtask2 = new SubTask(epic.getId(), "Вкусно позавтракать");
        SubTask subtask3 = new SubTask(epic.getId(), "Выйти на прогулку");
        assert manager.addSubtask(subtask1);
        assert manager.addSubtask(subtask2);
        assert manager.addSubtask(subtask3);
        assert manager.deleteTask(task1.getId());
        assert manager.deleteSubtask(subtask1.getId());
        assert manager.deleteEpic(epic.getId());
    }

}