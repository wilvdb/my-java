package vavr;

import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static org.junit.Assert.assertEquals;

public class PatternMatchingTest {

    @Test
    public void whenMatchworks_thenCorrect() {
        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertEquals("two", output);
    }

    @Test
    public void whenMatchArgs_thenRun() {
        String arg = "-h";
        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );
    }

    void displayHelp() {
        System.out.println("Help");
    }

    void displayVersion() {
        System.out.println("Version");
    }
}