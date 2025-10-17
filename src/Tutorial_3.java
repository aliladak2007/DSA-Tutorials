public class Tutorial_3 {

    // Node class for singly linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // Question 1 – Demonstrate the sequence of pointer manipulations
    public static void question1() {
        Node p = new Node(3, new Node(5, new Node(4, new Node(1, null))));
        System.out.println("Initial list: ");
        printList(p);

        // 1. p.next = p.next.next;
        p.next = p.next.next;
        System.out.println("After p.next = p.next.next:");
        printList(p);

        // 2. p.next = new Node(7, p.next);
        p.next = new Node(7, p.next);
        System.out.println("After p.next = new Node(7, p.next):");
        printList(p);

        // 3. p.next.next = new Node(8);
        p.next.next = new Node(8);
        System.out.println("After p.next.next = new Node(8):");
        printList(p);

        // 4. p.next.next = p;
        p.next.next = p;
        System.out.println("After p.next.next = p (circular list):");
        // Printing safely to avoid infinite loop
        printCircular(p, 6);
    }

    // Question 2 – Modify method from SLList
    static class SLList {
        Node head;
    }

    public static void modify(SLList list) {
        if (list.head != null && list.head.next != null) {
            Node tmp = list.head.next;
            list.head.next = tmp.next;
            tmp.next = list.head;
            list.head = tmp;
        }
    }

    public static void question2() {
        SLList list = new SLList();
        list.head = new Node(1, new Node(2, new Node(3, null)));

        System.out.println("Before modify:");
        printList(list.head);

        modify(list);

        System.out.println("After modify:");
        printList(list.head);
    }

    // Question 3 – Queue using singly linked list
    static class Queue {
        Node front, rear;

        public boolean isEmpty() {
            return front == null;
        }

        public void enqueue(int data) {
            Node newNode = new Node(data);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }

        public Integer dequeue() {
            if (isEmpty()) return null;
            int val = front.data;
            front = front.next;
            if (front == null) rear = null;
            return val;
        }
    }

    public static void question3() {
        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Dequeued: " + q.dequeue());
    }

    // Question 4 – Compare two singly linked lists
    public static boolean areIdentical(Node head1, Node head2) {
        Node a = head1, b = head2;
        if ((a != null && b == null ) || (a == null && b != null))
            return false;
        while (a != null && b != null) {
            if (a.data != b.data)
                return false;
            a = a.next;
            b = b.next;
        }
        if (a != null || b != null)
            return false;
        return true;
    }

    public static void question4() {
        Node list1 = new Node(1, new Node(2, new Node(3, null)));
        Node list2 = new Node(1, new Node(2, new Node(3, null)));
        Node list3 = new Node(1, new Node(3, null));

        System.out.println("List1 == List2? " + areIdentical(list1, list2));
        System.out.println("List1 == List3? " + areIdentical(list1, list3));
    }

    // Helper methods
    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private static void printCircular(Node head, int limit) {
        Node curr = head;
        int count = 0;
        while (curr != null && count < limit) {
            System.out.print(curr.data + " ");
            curr = curr.next;
            count++;
        }
        System.out.println("...");
    }

    public static void main(String[] args) {
        System.out.println("=== Question 1 ===");
        question1();

        System.out.println("\n=== Question 2 ===");
        question2();

        System.out.println("\n=== Question 3 ===");
        question3();

        System.out.println("\n=== Question 4 ===");
        question4();
    }
}
