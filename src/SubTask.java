public class SubTask extends Task {
    private int idEpic;

    public SubTask(int idEpic) {
        super();
        this.idEpic = idEpic;
    }

    public SubTask(int idEpic, String name) {
        super(name);
        this.idEpic = idEpic;
    }

    public SubTask(int idEpic, String name, String description) {
        super(name, description);
        this.idEpic = idEpic;
    }
    public SubTask(int idEpic, String name, String description, Status status) {
        super(name, description, status);
        this.idEpic = idEpic;
    }

    public SubTask(int idEpic, String name, String description, Status status, Integer id) {
        super(name, description, status);
        this.idEpic = idEpic;
        this.id = id;
    }

    public int getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "idEpic=" + idEpic +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
