package task;

import java.util.ArrayList;


public class Epic extends Task {
    private ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(String name) {
        super(name);
    }

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(String name, String description, Integer id) {
        super(name, description);
        this.id = id;
    }

    public Epic(String name, String description, Integer id, ArrayList<Integer> subtasks) {
        super(name, description);
        this.id = id;
        this.subtasks = subtasks;
    }


    public ArrayList<Integer> getSubTasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "task.Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
