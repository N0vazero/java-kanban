package manager;

import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;
import util.HistoryList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final HistoryManager<Task> historyManager = new InMemoryHistoryManager();
    private int id;

    public InMemoryTaskManager() {
        id = 1;
    }

    @Override
    public boolean addTask(Task task) {
        task.setId(id);
        tasks.put(id, task);
        idIncrease();
        return true;
    }

    @Override
    public boolean addEpic(Epic epic) {
        epic.setId(id);
        epics.put(id, epic);
        for (Integer idSubtask : this.getSubtasksFromEpic(epic.getId())) {
            this.addSubtask(this.getSubtask(idSubtask, false));
        }
        this.updateStatusEpic(epic.getId());
        idIncrease();
        return true;
    }

    @Override
    public boolean addSubtask(SubTask subtask) {
        Epic epic = this.getEpic(subtask.getIdEpic(), false);
        if (epic != null) {
            subtask.setId(id);
            subTasks.put(id, subtask);
            epic.getSubTasks().add(subtask.getId());
            this.updateStatusEpic(epic.getId());
            idIncrease();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTask(Task task) {
        if (task.getId() != null) {
            if (tasks.containsKey(task.getId())) {
                tasks.put(task.getId(), task);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateEpic(Epic epic) {
        if (epic.getId() != null) {
            if (epics.containsKey(epic.getId())) {
                epics.put(epic.getId(), epic);
                Integer[] temp = new Integer[0];
                Integer[] subtasksId = epic.getSubTasks().toArray(temp);
                for (Integer idSubtask : subtasksId) {
                    this.updateSubtask(this.getSubtask(idSubtask, false));
                }
                this.updateStatusEpic(epic.getId());
                return true;
            }
        }
        return false;

    }

    @Override
    public void updateSubtask(SubTask subtask) {
        if (subtask.getId() != null) {
            if (subTasks.containsKey(subtask.getId())) {
                subTasks.put(subtask.getId(), subtask);
                Epic epic = this.getEpic(subtask.getIdEpic(), false);
                epic.getSubTasks().add(subtask.getId());
                this.updateStatusEpic(epic.getId());
            }
        }
    }

    private void idIncrease() {
        id++;
    }

    @Override
    public boolean deleteTask(Integer id) {
        if (id != null) {
            if (tasks.containsKey(id)) {
                tasks.remove(id);
                return true;
            }
        }
        return false;
    }

    private boolean deleteSubtaskWithoutUpdate(Integer id) {
        if (id != null) {
            if (subTasks.containsKey(id)) {
                subTasks.remove(id);
                return true;
            }
        }
        return false;
        /* Вспомогательный метод для полного удаления subtask из epic
        Удаление каждой subtask.getId() не требуется, updateStatusEpic() не требуется, поскольку
        epic по итогу удаляется, метод призван уменьшить трату ресурсов */
    }

    @Override
    public boolean deleteSubtask(Integer id) {
        if (id != null) {
            if (subTasks.containsKey(id)) {
                SubTask subtask = subTasks.get(id);
                Epic epic = this.getEpic(subtask.getIdEpic(), false);
                epic.getSubTasks().remove(subtask.getId());
                subTasks.remove(id);
                this.updateStatusEpic(epic.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEpic(Integer id) {
        if (id != null) {
            if (epics.containsKey(id)) {
                Epic epic = epics.get(id);
                ArrayList<Integer> subtasks = epic.getSubTasks();
                for (Integer idSubtask : subtasks) {
                    this.deleteSubtaskWithoutUpdate(idSubtask);
                }
                epics.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Task getTask(Integer id, boolean isUserCall) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            if (isUserCall) {
                historyManager.add(task);
            }
            return task;
        }
        return null;
    }

    @Override
    public Epic getEpic(Integer id, boolean isUserCall) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            if (isUserCall) {
                historyManager.add(epic);
            }
            return epic;
        }
        return null;
    }

    @Override
    public SubTask getSubtask(Integer id, boolean isUserCall) {
        if (subTasks.containsKey(id)) {
            SubTask subTask = subTasks.get(id);
            if (isUserCall) {
                historyManager.add(subTask);
            }
            return subTask;
        }
        return null;
    }

    @Override
    public ArrayList<Integer> getSubtasksFromEpic(int id) {
        Epic epic = this.getEpic(id, false);
        return epic.getSubTasks();
    }

    private void updateStatusEpic(int id) {
        Epic epic = this.getEpic(id, false);
        boolean isNew = true;
        boolean isDone = true;
        if (this.getSubtasksFromEpic(id).isEmpty()) {
            isNew = true;
        }
        for (Integer idSubtask : this.getSubtasksFromEpic(id)) {
            if (this.getSubtask(idSubtask, false).getStatus() == Status.IN_PROGRESS) {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
            if (this.getSubtask(idSubtask, false).getStatus() == Status.DONE) {
                isNew = false;
            }
            if (this.getSubtask(idSubtask, false).getStatus() == Status.NEW) {
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

    @Override
    public void deleteAllTasks() {
        Task[] temp = new Task[0];
        for (Task task : tasks.values().toArray(temp)) {
            this.deleteTask(((Task) (task)).getId());
        }
    }

    @Override
    public void deleteAllSubtasks() {
        SubTask[] temp = new SubTask[0];
        for (SubTask subtask : subTasks.values().toArray(temp)) {
            this.deleteSubtask(((SubTask) (subtask)).getId());
        }
    }

    @Override
    public void deleteAllEpics() {
        Epic[] temp = new Epic[0];
        for (Epic epic : epics.values().toArray(temp)) {
            this.deleteEpic(((Epic) (epic)).getId());
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public HistoryList getHistory() {
        return historyManager.getHistory();
    }

    public String printHistory() {
        return historyManager.toString();
    }
}
