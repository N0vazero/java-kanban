package manager;

import task.Epic;
import task.SubTask;
import task.Task;
import java.util.List;

public interface TaskManager {

    boolean addTask(Task task);

    boolean addEpic(Epic epic);

    boolean addSubtask(SubTask subtask);

    boolean updateTask(Task task);

    boolean updateEpic(Epic epic);

    void updateSubtask(SubTask subtask);

    boolean deleteTask(Integer id);

    boolean deleteSubtask(Integer id);

    boolean deleteEpic(Integer id);

    Task getTask(Integer id);

    Epic getEpic(Integer id);

    SubTask getSubtask(Integer id);

    List<Integer> getSubtasksFromEpic(int id);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    List<Task> getTasks();

    List<SubTask> getSubTasks();

    List<Epic> getEpics();

    String printHistory();

    List<Task> getHistory();
}
