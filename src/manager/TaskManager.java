package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;

public interface TaskManager {
    HistoryManager<Task> getHistoryManager();

    boolean addTask(Task task);

    boolean addEpic(Epic epic);

    boolean addSubtask(SubTask subtask);

    boolean updateTask(Task task);

    boolean updateEpic(Epic epic);

    void updateSubtask(SubTask subtask);

    boolean deleteTask(int id);

    boolean deleteSubtask(int id);

    boolean deleteEpic(int id);

    Task getTask(int id, boolean isUserCall);

    Epic getEpic(int id, boolean isUserCall);

    SubTask getSubtask(int id, boolean isUserCall);

    ArrayList<Integer> getSubtasksFromEpic(int id);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    ArrayList<Task> getTasks();

    ArrayList<SubTask> getSubTasks();

    ArrayList<Epic> getEpics();
}
