package backend.academy.display;

import java.io.PrintStream;

public class InfoDisplay implements Display {
    private final PrintStream out;

    public InfoDisplay() {
        this(System.out);
    }

    public InfoDisplay(PrintStream out) {
        this.out = out;
    }

    @Override
    public void show(String text) {
        out.println(text);
    }
}
