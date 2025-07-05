package manager;

import java.util.ArrayList;

public interface HistoryManager<T> {
    ArrayList<T> getHistory();

    void add(T obj);
}
