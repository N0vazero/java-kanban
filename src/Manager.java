import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


public class Manager {
    private int id;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, EpicShard> subtasks;
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
        for (EpicShard subtask : this.getSubtasksFromEpic(epic.getId())) {
            this.addSubtaskWithoutUpdate(subtask);
        }
        this.updateEpic(epic.getId());
        idIncrease();
    }

    public void addSubtask(EpicShard subtask) {
        subtask.setId(id);
        subtasks.put(id, subtask);
        Epic epic = this.getEpic(subtask.getIdEpic());
        epic.getSubTasks().add(subtask);
        this.updateEpic(epic.getId());
        idIncrease();
    }

    public void setTask(int id, Task task) {
        if (tasks.containsKey(id)) {
            this.deleteTask(id);
            task.setId(id);
            tasks.put(id, task);
        }
    }

    public boolean setEpic(int id, Epic epic) {
        if (epics.containsKey(id)) {
            this.deleteEpic(id);
            epic.setId(id);
            epics.put(id, epic);
            for (EpicShard subtask : this.getSubtasksFromEpic(epic.getId())) {
                this.addSubtaskWithoutUpdate(subtask);
            }
            this.updateEpic(epic.getId());
            return true;
        }
        return false;

    }

    public void setSubtask(int id, EpicShard subtask) {
        if (subtasks.containsKey(id)) {
            this.deleteSubtask(id);
            subtask.setId(id);
            subtasks.put(id, subtask);
            Epic epic = this.getEpic(subtask.getIdEpic());
            epic.getSubTasks().add(subtask);
            this.updateEpic(epic.getId());
        }
    }

    private void addSubtaskWithoutUpdate(EpicShard subtask) {
        subtask.setId(id);
        subtasks.put(id, subtask);
        idIncrease();
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
    }

    public boolean deleteSubtask(int id) {
        if (subtasks.containsKey(id)) {
            EpicShard subtask = subtasks.get(id);
            Epic epic = this.getEpic(subtask.getIdEpic());
            epic.getSubTasks().remove(subtask);
            subtasks.remove(id);
            this.updateEpic(epic.getId());
            return true;
        }
        return false;
    }

    public boolean deleteEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            ArrayList<EpicShard> subtasks = epic.getSubTasks();
            for (EpicShard subtask : subtasks) {
                this.deleteSubtaskWithoutUpdate(subtask.getId());
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

    public EpicShard getSubtask(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }
        return null;
    }

    public ArrayList<EpicShard> getSubtasksFromEpic(int id) {
        Epic epic = this.getEpic(id);
        return epic.getSubTasks();
    }

    private void updateEpic(int id) {
        Epic epic = this.getEpic(id);
        boolean isNew = true;
        boolean isDone = true;
        if (this.getSubtasksFromEpic(id).isEmpty()) {
            isNew = true;
        }
        for (EpicShard subtask : this.getSubtasksFromEpic(id)) {
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                this.setStatusEpic(id, Status.IN_PROGRESS);
                return;
            }
            if (subtask.getStatus() == Status.DONE) {
                isNew = false;
            }
            if (subtask.getStatus() == Status.NEW) {
                isDone = false;
            }
        }
        if (isNew) {
            this.setStatusEpic(id, Status.NEW);
        } else if (isDone) {
            this.setStatusEpic(id, Status.DONE);
        } else {
            this.setStatusEpic(id, Status.IN_PROGRESS);
        }
    }

    private boolean setStatusEpic(int id, Status status) {
        Epic epic = this.getEpic(id);
        if (epic != null) {
            epic.setStatus(status);
            return true;
        }
        return false;
    }

    public boolean setStatusTask(int id, Status status) {
        Task task = this.getTask(id);
        if (task != null) {
            task.setStatus(status);
            return true;
        }
        return false;
    }

    public boolean setStatusSubtask(int id, Status status) {
        EpicShard subtask = this.getSubtask(id);
        if (subtask != null) {
            subtask.setStatus(status);
            this.updateEpic(subtask.getIdEpic());
            return true;
        }
        return false;
    }

    public void deleteAllTasks() {
        for (Object task : tasks.values().toArray()) {
            this.deleteTask(((Task)(task)).getId());
        }
    }

    public void deleteAllSubtasks() {
        for (Object subtask : subtasks.values().toArray()) {
            this.deleteSubtask(((EpicShard)(subtask)).getId());
        }
    }

    public void deleteAllEpics() {
        for (Object epic : epics.values().toArray()) {
            this.deleteEpic(((Epic)(epic)).getId());
        }
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, EpicShard> getSubtasks() {
        return subtasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }
}
