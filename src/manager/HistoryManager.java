package manager;

import util.HistoryList;

public interface HistoryManager<T> {
    void remove(int id);

    HistoryList getHistory();

    void add(T obj);
}
