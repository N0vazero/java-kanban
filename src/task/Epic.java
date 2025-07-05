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
        this.subtasks = (ArrayList<Integer>)(subtasks.clone()); // Исключения изменения приватного
        // списка путем изменения списка из аргумента
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Epic) {
            boolean isIdEquals = this.id.equals( ( (Epic) o).id);
            boolean isSubtasksEquals = this.subtasks.equals( ((Epic) o).subtasks);
            return isSubtasksEquals && isIdEquals;
        }
        return false;
    }

    @Override
    public Task copy() {
        return new Epic(name, description, id, subtasks);
    }
}
