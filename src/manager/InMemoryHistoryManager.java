package manager;

import task.Task;
import util.HistoryList;

import java.util.List;


public class InMemoryHistoryManager implements HistoryManager<Task> {
    private final HistoryList history = new HistoryList();

    @Override
    public HistoryList getHistory() {
        return history;
    }

    @Override
    public void add(Task task) {
        history.addLast(task.copy());
    }

    @Override
    public void remove(int id) {
        for (Task task : history) {
            if (task.getId().equals(id)) {
                history.remove(task);
            }
        }
    }

    @Override
    public String toString() {
        String resultString = "History of get-calls methods:\n";
        for (Task task : history) {
            resultString += "\t" + task + " h: " + task.hashCode() + "\n";
        }
        return resultString;

    }
}
