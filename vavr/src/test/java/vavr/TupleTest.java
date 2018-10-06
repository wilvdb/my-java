package vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TupleTest {

    @Test
    public void tuple() {
        Tuple2<String, Integer> tuple = Tuple.of("Hello", 1);
        assertEquals("Hello", tuple._1());
        assertEquals(new Integer(1), tuple._2());
        Tuple2<String, Integer> updatedTuple = tuple.update1("Hello hello");
        assertThat(updatedTuple, not(sameInstance(tuple)));
    }

}
