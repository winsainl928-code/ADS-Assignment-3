package Assignment3.anagramchecker;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter first string:");
        String firstText = inputScanner.nextLine();

        System.out.println("Enter second string:");
        String secondText = inputScanner.nextLine();

        boolean areAnagrams = checkAnagramUsingSorting(firstText, secondText);

        if (areAnagrams) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        inputScanner.close();
    }

    public static boolean checkAnagramUsingSorting(String firstText, String secondText) {
        String normalizedFirstText = firstText.replace(" ", "").toLowerCase();
        String normalizedSecondText = secondText.replace(" ", "").toLowerCase();

        if (normalizedFirstText.length() != normalizedSecondText.length()) {
            return false;
        }

        char[] firstCharacters = normalizedFirstText.toCharArray();
        char[] secondCharacters = normalizedSecondText.toCharArray();

        sortCharactersAscending(firstCharacters);
        sortCharactersAscending(secondCharacters);

        for (int index = 0; index < firstCharacters.length; index++) {
            if (firstCharacters[index] != secondCharacters[index]) {
                return false;
            }
        }

        return true;
    }

    public static void sortCharactersAscending(char[] characters) {
        for (int pass = 0; pass < characters.length - 1; pass++) {
            for (int index = 0; index < characters.length - 1 - pass; index++) {
                if (characters[index] > characters[index + 1]) {
                    char temporaryCharacter = characters[index];
                    characters[index] = characters[index + 1];
                    characters[index + 1] = temporaryCharacter;
                }
            }
        }
    }
}