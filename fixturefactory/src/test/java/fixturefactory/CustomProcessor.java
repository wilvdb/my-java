package fixturefactory;

import br.com.six2six.fixturefactory.processor.Processor;

/**
 * Created by wil on 31/03/2018.
 */
public class CustomProcessor implements Processor {

    @Override
    public void execute(Object o) {
        System.out.println("Process type " + o.getClass().getSimpleName());
    }
}
