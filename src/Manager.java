import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Manager {
    private int id;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subtasks;
    private HashMap<Integer, Epic> epics;

    public Manager() {
        id = 0;
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public void addTask(Task task) {
        task.setId(id);
        tasks.put(id, task);
        idIncrease();
    }

    public void addEpic(Epic epic) {
        epic.setId(id);
        epics.put(id, epic);
        for (Integer i : this.getSubtasksFromEpic(epic.getId())) {
            this.addSubtask(this.getSubtask(i));
        }
        this.updateStatusEpic(epic.getId());
        idIncrease();
    }

    public void addSubtask(SubTask subtask) {
        subtask.setId(id);
        subtasks.put(id, subtask);
        Epic epic = this.getEpic(subtask.getIdEpic());
        epic.getSubTasks().add(subtask.getId());
        this.updateStatusEpic(epic.getId());
        idIncrease();
    }

    public boolean updateTask(Task task) {
        if (task.getId() != null) {
            if (tasks.containsKey(task.getId())) {
                tasks.put(task.getId(), task);
                return true;
            }
        }
        return false;
    }

    public boolean updateEpic(Epic epic) {
        if (epic.getId() != null) {
            if (epics.containsKey(epic.getId())) {
                epics.put(epic.getId(), epic);
                for (Integer i : this.getSubtasksFromEpic(epic.getId())) {
                    this.addSubtask(this.getSubtask(i));
                }
                this.updateStatusEpic(epic.getId());
                return true;
            }
        }
        return false;

    }

    public void updateSubtask(SubTask subtask) {
        if (subtask.getId() != null) {
            if (subtasks.containsKey(subtask.getId())) {
                subtasks.put(subtask.getId(), subtask);
                Epic epic = this.getEpic(subtask.getIdEpic());
                epic.getSubTasks().add(subtask.getId());
                this.updateStatusEpic(epic.getId());
            }
        }
    }

    private void idIncrease() {
        id++;
    }

    public boolean deleteTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }

    private boolean deleteSubtaskWithoutUpdate(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            return true;
        }
        return false;
        /* Вспомогательный метод для полного удаления subtask из epic
        Удаление каждой subtask.getId() не требуется, updateStatusEpic() не требуется, поскольку
        epic по итогу удаляется, метод призван уменьшить трату ресурсов */
    }

    public boolean deleteSubtask(int id) {
        if (subtasks.containsKey(id)) {
            SubTask subtask = subtasks.get(id);
            Epic epic = this.getEpic(subtask.getIdEpic());
            epic.getSubTasks().remove(subtask.getId());
            subtasks.remove(id);
            this.updateStatusEpic(epic.getId());
            return true;
        }
        return false;
    }

    public boolean deleteEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            ArrayList<Integer> subtasks = epic.getSubTasks();
            for (Integer i : subtasks) {
                this.deleteSubtaskWithoutUpdate(i);
            }
            epics.remove(id);
            return true;
        }
        return false;
    }

    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Epic getEpic(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

    public SubTask getSubtask(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }
        return null;
    }

    public ArrayList<Integer> getSubtasksFromEpic(int id) {
        Epic epic = this.getEpic(id);
        return epic.getSubTasks();
    }

    private void updateStatusEpic(int id) {
        Epic epic = this.getEpic(id);
        boolean isNew = true;
        boolean isDone = true;
        if (this.getSubtasksFromEpic(id).isEmpty()) {
            isNew = true;
        }
        for (Integer i : this.getSubtasksFromEpic(id)) {
            if (this.getSubtask(i).getStatus() == Status.IN_PROGRESS) {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
            if (this.getSubtask(i).getStatus() == Status.DONE) {
                isNew = false;
            }
            if (this.getSubtask(i).getStatus() == Status.NEW) {
                isDone = false;
            }
        }
        if (isNew) {
            epic.setStatus(Status.NEW);
        } else if (isDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    public boolean setStatusSubtask(int id, Status status) {
        SubTask subtask = this.getSubtask(id);
        if (subtask != null) {
            subtask.setStatus(status);
            this.updateStatusEpic(subtask.getIdEpic());
            return true;
        }
        return false;
    }

    public void deleteAllTasks() {
        Task[] temp = new Task[0];
        for (Task task : tasks.values().toArray(temp)) {
            this.deleteTask(((Task)(task)).getId());
        }
    }

    public void deleteAllSubtasks() {
        SubTask[] temp = new SubTask[0];
        for (SubTask subtask : subtasks.values().toArray(temp)) {
            this.deleteSubtask(((SubTask)(subtask)).getId());
        }
    }

    public void deleteAllEpics() {
        Epic[] temp = new Epic[0];
        for (Epic epic : epics.values().toArray(temp)) {
            this.deleteEpic(((Epic)(epic)).getId());
        }
    }

    public Collection<Task> getTasks() {
        return tasks.values();
    }

    public Collection<SubTask> getSubtasks() {
        return subtasks.values();
    }

    public Collection<Epic> getEpics() {
        return epics.values();
    }
}
