package backend.academy.picture;

import java.util.ArrayList;
import java.util.List;

public class EasyHangmanPicture implements PictureProvider {
    private final List<String> pictures = new ArrayList<>();

    public EasyHangmanPicture() {
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
        pictures.add("""
              _____
              |   |
              |   O
              |  /|\\
              | _/ \\
            __|__
            """);
        pictures.add("""
              _____
              |   |
              |   O
              |  /|\\
              | _/ \\_
            __|__
            """);
    }

    @Override
    public String get(int attemptNumber) {
        return pictures.get(attemptNumber);
    }
}
