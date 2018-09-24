package mapstruct.mapper;

import mapstruct.dto.PersonDto;
import mapstruct.vo.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonDto personToDto(Person person);

    List<PersonDto> personToDto(List<Person> persons);
}
