import auto.AutoBean;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wil on 01/04/2018.
 */
public class AutoBeanTest {

    @Test
    public void build() {
        AutoBean bean1 = AutoBean.builder().setName("test").build();
        Assert.assertThat(bean1.name(), CoreMatchers.is("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void incomplete_build() {
        AutoBean.builder().setAge(4).build();
    }

    @Test
    public void equals() {
        AutoBean bean1 = AutoBean.builder().setName("test").build();
        AutoBean bean2 = AutoBean.builder().setName("test").build();
        Assert.assertTrue(bean1.equals(bean2));
    }
}
