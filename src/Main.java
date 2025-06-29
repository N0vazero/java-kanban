public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task = new Task("Отработать смену на заводе", "8:00 - 17:00", Status.DONE);
        manager.addTask(task);

        Epic epic = new Epic("Планы на выходные");
        manager.addEpic(epic);

        SubTask subtask1 = new SubTask(epic.getId(), "Приготовить вкусную кашу");
        SubTask subtask2 = new SubTask(epic.getId(), "Убраться в квартире");
        SubTask subtask3 = new SubTask(epic.getId(), "Погулять");
        SubTask subtask4 = new SubTask(epic.getId(), "Отдохнуть от бытовой суеты", null, Status.IN_PROGRESS, 4);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);

        print(manager.getTasks());
        print(manager.getEpics());
        print(manager.getSubtasks());

        manager.updateSubtask(subtask4);

        print(manager.getTasks());
        print(manager.getEpics());
        print(manager.getSubtasks());
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
