package com.pistophone;

import com.pistophone.exception.BadInputException;

public class Main {
    private static final char PLAY_GAME = 'д';
    private static final char QUIT_GAME = 'н';

    static void main() {
        FileReader fileReader = new FileReader();
        InputReader inputReader = new InputReader();
        boolean hasQuit = false;
        while (!hasQuit) {
            char input;
            try {
                input = inputReader.getLetter("Хотите поиграть в виселицу? (%c/%c): ".formatted(PLAY_GAME, QUIT_GAME));
            } catch (BadInputException e) {
                IO.println("Ввод должен быть одной буквой Кириллицы");
                continue;
            }
            if (input == QUIT_GAME) {
                hasQuit = true;
            } else if (input == PLAY_GAME) {
                var game = new HangmanGame();
                game.start(fileReader.getRandomWord());
            } else {
                IO.println("Хотите поиграть в виселицу? (%c/%c): ".formatted(PLAY_GAME, QUIT_GAME));
            }
        }
    }
}
