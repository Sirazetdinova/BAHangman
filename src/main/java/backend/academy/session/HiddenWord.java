package backend.academy.session;

import backend.academy.exception.NoSuchLetterException;

public class HiddenWord {
    private static final String PLACEHOLDER = "_";

    private final String text;
    private final StringBuilder mask;

    public HiddenWord(String text) {
        this.text = text.toUpperCase();
        this.mask = createWordMask();
    }

    private StringBuilder createWordMask() {
        String maskedWord = PLACEHOLDER.repeat(text.length());
        return new StringBuilder(maskedWord);
    }

    public String revealLetter(String letter) {
        String upperCaseLetter = letter.toUpperCase();
        if (!text.contains(upperCaseLetter)) {
            throw new NoSuchLetterException(upperCaseLetter);
        }

        if (isGuessed() || isLetterAlreadyRevealed(upperCaseLetter)) {
            return mask.toString();
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = upperCaseLetter.charAt(0);
            if (text.charAt(i) == ch) {
                mask.setCharAt(i, ch);
            }
        }

        return mask.toString();
    }

    private boolean isLetterAlreadyRevealed(String letter) {
        return mask.toString().contains(letter);
    }

    public boolean isGuessed() {
        return !mask.toString().contains(PLACEHOLDER);
    }

    public String reveal() {
        return text;
    }

    public String getMask() {
        return mask.toString();
    }
}
