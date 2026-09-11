package com.pistophone;

import com.pistophone.exception.BadInputException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class HangmanSession {
    private static final int MAX_MISTAKES = 6;
    private static final char BLANK = '_';

    private final String word;
    private int mistakeCount;
    private final StringBuilder hint;
    private final Set<Character> triedLetters;
    private final Set<Character> wordLetters;

    public HangmanSession(String word) {
        this.word = word;
        hint = new StringBuilder();
        hint.repeat(BLANK, word.length());
        triedLetters = new HashSet<>();
        wordLetters = word.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    }

    public void start() {
        InputReader inputReader = new InputReader();
        while (!isGameOver()) {
            printGameState();
            char guess;
            try {
                guess = inputReader.getLetter("Буква?: ");
            } catch (BadInputException e) {
                IO.println("Ввод должен быть одной буквой Кириллицы");
                continue;
            }
            if (triedLetters.contains(guess)) {
                continue;
            }
            if (wordLetters.contains(guess)) {
                revealLetters(guess);
            } else {
                ++mistakeCount;
            }
            triedLetters.add(guess);
        }
        if (isGameLost()) {
            IO.println("Вы проиграли!");
            IO.println(HangmanArt.HANGMAN_STATES[6]);
        } else if (isGameWon()) {
            IO.println("Вы выиграли!");
        }
    }
    private void revealLetters(char guess) {
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == guess) {
                hint.setCharAt(i, guess);
            }
        }
    }
    private void printGameState() {
        printHint();
        IO.println("ошибки: " + mistakeCount);
        IO.println("вы пробовали: " + triedLetters);
        IO.println(HangmanArt.HANGMAN_STATES[mistakeCount]);
    }
    private void printHint() {
        for (int i = 0; i < hint.length(); ++i) {
            IO.print(hint.charAt(i) + " ");
        }
        IO.println();
    }
    private boolean isGameOver() {
        return hint.indexOf(String.valueOf(BLANK)) == -1 || mistakeCount == MAX_MISTAKES;
    }
    private boolean isGameLost() {
        return mistakeCount == MAX_MISTAKES;
    }
    private boolean isGameWon() {
        return hint.indexOf(String.valueOf(BLANK)) == -1;
    }
}
