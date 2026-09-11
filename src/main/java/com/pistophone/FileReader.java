package com.pistophone;

import com.pistophone.exception.FileReadException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FileReader {
    private static final String WORDS_FILE_PATH = "/words.txt";

    public String getRandomWord() {
        List<String> words;
        try {
            URI uri = Objects.requireNonNull(getClass().getResource(WORDS_FILE_PATH)).toURI();
            Path path = Path.of(uri);
            words = Files.readAllLines(path);
        } catch (URISyntaxException e) {
            throw new FileReadException("File not found");
        } catch (IOException e) {
            throw new FileReadException("Failed to read the file at %s".formatted(WORDS_FILE_PATH));
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
