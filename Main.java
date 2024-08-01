public class Main {
    static int comparisonCount = 0;
    static int swapCount = 0;

    public static void main(String[] args) {
        // Initialize an array from 1 to 10 in reverse order
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = 10 - i;
        }

        // Print the original array
        System.out.println("Original Reverse Array:");
        printArray(arr);

        // Call heapSort method to sort the array
        heapSortWithCounters(arr);

        // Print the sorted array
        System.out.println("\nSorted Array:");
        printArray(arr);

        // Print the comparison and swap counts
        System.out.println("\nNumber of Comparisons: " + comparisonCount);
        System.out.println("Number of Swaps: " + swapCount);
        
        int[] arr1_5 = new int[10];
        for (int i = 0; i < 10; i++) {
            arr1_5[i] = i + 1;
        }
        
        System.out.println("Original Sorted Array:");
        printArray(arr1_5);
        
        comparisonCount = 0;
        swapCount = 0;
        
        heapSortWithCounters(arr1_5);
        
        System.out.println("\nSorted Array:");
        printArray(arr1_5);
        System.out.println("\nNumber of Comparisons: " + comparisonCount);
        System.out.println("Number of Swaps: " + swapCount);
    
        int[] arr2 = new int[10];
        for (int i = 0; i < 10; i++) {
            arr2[i] = 10 - i;
        }
        
        
        System.out.println("\nOriginal Array:");
        printArray(arr2);
        comparisonCount = 0;
        swapCount = 0;
        
        quickSort(arr2, 0, 9);
    }

    // Helper method to print an array
    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void heapSortWithCounters(int arr[]) {
        int n = arr.length;
        comparisonCount = 0;
        swapCount = 0;

        // Build max heap (bottom-up)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyWithCounters(arr, n, i);
        }

        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap current root with end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swapCount++;
            System.out.println("\nSwaps: " + swapCount);
            System.out.println("element " +  " is extracted.");
            printArray(arr);

            // Heapify the reduced heap
            heapifyWithCounters(arr, i, 0);
        }
    }

    public static void heapifyWithCounters(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Increment the comparison count
        comparisonCount++;
        System.out.println("\nComparisons: " + comparisonCount);
        System.out.println("Comparing left child (" + left + ") and root (" + i + ")");


        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            System.out.println("Left child (" + left  + ") = " + arr[left] + " is bigger than root node (" + i + ") = " + arr[largest]);
            largest = left;
        }

        // Increment the comparison count
        comparisonCount++;
        System.out.println("\nComparisons: " + comparisonCount);
        System.out.println("Comparing right child (" + right + ") and root node (" + i + ")" );

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            System.out.println("Right child (" + right + ") = " + arr[right] + " is bigger than root node (" + i + ") = " + arr[largest]);
            largest = right;

        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            System.out.println("\nLargest " + largest + " is not root so it will be swapped with " + i + " root node");

            swapCount++;
            System.out.println("Swaps: " + swapCount);
            printArray(arr);

            // Recursively heapify the affected sub-tree
            heapifyWithCounters(arr, n, largest);
        }
    }
    
    public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pivotIndex = partition(arr, low, high);

        // Recursively sort the elements before and after the pivot
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    // If the entire array is sorted, print comparisons and exchanges
    if (low == 0 && high == arr.length - 1) {
        System.out.println("Quick Sort Comparisons: " + comparisonCount);
        System.out.println("Quick Sort Swaps: " + swapCount);
        comparisonCount = 0;
        swapCount = 0;
    }
}

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisonCount++; // Increment comparisons counter
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] (or the pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swapCount++; // Increment exchanges counter
        printArray(arr);
        return i + 1;
    }
}