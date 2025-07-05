package manager;

import java.util.ArrayList;
import java.util.List;

public interface HistoryManager<T> {
    List<T> getHistory();

    void add(T obj);
}
