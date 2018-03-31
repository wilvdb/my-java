package fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wil on 31/03/2018.
 */
public class CustomTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(SecondaryBean.class).addTemplate("rnd", new Rule() {{
            add("secondaryDouble", random(Double.class, range(0, 100)));
            add("secondaryDate", randomDate("01-01-2000", "31-12-2017", new SimpleDateFormat("dd-MM-yyyy")));
        }});

        Fixture.of(PrimaryBean.class).addTemplate("rnd", new Rule() {{
            add("primaryInt", random(Integer.class));
            add("primaryString", random("test_1", "test_2"));
            add("secondaries", has(3).of(SecondaryBean.class, "rnd"));
        }});
    }
}
