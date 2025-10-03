import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class Tutorial_2 {

    //Q1
    public static class ArrayPQ {
        private int[] data;
        private int count = 0;

        // Constructor
        public ArrayPQ(int size) {
            data = new int[size];
        }

        // Check if empty
        public boolean isEmpty() {
            return count == 0;
        }

        // Add element (maintain sorted order)
        public void add(int elt) {
            insert(data, count++, elt);
        }

        // Extract the largest element (end of array)
        public int extractMax() {
            return data[--count];
        }

        private void insert(int[] data, int count, int elt) {
            int i = count - 1;
            while ( i >= 0 && data[i] > elt) {
                data[i + 1] = data[i];
                i--;
            }
            data [i + 1] = elt;
        }
    }

    // Q2
    public static class UnsortedArrayPQ {
        private int[] data;
        private int count = 0;

        // Constructor
        public UnsortedArrayPQ(int size) {
            data = new int[size];
        }

        // Remains the same
        public boolean isEmpty() {
            return count == 0;
        }

        // Add element (maintain sorted order)
        public void add(int elt) {
            insert(data, count++, elt);
        }

        // Extract the largest element (end of array)
        public int extractMax() {
            if (isEmpty()) throw  new NoSuchElementException("Array is empty");
            int maxIndex = 0;
            for (int i = 1; i < count; i++) {
                if (data[i] > data[maxIndex]) {
                    maxIndex = i;
                }
            }
            int max =  data[maxIndex];
            data[maxIndex] = data[--count];
            return max;
        }

        private void insert(int[] data, int count, int elt) {
            data[count] = elt;
        }
    }

    // Q3
    // Queue interface
    public interface MyQueue {
        boolean isEmpty();
        void enqueue(int elt);
        int dequeue();
    }

    // Array-based Queue implementation
    public static class ArrayQueue implements MyQueue {

        private int[] data;
        private int front = 0;
        private int rear = 0;
        private int count = 0;

        public ArrayQueue(int size) {
            data = new int[size];
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }

        @Override
        public void enqueue(int elt) {
            // your code here
            if (count == data.length) {
                throw new IllegalArgumentException("Queue is full");
            }
            data[rear] = elt;
            rear = (rear + 1) % data.length;
            count++;
        }

        @Override
        public int dequeue() {
            // your code here
            if (count == 0) {
                throw new IllegalArgumentException("Queue is empty");
            }
            int elt = data[front];
            front = (front + 1) % data.length;
            count--;
            return elt;
        }
    }

    // Q4
    public static Queue<Integer> reverseQueue(Queue<Integer> input) {
        if (input == null) throw  new IllegalArgumentException("Input is null");
        Stack<Integer> stack = new Stack<>();
        for (Integer elt : input) {
            stack.push(elt);
        }
        Queue<Integer> reversed = new LinkedList<>();
        while (!stack.isEmpty()) {
            reversed.add(stack.pop());
        }
        return reversed;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);

        System.out.println("Original queue: " + q);
        Queue<Integer> reversed = reverseQueue(q);
        System.out.println("Reversed queue: " + reversed);
    }

}
