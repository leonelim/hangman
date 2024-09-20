package hangman;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args) {
        while (true) {
            System.out.print("Начать новую игру? (д/н): ");
            char input = readInput(scanner);
            if (input == 'н') {
                break;
            } else if (input == 'д'){
                String word = pickWord();
                int wordLength = word.length();
                List<Character> hint = new ArrayList<>();
                Set<Character> triedLetters = new HashSet<>();
                int mistakeCount = 0;
                for (int i = 0; i < wordLength; i++) {
                    hint.add('_');
                }
                while (hint.contains('_')) {
                    System.out.println(art[mistakeCount]);
                    System.out.println("Количество ошибок: " + mistakeCount);
                    System.out.println("Вы уже пробовали:" + triedLetters);
                    if (mistakeCount == 6) {
                        System.out.println("Вы проиграли!");
                        System.out.println(word);
                        break;
                    }
                    System.out.println(hint);
                    System.out.print("Буква? ");
                    char guess = readInput(scanner);
                    if (triedLetters.contains(guess)) {
                        System.out.println("Вы уже пробовали эту букву");
                        continue;
                    } else {
                        triedLetters.add(guess);
                    }

                    if (!(word.contains(String.valueOf(guess)))) {
                        mistakeCount += 1;
                    } else {
                    for (int i = 0; i < hint.size(); i++) {
                        if (word.charAt(i) == guess) {
                            hint.set(i, guess);
                        }
                        }
                    }
                    if (!(hint.contains('_'))) {
                        System.out.println("Вы выиграли!");
                        System.out.println("Слово: " + word);
                    }
                System.out.println("*************");
                }
            }
        }
    }
    public static char readInput(Scanner scanner) {
        char input;
        try {
            input = scanner.nextLine().toLowerCase().charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Неверный ввод");
            return 'p';
        }
        if (verifyChar(input)) {
            return input;
        } else {
            System.out.println("Неверный ввод");
            return 'p';
        }
    }
    public static boolean verifyChar(char input) {
        return Character.UnicodeBlock.of(input).equals(Character.UnicodeBlock.CYRILLIC);
    }
    public static String pickWord() {
        List<String> Words = new ArrayList<>();
        try {
            File wordsFile = new File("words.txt");
            Scanner fileScanner = new Scanner(wordsFile);
            while (fileScanner.hasNextLine()) {
                Words.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось считать файл");
            e.printStackTrace();
        }
        Random rand = new Random();
        int n = rand.nextInt(Words.size());
        return Words.get(n);
    }
    static String[] art = {"""
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \s""", """
                                        
                                         ___\
                                        
                                        |/  | \
                                        
                                        |   *\
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \
                                        
                                        |   \s""", """
                                        
                                         ___\
                                        
                                        |/  | \
                                        
                                        |   *\
                                        
                                        |   |\
                                        
                                        |   |\
                                        
                                        |   \
                                        
                                        |   \s""", """
                                        
                                         ___\
                                        
                                        |/  | \
                                        
                                        |   *\
                                        
                                        |  /|\
                                        
                                        |   |\
                                        
                                        |    \
                                        
                                        |   \s""", """
                                    
                                     ___\
                                    
                                    |/  | \
                                    
                                    |   *\
                                    
                                    |  /|\\ \
                                    
                                    |   |\
                                    
                                    |    \
                                    
                                    |   \s""", """
                                        
                                         ___\
                                        
                                        |/  | \
                                        
                                        |   *\
                                        
                                        |  /|\\ \
                                        
                                        |   |\
                                        
                                        |  / \
                                        
                                        |   \s""", """
                                    
                                     ___\
                                    
                                    |/  | \
                                    
                                    |   *\
                                    
                                    |  /|\\ \
                                    
                                    |   |\
                                    
                                    |  / \\\
                                    
                                    |   \s"""};
}