package mapstruct.mapper;

import mapstruct.dto.CarDto;
import mapstruct.vo.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AmountMapper.class, PersonMapper.class})
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "buyDate", target = "buy"),
            @Mapping(source = "motor.cubicCapacity", target = "cubicCapacity"),
            @Mapping(source = "motor.cubicCount", target = "cubicCount"),
            @Mapping(source = "motor.power", target = "power")
    })
    CarDto carToCarDto(Car car);
}
