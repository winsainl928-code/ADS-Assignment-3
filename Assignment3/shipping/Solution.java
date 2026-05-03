package Assignment3.shipping;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter number of packages:");
        int packageCount = inputScanner.nextInt();

        int[] packageWeights = readIntegerArray(inputScanner, packageCount);

        System.out.println("Enter number of days:");
        int availableDays = inputScanner.nextInt();

        int minimumCapacity = findMinimumShippingCapacity(packageWeights, availableDays);
        System.out.println(minimumCapacity);

        inputScanner.close();
    }

    public static int findMinimumShippingCapacity(int[] packageWeights, int availableDays) {
        int minimumCapacity = findMaximumValue(packageWeights);
        int maximumCapacity = findTotalWeight(packageWeights);
        int answerCapacity = maximumCapacity;

        while (minimumCapacity <= maximumCapacity) {
            int middleCapacity = minimumCapacity + (maximumCapacity - minimumCapacity) / 2;

            if (isShippingPossible(packageWeights, availableDays, middleCapacity)) {
                answerCapacity = middleCapacity;
                maximumCapacity = middleCapacity - 1;
            } else {
                minimumCapacity = middleCapacity + 1;
            }
        }

        return answerCapacity;
    }

    public static boolean isShippingPossible(int[] packageWeights, int availableDays, int truckCapacity) {
        int usedDays = 1;
        int currentLoad = 0;

        for (int index = 0; index < packageWeights.length; index++) {
            if (currentLoad + packageWeights[index] > truckCapacity) {
                usedDays++;
                currentLoad = packageWeights[index];
            } else {
                currentLoad += packageWeights[index];
            }
        }

        return usedDays <= availableDays;
    }

    public static int[] readIntegerArray(Scanner inputScanner, int arraySize) {
        int[] numbers = new int[arraySize];

        System.out.println("Enter " + arraySize + " integers:");
        for (int index = 0; index < arraySize; index++) {
            numbers[index] = inputScanner.nextInt();
        }

        return numbers;
    }

    public static int findMaximumValue(int[] numbers) {
        int maximumValue = numbers[0];

        for (int index = 1; index < numbers.length; index++) {
            if (numbers[index] > maximumValue) {
                maximumValue = numbers[index];
            }
        }

        return maximumValue;
    }

    public static int findTotalWeight(int[] numbers) {
        int totalWeight = 0;

        for (int index = 0; index < numbers.length; index++) {
            totalWeight += numbers[index];
        }

        return totalWeight;
    }
}