package com.pistophone;

import com.pistophone.exception.BadInputException;

import java.util.HashSet;
import java.util.Set;

public class HangmanGame {
    private static final int MAX_MISTAKES = 6;
    private static final char BLANK = '_';
    private final InputReader INPUT_READER = new InputReader();

    public void start(String word) {
        int mistakeCount = 0;
        StringBuilder hint = new StringBuilder(word.length());
        hint.repeat(BLANK, word.length());
        Set<Character> triedLetters = new HashSet<>();
        Set<Character> wordLetters = new HashSet<>();
        word.chars().mapToObj(c -> (char) c).forEach(wordLetters::add);
        runGameLoop(word, hint, triedLetters, wordLetters, mistakeCount);
    }
    private void runGameLoop(String word, StringBuilder hint, Set<Character> triedLetters,
                             Set<Character> wordLetters, int mistakeCount) {
        while (!isGameOver(mistakeCount, hint)) {
            printGameState(hint, mistakeCount, triedLetters);
            char guess;
            try {
                guess = INPUT_READER.getLetter("Буква?: ");
            } catch (BadInputException e) {
                IO.println("Ввод должен быть одной буквой Кириллицы");
                continue;
            }
            if (triedLetters.contains(guess)) {
                continue;
            }
            if (wordLetters.contains(guess)) {
                revealLetters(hint, word, guess);
            } else {
                ++mistakeCount;
            }
            triedLetters.add(guess);
        }
        if (mistakeCount == MAX_MISTAKES) {
            IO.println("Вы проиграли!");
            IO.println(HangmanArt.HANGMAN_STATES[6]);
        } else {
            IO.println("Вы выиграли!");
        }
    }
    private void revealLetters(StringBuilder hint, String word, char guess) {
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == guess) {
                hint.setCharAt(i, guess);
            }
        }
    }
    private void printGameState(StringBuilder hint, int mistakeCount, Set<Character> triedLetters) {
        printHint(hint);
        IO.println("ошибки: " + mistakeCount);
        IO.println("вы пробовали: " + triedLetters);
        IO.println(HangmanArt.HANGMAN_STATES[mistakeCount]);
    }
    private void printHint(StringBuilder hint) {
        for (int i = 0; i < hint.length(); ++i) {
            IO.print(hint.charAt(i) + " ");
        }
        IO.println();
    }
    private boolean isGameOver(int mistakeCount, StringBuilder hint) {
        return hint.indexOf(String.valueOf(BLANK)) == -1 || mistakeCount == MAX_MISTAKES;
    }
}
