package com.pistophone;

import com.pistophone.exception.FileReadException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class FileReader {
    private static final String WORDS_FILE_PATH = "/words.txt";

    public String getRandomWord() {
        List<String> words;
        try {
            Path path = Path.of(getClass().getResource(WORDS_FILE_PATH).toURI());
            words = Files.readAllLines(path);
        } catch (URISyntaxException | NullPointerException | IOException e) {
            throw new FileReadException("Failed to read the file at %s".formatted(WORDS_FILE_PATH));
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
