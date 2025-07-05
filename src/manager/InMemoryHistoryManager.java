package manager;

import task.Task;

import java.util.ArrayList;



public class InMemoryHistoryManager implements HistoryManager<Task> {
    private final ArrayList<Task> history = new ArrayList<>();;
    private final Integer maxHistoryLength = 10;

    public InMemoryHistoryManager() {

    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }

    @Override
    public void add(Task task) {
        if (history.size() >= maxHistoryLength) {
            history.removeFirst();
        }
        history.addLast(task.copy());
    }

    @Override
    public String toString() {
        String resultString = "History of get-calls methods:\n";
        for (Task task : history) {
            resultString += "\t" + task + "\n";
        }
        return resultString;

    }
}
