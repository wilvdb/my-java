package truth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.google.common.truth.Correspondence;
import org.junit.Test;

import javax.annotation.Nullable;

import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by wil on 02/04/2018.
 */
public class FuzzyTest {

    private static final Correspondence<String, Integer> STRING_PARSES_TO_INTEGER_CORRESPONDENCE =
            new Correspondence<String, Integer>() {
                @Override
                public boolean compare(@Nullable String actual, @Nullable Integer expected) {
                    if (actual == null) {
                        return expected == null;
                    }
                    try {
                        return Integer.decode(actual).equals(expected);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                @Override
                public String toString() {
                    return "parse to";
                }
            };

    @Test
    public void iterable() {
        Iterable<String> actual = ImmutableList.of("+64", "+128", "+256", "0x80");
        assertThat(actual)
                .comparingElementsUsing(STRING_PARSES_TO_INTEGER_CORRESPONDENCE)
                .containsExactly(64, 128, 256, 128)
                .inOrder();
    }

    @Test
    public void map() {
        Map<String, String> actual = ImmutableMap.of("abc", "123", "def", "456");
        assertThat(actual)
                .comparingValuesUsing(STRING_PARSES_TO_INTEGER_CORRESPONDENCE)
                .containsExactly("def", 456, "abc", 123);
    }

    @Test
    public void multimap() {
        Multimap<String, String> actual =
                ImmutableListMultimap.of("abc", "123", "def", "456", "def", "789");
        assertThat(actual)
                .comparingValuesUsing(STRING_PARSES_TO_INTEGER_CORRESPONDENCE)
                .containsEntry("def", 789);
    }
}
