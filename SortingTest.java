import java.io.*;
import java.util.*;

class TextFileLoader {
    public static int[] loadDataFromFile(String filePath) {
        try {
            Scanner inputFile = new Scanner(new File(filePath));
            List<Integer> data = new ArrayList<>();

            while (inputFile.hasNextInt()) {
                int number = inputFile.nextInt();
                data.add(number);
            }

            int[] dataArray = new int[data.size()];
            for (int i = 0; i < data.size(); i++) {
                dataArray[i] = data.get(i);
            }

            inputFile.close();
            return dataArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class SortingFramework {
    public static void insertionSort(int[] arr) {

        int n = arr.length;
        int comparisonCount = 0, exchangeCount = 0; 
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisonCount++;
                
                arr[j + 1] = arr[j];
                j--;
                exchangeCount++;
            }
            arr[j + 1] = key;
        }
        System.out.println("Insertion Sort Comparisons: " + comparisonCount);
        System.out.println("Insertion Sort Exchanges: " + exchangeCount);
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisonCount = 0, exchangeCount = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            exchangeCount++;
        }
        System.out.println("Selection Sort Comparisons: " + comparisonCount);
        System.out.println("Selection Sort Exchanges: " + exchangeCount);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisonCount = 0, exchangeCount = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisonCount++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    exchangeCount++;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.println("Bubble Sort Comparisons: " + comparisonCount);
        System.out.println("Bubble Sort Exchanges: " + exchangeCount);
    }
    
    
    public static void shellSortHalfGap(int[] arr) {
        int n = arr.length;
        int comparisonCount = 0, exchangeCount = 0;

        // Start with a large gap and reduce it
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort for elements at each gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                // Shift elements that are greater than temp
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    comparisonCount++; // Increment comparisons counter
                    exchangeCount++; // Increment exchanges counter
                }

                // Place temp in its correct position
                arr[j] = temp;
                exchangeCount++; // Increment exchanges counter
            }
        }
        
        System.out.println("Shell Sort (Half Gap) Comparisons: " + comparisonCount);
        System.out.println("Shell Sort (Half Gap) Exchanges: " + exchangeCount);
    }
    public static void shellSortKnuth(int[] arr) {
    int n = arr.length;
    int comparisonCount = 0, exchangeCount = 0;

    int gap = 1;
    while (gap < n / 3) {
        gap = gap * 3 + 1;
    }

    while (gap > 0) {
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j;

            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                arr[j] = arr[j - gap];
                comparisonCount++; // Increment comparisons counter
                exchangeCount++; // Increment exchanges counter
            }

            arr[j] = temp;
            exchangeCount++; // Increment exchanges counter
        }

        gap = (gap - 1) / 3;
    }

    System.out.println("Shell Sort (Knuth) Comparisons: " + comparisonCount);
    System.out.println("Shell Sort (Knuth) Exchanges: " + exchangeCount);
}

    
    
    private static int comparison = 0;
    private static int exchange = 0;
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pivotIndex = partition(arr, low, high);

            // Recursively sort the elements before and after the pivot
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
            
             // Print comparisons and exchanges only once after the sorting is complete
            if (low == 0 && high == arr.length - 1) {
                System.out.println("Quick Sort Comparisons: " + comparison);
                System.out.println("Quick Sort Exchanges: " + exchange);
                comparison = 0;
                exchange = 0;
            }
            
        }          
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparison++; // Increment comparisons counter
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
        exchange++; // Increment exchanges counter
        return i + 1;
    }

    public static void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        comparison++;
        if (left < n && arr[left] > arr[largest]) 
            largest = left;

        // If right child is larger than largest so far
        comparison++;
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            exchange++;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void heapSort(int arr[]) {
        int n = arr.length;
        comparison = 0;
        exchange = 0;

        // Build max heap (bottom-up)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap current root with end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            exchange++;

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
        System.out.println("Heap Sort Comparisons: " + comparison);
        System.out.println("Heap Sort Exchanges: " + exchange);
    }




    

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println(); 
    }   
}

class RandomDataGenerator {
    public static void generateUniqueRandomData(String filePath, int dataSize, int minValue, int maxValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            List<Integer> uniqueData = new ArrayList<>();
            for (int i = minValue; i <= maxValue; i++) {
                uniqueData.add(i);
            }
            
            Collections.shuffle(uniqueData);

            for (int i = 0; i < dataSize; i++) {
                writer.write(Integer.toString(uniqueData.get(i)));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void generateIncreasingOrderData(String filePath, int dataSize, int maxValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= dataSize; i++) {
                writer.write(Integer.toString(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateDecreasingOrderData(String filePath, int dataSize, int maxValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = dataSize; i >= 1; i--) {
                writer.write(Integer.toString(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class SortingTest {
    public static void main(String[] args) {
        String filePath_Ordered = "ordered_data_prototype.txt"; 
        
        String filePath_Reversed = "reverse_data_prototype.txt";
        
        String filePath_Random = "unique_random_data_txt";
        
        String filePath_Ordered1 = "ordered_data.txt"; 
        
        String filePath_Reversed1 = "reverse_data.txt";
        
        String filePath_Random1 = "unique_random.txt";
        
        //Creating files with data
        RandomDataGenerator.generateIncreasingOrderData(filePath_Ordered, 10, 10);
        
        RandomDataGenerator.generateDecreasingOrderData(filePath_Reversed, 10, 10);

        RandomDataGenerator.generateUniqueRandomData(filePath_Random, 10, 1, 10);
        
        RandomDataGenerator.generateIncreasingOrderData(filePath_Ordered1, 2000, 2000);
        
        RandomDataGenerator.generateDecreasingOrderData(filePath_Reversed1, 2000, 2000);
        
        RandomDataGenerator.generateUniqueRandomData(filePath_Random1, 2000, 1, 2000);
        
        // Loading data from the file into arrays
        int[] ordered_data = TextFileLoader.loadDataFromFile(filePath_Ordered);
        
        int[] reversed_data = TextFileLoader.loadDataFromFile(filePath_Reversed);
        
        int[] random_data = TextFileLoader.loadDataFromFile(filePath_Random);
        
        int[] ordered_data1 = TextFileLoader.loadDataFromFile(filePath_Ordered1);
        
        int[] reversed_data1 = TextFileLoader.loadDataFromFile(filePath_Reversed1);
        
        int[] random_data1 = TextFileLoader.loadDataFromFile(filePath_Random1);

        //Cloning arrays
        int[] orderedDataCopy1P = ordered_data.clone();
        int[] orderedDataCopy2P = ordered_data.clone();
        int[] orderedDataCopy3P = ordered_data.clone();
            
        int[] reversedDataCopy1P = reversed_data.clone();
        int[] reversedDataCopy2P = reversed_data.clone();
        int[] reversedDataCopy3P = reversed_data.clone();
            
        int[] randomDataCopy1P = random_data.clone();
        int[] randomDataCopy2P = random_data.clone();
        int[] randomDataCopy3P = random_data.clone();
            
        int[] orderedDataCopy1 = ordered_data1.clone();
        int[] orderedDataCopy2 = ordered_data1.clone();
        int[] orderedDataCopy3 = ordered_data1.clone();
            
        int[] reversedDataCopy1 = reversed_data1.clone();
        int[] reversedDataCopy2 = reversed_data1.clone();
        int[] reversedDataCopy3 = reversed_data1.clone();
            
        int[] randomDataCopy1 = random_data1.clone();
        int[] randomDataCopy2 = random_data1.clone();
        int[] randomDataCopy3 = random_data1.clone();
        
        //Cloned Data for Homework 2

        int[] orderedDataCopy1P_HW2 = ordered_data.clone();
        int[] orderedDataCopy1_5P_HW2 = ordered_data.clone();
        int[] orderedDataCopy2P_HW2 = ordered_data.clone();
            
        int[] reversedDataCopy1P_HW2 = reversed_data.clone();
        int[] reversedDataCopy1_5P_HW2 = reversed_data.clone();
        int[] reversedDataCopy2P_HW2 = reversed_data.clone();

        int[] randomDataCopy1P_HW2 = random_data.clone();
        int[] randomDataCopy1_5P_HW2 = random_data.clone();
        int[] randomDataCopy2P_HW2 = random_data.clone();
        
        int[] orderedDataCopy1_HW2 = ordered_data1.clone();
        int[] orderedDataCopy1_5_HW2 = ordered_data1.clone();
        int[] orderedDataCopy2_HW2 = ordered_data1.clone();
            
        int[] reversedDataCopy1_HW2 = reversed_data1.clone();
        int[] reversedDataCopy1_5_HW2 = reversed_data1.clone();
        int[] reversedDataCopy2_HW2 = reversed_data1.clone();
        
        int[] randomDataCopy1_HW2 = random_data1.clone();
        int[] randomDataCopy1_5_HW2 = random_data1.clone();
        int[] randomDataCopy2_HW2 = random_data1.clone();
        
        //Cloned Data for Homework 4
        
        int[] orderedDataCopy1P_HW4 = ordered_data.clone();
        int[] reversedDataCopy1P_HW4 = reversed_data.clone();
        int[] randomDataCopy1P_HW4 = random_data.clone();
        
        int[] orderedDataCopy1_HW4 = ordered_data1.clone();
        int[] reversedDataCopy1_HW4 = reversed_data1.clone();
        int[] randomDataCopy1_HW4 = random_data1.clone();
        

        // Using SortingFramework to sort prototype data copies and print them
        
        //Homework 4 Prototype Results
        
        System.out.println("\nHomework 4 Results of Heap Sort.");
        
        System.out.println("\nOrdered Prototype Data:");
        
        SortingFramework.printArray(orderedDataCopy1P_HW4);
        SortingFramework.heapSort(orderedDataCopy1P_HW4);
        SortingFramework.printArray(orderedDataCopy1P_HW4);
        
        System.out.println("\nReversed Prototype Data:");
        
        SortingFramework.printArray(reversedDataCopy1P_HW4);
        SortingFramework.heapSort(reversedDataCopy1P_HW4);
        SortingFramework.printArray(reversedDataCopy1P_HW4);
        
        System.out.println("\nRandom Prototype Data:");
        
        SortingFramework.printArray(randomDataCopy1P_HW4);
        SortingFramework.heapSort(randomDataCopy1P_HW4);
        SortingFramework.printArray(randomDataCopy1P_HW4);
        
        //Homework 4 1-2000 Results
        
        System.out.println("\nOrdered Data:");        
        SortingFramework.heapSort(orderedDataCopy1_HW4);
        System.out.println("\nReversed Data:"); 
        SortingFramework.heapSort(reversedDataCopy1_HW4);
        System.out.println("\nRandom Data:"); 
        SortingFramework.heapSort(randomDataCopy1_HW4);
        
        //Homework 2 Results
        
        System.out.println("\nHomework 2 Results of Shell Sort and Quick Sort.");
        System.out.println();
        
        System.out.println("\nOrdered Prototype Data:");
        
        SortingFramework.shellSortHalfGap(orderedDataCopy1P_HW2);
        SortingFramework.printArray(orderedDataCopy1P_HW2);
        System.out.println();
        SortingFramework.shellSortKnuth(orderedDataCopy1_5P_HW2);
        SortingFramework.printArray(orderedDataCopy1_5P_HW2);
        System.out.println();
        
        SortingFramework.quickSort(orderedDataCopy2P_HW2, 0, 9);
        SortingFramework.printArray(orderedDataCopy2P_HW2);
        System.out.println();
        
        System.out.println("\nReversed Prototype Data:");
        
        SortingFramework.shellSortHalfGap(reversedDataCopy1P_HW2);
        SortingFramework.printArray(reversedDataCopy1P_HW2);
        System.out.println();
        SortingFramework.shellSortKnuth(reversedDataCopy1_5P_HW2);
        SortingFramework.printArray(reversedDataCopy1_5P_HW2);
        System.out.println();
        SortingFramework.quickSort(reversedDataCopy2P_HW2, 0, 9);
        SortingFramework.printArray(reversedDataCopy2P_HW2);
        System.out.println();
        
        System.out.println("\nRandom Prototype Data:");
        SortingFramework.shellSortHalfGap(randomDataCopy1P_HW2);
        SortingFramework.printArray(randomDataCopy1P_HW2);
        System.out.println();        
        SortingFramework.shellSortKnuth(randomDataCopy1_5P_HW2);
        SortingFramework.printArray(randomDataCopy1_5P_HW2);
        System.out.println();
        SortingFramework.quickSort(randomDataCopy2P_HW2, 0, 9);
        SortingFramework.printArray(randomDataCopy2P_HW2);
        System.out.println();
        
        //Data Sets 1 - 2000 Homework 2
        
        System.out.println("\nOrdered Data:");
        
        SortingFramework.shellSortHalfGap(orderedDataCopy1_HW2);
        System.out.println();
        SortingFramework.shellSortKnuth(orderedDataCopy1_5_HW2);
        System.out.println();
        SortingFramework.quickSort(orderedDataCopy2_HW2, 999, 1999);
        System.out.println();
        
        System.out.println("\nReversed Data:");
        
        
        SortingFramework.shellSortHalfGap(reversedDataCopy1_HW2);
        System.out.println();
        SortingFramework.shellSortKnuth(reversedDataCopy1_5_HW2);
        System.out.println();
        SortingFramework.quickSort(reversedDataCopy2_HW2, 0, 1999);
        System.out.println();
        
        System.out.println("\nRandom Data:");
        
        SortingFramework.shellSortHalfGap(randomDataCopy1_HW2);
        System.out.println();             
        SortingFramework.shellSortKnuth(randomDataCopy1_5_HW2);
        System.out.println();       
        SortingFramework.quickSort(randomDataCopy2_HW2, 0, 1999);
        System.out.println();
        
    }
}
