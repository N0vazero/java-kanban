package manager;

import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InMemoryHistoryManagerTest {

    @Test
    public void gettedTaskVersionAreFrezes() {
        TaskManager manager = Managers.getDefalut();
        Task task = new Task("t1");
        manager.addTask(task);
        manager.getTask(task.getId(), true);
        task.setDescription("There is some changes");
        Task oldTask = manager.getHistory().getFirst();
        assertNotEquals(task.getDescription(), oldTask.getDescription());
    }

}