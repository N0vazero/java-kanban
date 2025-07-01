import manager.Manager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
        Task task2 = new Task("Отправиться в бар", null, Status.IN_PROGRESS);
        Task task3 = new Task("Лечь спать");
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);

        Epic epic = new Epic("Планы на выходные");
        manager.addEpic(epic);

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

        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);
        print(manager.getTasks());
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");

        /*
        manager.updateSubtask(subtask4);
        manager.updateSubtask(subtask5);
        manager.updateSubtask(subtask6);
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.updateSubtask(subtask7);
        manager.updateSubtask(subtask8);
        manager.updateSubtask(subtask9);
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.deleteEpic(epic.getId());
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.deleteSubtask(subtask3.getId());
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.deleteTask(task3.getId());
        print(manager.getTasks());
        print("\n");
         */

        /*
        print(manager.getSubtasksFromEpic(epic.getId()));
         */

        /*
        Task task4 = new Task("Проспать работу", null, Status.DONE, task1.getId());
        manager.updateTask(task4);
        print(manager.getTasks());
        print("\n");
         */

        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(subtask1.getId());
        subtasks.add(subtask2.getId());
        subtasks.add(subtask3.getId());
        Epic epicTemp = new Epic("Планы на один выходной", null, epic.getId(), subtasks);

        /*
        manager.updateEpic(epicTemp);
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.deleteAllTasks();
        print(manager.getTasks());
        print("\n");
         */

        /*
        manager.deleteAllSubtasks();
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */

        /*
        manager.deleteAllEpics();
        print(manager.getEpics());
        print(manager.getSubtasks());
        print("\n");
         */
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
