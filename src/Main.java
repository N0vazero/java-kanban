import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
        manager.addTask(task);

        Epic epic = new Epic("Планы на выходные");
        manager.addEpic(epic);

        EpicShard subtask1 = new EpicShard(epic.getId(), "Приготовить вкусную кашу");
        EpicShard subtask2 = new EpicShard(epic.getId(), "Убраться в квартире");
        EpicShard subtask3 = new EpicShard(epic.getId(), "Погулять");
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);

        print(manager.getTasks());
        print(manager.getEpics());
        print(manager.getSubtasks());
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
