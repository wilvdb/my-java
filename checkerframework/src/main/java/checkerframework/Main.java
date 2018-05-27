package checkerframework;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;

/**
 * Created by wil on 10/04/2018.
 */
public class Main {

    @Regex(1)
    private String regex = "\\d*";


    public void main(String[] args) {
        Main main = new Main();
        main.test(-2);
    }

    /**
     * Will not compile because of nullable check
     * @param var
     */
    public void test(@NonNegative @Nullable Integer var) {
        System.out.println(var.toString());
    }
}
