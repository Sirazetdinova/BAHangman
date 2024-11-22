package backend.academy.picture;

import java.util.ArrayList;
import java.util.List;

public class HangmanPicture implements PictureProvider {
    private final List<String> pictures = new ArrayList<>();

    public HangmanPicture() {
        pictures.add("""
              _____
              |   |
              |
              |
              |
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |
              |
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |   |
              |
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |  /|
              |
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |  /|\\
              |
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |  /|\\
              |  /
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |  /|\\
              |  / \\
            __|__
            """);
    }

    @Override
    public String get(int attemptNumber) {
        return pictures.get(attemptNumber);
    }
}
