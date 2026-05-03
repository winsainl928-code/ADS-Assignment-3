package Assignment3.kthsmallest;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter array size:");
        int arraySize = inputScanner.nextInt();

        int[] numbers = readIntegerArray(inputScanner, arraySize);

        System.out.println("Enter k:");
        int position = inputScanner.nextInt();

        int kthSmallestValue = findKthSmallestElement(numbers, position);
        System.out.println(kthSmallestValue);

        inputScanner.close();
    }

    public static int findKthSmallestElement(int[] numbers, int position) {
        if (position < 1 || position > numbers.length) {
            throw new IllegalArgumentException("k is out of range.");
        }

        int[] copiedNumbers = copyArray(numbers);
        sortNumbersAscending(copiedNumbers, 0, copiedNumbers.length - 1);

        return copiedNumbers[position - 1];
    }

    public static int[] readIntegerArray(Scanner inputScanner, int arraySize) {
        int[] numbers = new int[arraySize];

        System.out.println("Enter " + arraySize + " integers:");
        for (int index = 0; index < arraySize; index++) {
            numbers[index] = inputScanner.nextInt();
        }

        return numbers;
    }

    public static int[] copyArray(int[] originalNumbers) {
        int[] copiedNumbers = new int[originalNumbers.length];

        for (int index = 0; index < originalNumbers.length; index++) {
            copiedNumbers[index] = originalNumbers[index];
        }

        return copiedNumbers;
    }

    public static void sortNumbersAscending(int[] numbers, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

        sortNumbersAscending(numbers, leftIndex, middleIndex);
        sortNumbersAscending(numbers, middleIndex + 1, rightIndex);
        mergeSortedParts(numbers, leftIndex, middleIndex, rightIndex);
    }

    public static void mergeSortedParts(int[] numbers, int leftIndex, int middleIndex, int rightIndex) {
        int leftPartSize = middleIndex - leftIndex + 1;
        int rightPartSize = rightIndex - middleIndex;

        int[] leftPart = new int[leftPartSize];
        int[] rightPart = new int[rightPartSize];

        for (int index = 0; index < leftPartSize; index++) {
            leftPart[index] = numbers[leftIndex + index];
        }

        for (int index = 0; index < rightPartSize; index++) {
            rightPart[index] = numbers[middleIndex + 1 + index];
        }

        int leftPointer = 0;
        int rightPointer = 0;
        int mergedPointer = leftIndex;

        while (leftPointer < leftPartSize && rightPointer < rightPartSize) {
            if (leftPart[leftPointer] <= rightPart[rightPointer]) {
                numbers[mergedPointer] = leftPart[leftPointer];
                leftPointer++;
            } else {
                numbers[mergedPointer] = rightPart[rightPointer];
                rightPointer++;
            }
            mergedPointer++;
        }

        while (leftPointer < leftPartSize) {
            numbers[mergedPointer] = leftPart[leftPointer];
            leftPointer++;
            mergedPointer++;
        }

        while (rightPointer < rightPartSize) {
            numbers[mergedPointer] = rightPart[rightPointer];
            rightPointer++;
            mergedPointer++;
        }
    }
}