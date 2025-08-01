package util;

import task.Task;

import java.util.*;

public class HistoryList implements List<Task>, Iterable<Task> {
    private final Map<Integer, Node<Task>> hashMap = new HashMap<>();
    private Node<Task> head = null;
    private Node<Task> tail = null;

    public Iterator<Task> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        return hashMap.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return hashMap.values().toArray(a);
    }

    @Override
    public boolean add(Task task) {
        addLast(task);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return hashMap.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        for (Object o : c) {
            if (o instanceof Task) {
                addLast((Task)o);
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Task> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void addLast(Task task) {
        Node<Task> existingNode = hashMap.get(task.getId());
        if (existingNode != null) {
            remove(existingNode);
        }
        Node<Task> newNode = new Node<>(task, tail, null);
        if (hashMap.isEmpty()) {
            head = newNode;
        }
        hashMap.put(task.getId(), newNode);
        if (tail != null) {
            tail.setNext(newNode);
        }

        tail = newNode;
    }

    private void remove(Node<Task> node) {
        Node<Task> prev = node.getPrev();
        Node<Task> next = node.getNext();

        if (prev == null) {
            next.setPrev(null);
            head = next;
        } else if (next == null) {
            prev.setNext(null);
            tail = prev;
        } else {
            prev.setNext(next);
            next.setPrev(prev);
        }

        Task task = node.getData();

        hashMap.remove(task.getId());
    }

    public void remove(Task task) {
        Node<Task> node = hashMap.get(task.getId());
        remove(node);
    }

    public Task getFirst() {
        return head.getData();
    }

    public Task getLast() {
        return tail.getData();
    }

    public void clear() {
        hashMap.clear();
        head = null;
        tail = null;
    }

    @Override
    public Task get(int index) {
        Node<Task> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public Task set(int index, Task element) {
        Node<Task> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        Task oldData = current.getData();
        current = new Node<>(element, current.getPrev(), current.getNext());
        return oldData;
    }

    @Override
    public void add(int index, Task element) {
        Node<Task> current = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                Node<Task> prev = current.getPrev();
                Node<Task> incoming = new Node<>(element, prev, current);
                prev = new Node<>(prev.getData(), prev.getPrev(), incoming);

                hashMap.put(incoming.getData().getId(), incoming);
            }
            current = current.getNext();
        }
    }

    @Override
    public Task remove(int index) {
        Node<Task> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        Task oldData = current.getData();
        remove(current);
        return oldData;
    }

    @Override
    public int indexOf(Object o) {
        if (o instanceof Task) {
            Node<Task> current = head;
            for (int i = 0; i < hashMap.size(); i++) {
                if (current.getData() == (Task) o) {
                    return i;
                }
                current = current.getNext();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return indexOf(o);
    }

    @Override
    public ListIterator<Task> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Task> listIterator(int index) {
        return null;
    }

    @Override
    public List<Task> subList(int fromIndex, int toIndex) {
        return null;
    }

    public int size() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Task) {
            return hashMap.containsValue(((Task) o).getId());
        } else if (o instanceof Integer) {
            return hashMap.containsKey((Integer) o);
        } else {
            return false;
        }
    }

    private static class Node<E> {
        private final E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            prev = null;
            next = null;
        }

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }
    }

    private class MyLinkedListIterator implements Iterator<Task> {
        private Node<Task> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Task next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Task data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
