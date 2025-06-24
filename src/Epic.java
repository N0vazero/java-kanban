import java.util.ArrayList;


public class Epic extends Task {
    private ArrayList<EpicShard> subtasks = new ArrayList<>();

    public Epic(String name) {
        super(name);
    }

    public Epic(String name, String description) {
        super(name, description);
    }
    public Epic(String name, String description, ArrayList<EpicShard> subtasks) {
        super(name, description);
        this.subtasks = subtasks;
    }

    public ArrayList<EpicShard> getSubTasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
