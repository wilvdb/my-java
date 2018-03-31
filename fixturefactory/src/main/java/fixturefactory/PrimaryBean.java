package fixturefactory;

import java.util.List;

/**
 * Created by wil on 31/03/2018.
 */
public class PrimaryBean {

    private int primaryInt;
    private String primaryString;
    private List<SecondaryBean> secondaries;

    public int getPrimaryInt() {
        return primaryInt;
    }

    public void setPrimaryInt(int primaryInt) {
        this.primaryInt = primaryInt;
    }

    public String getPrimaryString() {
        return primaryString;
    }

    public void setPrimaryString(String primaryString) {
        this.primaryString = primaryString;
    }

    public List<SecondaryBean> getSecondaries() {
        return secondaries;
    }

    public void setSecondaries(List<SecondaryBean> secondaries) {
        this.secondaries = secondaries;
    }
}
