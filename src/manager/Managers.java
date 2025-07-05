package manager;

public class Managers {
    private static final InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

    public static TaskManager getDefalut() {
        return inMemoryTaskManager;
    }
}
