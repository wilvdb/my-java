package vavr;

import io.vavr.control.Either;
import org.junit.Test;

public class EitherTest {

    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }

    @Test
    public void givenInput_whenComputed() {
        Either<String, Integer> either = computeWithEither(90);
        int right = either.right().getOrElse(0);
        String left = either.left().getOrElse(this::hello);
    }

    String hello() {
        return Integer.toString(3);
    }
}
