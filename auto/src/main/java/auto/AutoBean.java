package auto;

import auto.AutoValue_AutoBean;
import com.google.auto.value.AutoValue;
import com.google.auto.value.extension.memoized.Memoized;

import javax.annotation.Nullable;

/**
 * Created by wil on 01/04/2018.
 */
@AutoValue
public abstract class AutoBean {

    public static Builder builder() {
        return new AutoValue_AutoBean.Builder();
    }

    public abstract String name();

    @Nullable
    public abstract Integer age();

    /**
     * Is not used as a property
     * @return
     */
    @Memoized
    public String title() {
        return "Mr " + name();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setName(String value);
        public abstract Builder setAge(Integer value);
        public abstract AutoBean build();
    }
}
