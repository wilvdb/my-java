package truth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Created by wil on 02/04/2018.
 */
public class TruthTest {

    enum Color {
        BLUE, RED, YELLOW, GREEN
    }

    @Test
    public void withMessage() {
        String string = "awesome";
        assertThat(string).startsWith("awe");
        assertWithMessage("Without me, it's just aweso").that(string).contains("me");
    }

    @Test
    public void iterable() {
        Iterable<Color> googleColors = ImmutableList.of(Color.BLUE, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED);
        assertThat(googleColors)
                .containsExactly(Color.BLUE, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED)
                .inOrder();
    }

    
}
