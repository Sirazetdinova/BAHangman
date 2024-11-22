package backend.academy.repository;

import backend.academy.constant.Language;
import backend.academy.exception.OpenWordsFileException;
import backend.academy.exception.ReadWordsFileException;
import backend.academy.session.HangmanSession.Category;
import backend.academy.validator.WordRepositoryValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileWordRepository implements WordRepository {
    private final WordRepositoryValidator wordRepositoryValidator;
    private final String filename;
    private List<String> words;

    public FileWordRepository(String directory, String filenameTemplate, Language language, Category category) {
        this.filename =
            String.format("/%s/%s/%s.txt", directory, language.name().toLowerCase(), category.name().toLowerCase());
        this.wordRepositoryValidator = new WordRepositoryValidator(language);
    }

    @Override
    public String toString() {
        return this.filename;

    }

    @Override
    public String get() {
        if (words == null) {
            loadWords();
        }

        int randomIndex = getRandomIndex();
        return words.get(randomIndex);
    }

    private void loadWords() {
        InputStream wordsFileAsStream = getWordsFileAsStream();
        try (var reader = new BufferedReader(new InputStreamReader(wordsFileAsStream))) {
            words = reader.lines()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
            wordRepositoryValidator.validate(words);
        } catch (IOException e) {
            throw new ReadWordsFileException(e);
        }
    }

    private InputStream getWordsFileAsStream() {
        InputStream resource = FileWordRepository.class.getResourceAsStream(filename);
        if (resource == null) {
            throw new OpenWordsFileException();
        }
        return resource;
    }

    private int getRandomIndex() {
        return (int) (Math.random() * words.size());
    }
}
