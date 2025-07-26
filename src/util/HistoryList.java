package util;

import task.Task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class HistoryList implements Iterable<Task> {
    private final Map<Integer, Node<Task>> hashMap = new HashMap<>();
    private Node<Task> head = null;
    private Node<Task> tail = null;

    private int size = 0;

    public Iterator<Task> iterator() {
        return new MyLinkedListIterator();
    }

    public void addLast(Task task) {
        Node<Task> existingNode = hashMap.get(task.getId());
        if (existingNode != null) {
            remove(existingNode);
        }
        Node<Task> newNode = new Node<>(task, tail, null);
        hashMap.put(task.getId(), newNode);
        if (tail != null) {
            tail.setNext(newNode);
        }
        if (size == 0) {
            head = newNode;
        }
        tail = newNode;
        size++;
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
        size--;
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
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
