// Single-file implementation wrapped in a public class named Tutorial_4.
// The circular linked list and node are private inner classes.
import java.util.PriorityQueue;
import java.util.LinkedList;

public class Tutorial_4 {

    // -------- private node --------
    private static final class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // -------- private circular singly linked list of ints --------
    private static class CircularLinkedList {
        private Node head; // first node
        private Node tail; // last node; when non-empty, tail.next == head

        public CircularLinkedList() {
            head = null;
            tail = null;
        }

        // Insert new value at the end
        public void insert(int data) {
            Node n = new Node(data);
            if (head == null) {
                head = n;
                tail = n;
                n.next = head; // self-loop
            } else {
                n.next = head;
                tail.next = n;
                tail = n;
            }
        }

        // Delete first occurrence of key (no-op if not found)
        public void delete(int key) {
            if (head == null) return;

            // One-node case
            if (head == tail && head.data == key) {
                head = tail = null;
                return;
            }

            Node curr = head;
            Node prev = tail;

            // Traverse once around the ring
            do {
                if (curr.data == key) {
                    if (curr == head) {
                        head = head.next;
                        tail.next = head;
                    } else {
                        prev.next = curr.next;
                        if (curr == tail) tail = prev;
                    }
                    return; // delete first match only
                }
                prev = curr;
                curr = curr.next;
            } while (curr != head);
        }

        // Print list contents
        public void printList() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            System.out.print("Linked List Elements : ");
            Node p = head;
            do {
                System.out.print(p.data + " ");
                p = p.next;
            } while (p != head);
            System.out.println();
        }

        public int findMax(CircularLinkedList list) {
            if (list.head == null)
                throw new java.util.NoSuchElementException("List is empty");

            Node curr = list.head.next;
            int best = list.head.data;

            while (curr != list.head) {
                if (curr.data > best) {
                    best = curr.data;
                }
                curr = curr.next;
            }
            return best;
        }
    }

    public int findMax (LinkedList<Integer> list) {
        if (list.isEmpty())
            throw new java.util.NoSuchElementException("List is empty");

        int best = list.getFirst();

        for (Integer v : list) {
            if (v > best) {
                best = v;
            }
        }
        return best;
    }

    private CircularLinkedList makeCircularFrom (PriorityQueue<Integer> pq, LinkedList<Integer> list) {
        if (pq == null && list == null)
            throw new IllegalArgumentException("Both are null.");

        CircularLinkedList out = new CircularLinkedList();

        if (pq != null) {
            PriorityQueue<Integer> heap = new PriorityQueue<>(pq);
            while (!heap.isEmpty()) {
                Integer v =  heap.poll();
                if (v != null) out.insert(v);
            }
        }

        if (list != null) {
            for (Integer v : list) {
                if (v != null) out.insert(v);
            }
        }

        return out;
    }

    private CircularLinkedList mergeCLSorted(CircularLinkedList A, CircularLinkedList B) {
        CircularLinkedList out  = new CircularLinkedList();

        if (A == null || A.head == null) {
            if (B == null || B.head == null) {
                return out;
            }
            Node p = B.head;
            do {
                out.insert(p.data);
                p = p.next;
            } while (p !=  B.head);
            return out;
        }

        if (B == null || B.head == null) {
            Node p = A.head;
            do {
                out.insert(p.data);
                p = p.next;
            } while (p != A.head);
            return out;
        }

        Node pa = A.head, endA = A.head;
        Node pb = B.head, endB = B.head;

        while (pa != null || pb != null) {
            if (pb == null || (pa != null && pa.data <= pb.data)) {
                out.insert(pa.data);
                pa = pa.next;
                if (pa == endA) {
                    pa = null;
                }
            } else {
                out.insert(pb.data);
                pb = pb.next;
                if (pb == endB) {
                    pb = null;
                }
            }
        }
        return out;
    }
}

