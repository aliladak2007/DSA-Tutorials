import java.util.Stack;

public class Tutorial_1 {
    public static int[] mergeArrayStack (int[] arr, Stack<Integer> stk) {
        if (stk.isEmpty()) return arr;
        int[] stkArray = new int[stk.size()];
        for (int i = stk.size() - 1; i >= 0; i--) {
            stkArray[i] = stk.pop();
        }

        mergeSort(stkArray);
        return mergeArrays(arr, stkArray);
    }

    public static void mergeSort(int[] a) {
        if (a.length < 2) return;
        int mid = a.length / 2;
        int[] left  = new int[mid];
        int[] right = new int[a.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = a[mid + i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left, right, a);
    }

    public static void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            }
            else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static int[] mergeArrays (int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            }
            else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        // arr is already sorted
        int[] arr = {1, 4, 6, 10};

        // stack is unsorted
        Stack<Integer> stk = new Stack<>();
        stk.push(5);
        stk.push(2);
        stk.push(12);
        stk.push(3);
        stk.push(8);

        // Merge them
        int[] result = mergeArrayStack(arr, stk);

        // Print result
        System.out.print("Merged + Sorted: ");
        for (int v : result) {
            System.out.print(v + " ");
        }
    }
}