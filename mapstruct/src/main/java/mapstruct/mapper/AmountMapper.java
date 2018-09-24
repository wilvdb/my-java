package mapstruct.mapper;

import mapstruct.vo.Amount;
import org.mapstruct.Mapper;

import java.text.NumberFormat;

@Mapper
public class AmountMapper {

    public String asString(Amount amount) {
        if (amount == null) {
            return "";
        }
        String value = NumberFormat.getNumberInstance().format(amount.getValue());

        return value + " " + amount.getCurrency().getCurrencyCode();
    }
}
