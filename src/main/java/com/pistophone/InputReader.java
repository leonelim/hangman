package com.pistophone;

import com.pistophone.exception.BadInputException;

public class InputReader {
    public char getLetter(String prompt) throws BadInputException {
        String input = IO.readln(prompt);
        if (input == null || input.length() != 1) {
            throw new BadInputException();
        }
        char chr = Character.toLowerCase(input.charAt(0));
        if (Character.UnicodeBlock.of(chr) != Character.UnicodeBlock.CYRILLIC) {
            throw new BadInputException();
        }
        return chr;
    }
}
