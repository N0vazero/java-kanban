import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefalut();

        Task task1 = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
        Task task2 = new Task("Отправиться в бар", null, Status.IN_PROGRESS);
        Task task3 = new Task("Лечь спать");
        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);
        inMemoryTaskManager.addTask(task3);

        Epic epic = new Epic("Планы на выходные");
        inMemoryTaskManager.addEpic(epic);

        SubTask subtask1 = new SubTask(epic.getId(), "Встать пораньше, понаблюдать за птицами");
        SubTask subtask2 = new SubTask(epic.getId(), "Вкусно позавтракать");
        SubTask subtask3 = new SubTask(epic.getId(), "Выйти на прогулку");
        SubTask subtask4 = new SubTask(epic.getId(), "Отдохнуть от мирской суеты", null, Status.IN_PROGRESS, 5);
        SubTask subtask5 = new SubTask(epic.getId(), "Прочитать пару десятков страниц любимой книги",
                null, Status.IN_PROGRESS, 6);
        SubTask subtask6 = new SubTask(epic.getId(), "Тренироваться", null, Status.IN_PROGRESS, 7);
        SubTask subtask7 = new SubTask(epic.getId(), "Убраться в квартире", null, Status.DONE, 5);
        SubTask subtask8 = new SubTask(epic.getId(), "Приготовиться к походу", null, Status.DONE, 6);
        SubTask subtask9 = new SubTask(epic.getId(), "Списаться с друзьями", null, Status.DONE, 7);

        inMemoryTaskManager.addSubtask(subtask1);
        inMemoryTaskManager.addSubtask(subtask2);
        inMemoryTaskManager.addSubtask(subtask3);
        print(inMemoryTaskManager.getTasks());
        print(inMemoryTaskManager.getEpics());
        print(inMemoryTaskManager.getSubTasks());
        print("\n");

        inMemoryTaskManager.getTask(1, true);
        inMemoryTaskManager.getTask(2, true);
        inMemoryTaskManager.getTask(3, true);
        inMemoryTaskManager.getTask(4, true);
        inMemoryTaskManager.getEpic(4, true);
        print(inMemoryTaskManager.getHistoryManager().toString());
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
