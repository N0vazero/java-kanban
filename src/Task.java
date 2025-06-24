public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected Status status;

    public Task() {
        this.status = Status.NEW;
    }
    public Task(String name) {
        this();
        this.name = name;
    }

    public Task(String name, String description) {
        this(name);
        this.description = description;
    }
    public Task(String name, String description, Status status) {
        this(name, description);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
