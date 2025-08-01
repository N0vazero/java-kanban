package manager;

import java.util.List;

public interface HistoryManager<T> {
    void remove(int id);

    List<T> getHistory();

    void add(T obj);
}
