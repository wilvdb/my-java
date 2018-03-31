package fixturefactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by wil on 31/03/2018.
 */
public class FixtureFactoryTest {

    @BeforeClass
    public static void setUpClass() {
        Fixture.of(SecondaryBean.class).addTemplate("basic", new Rule() {{
            add("secondaryDouble", 12d);
            add("secondaryDate", new Date());
        }});

        Fixture.of(PrimaryBean.class).addTemplate("basic", new Rule() {{
            add("primaryInt", random(Integer.class));
            add("primaryString", "test_value");
            add("secondaries", has(1).of(SecondaryBean.class, "basic"));
        }});

        FixtureFactoryLoader.loadTemplates("fixturefactory");
    }

    @Test
    public void basic() {
        PrimaryBean bean = Fixture.from(PrimaryBean.class).gimme("basic");
        assertThat(bean.getPrimaryString(), is("test_value"));
        assertThat(bean.getSecondaries(), notNullValue());
        assertThat(bean.getSecondaries().size(), is(1));
        SecondaryBean sb = bean.getSecondaries().get(0);
        assertThat(sb.getSecondaryDouble(), is(12d));
    }

    @Test
    public void rnd() {
        PrimaryBean bean = Fixture.from(PrimaryBean.class).uses(new CustomProcessor()).gimme("rnd");
        assertThat(bean.getSecondaries(), notNullValue());
        assertThat(bean.getSecondaries().size(), is(3));
    }
}
