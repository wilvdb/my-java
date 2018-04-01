package errorprone;

import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wil on 01/04/2018.
 */
public class ShortSet {

    public static void main (String[] args) {
        Set<Short> s = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            s.add(i);
            // error prone: incompatible type (int - short)
            s.remove(i - 1);
        }
        System.out.println(s.size());

        // error prone: returned value not checked
        returnAnything();

        // error prone: format conversion
        String.format("%f", "abcd");

        // error prone: format conversion
        formatStr("%d test", 2);
    }

    @CheckReturnValue
    public static int returnAnything() {
        return 2;
    }

    /**
     * error prone: not protected
     */
    @ForOverride
    public void testOverride() {
        System.out.println("ForOverride test");
    }

    @FormatMethod
    static String formatStr(@FormatString String fmt, int value) {
        return String.format(fmt, value);
    }

}
