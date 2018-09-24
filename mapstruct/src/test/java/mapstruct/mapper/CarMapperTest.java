package mapstruct.mapper;

import mapstruct.dto.CarDto;
import mapstruct.vo.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;

public class CarMapperTest {

    @Test
    public void null_values() {
        Car car = new Car();
        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(dto);
        Assert.assertNull(dto.getBuy());
    }

    @Test
    public void localdate_to_date() {
        Car car = new Car();
        car.setBuyDate(LocalDate.now());

        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getBuy());
    }

    @Test
    public void motor() {
        Car car = new Car();
        car.setBuyDate(LocalDate.now());
        Motor motor = new Motor();
        car.setMotor(motor);
        motor.setCubicCapacity(1200l);
        motor.setCubicCount(4);
        motor.setPower(Power.GAZOLE);

        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getBuy());
        Assert.assertEquals("GAZOLE", dto.getPower());
    }

    @Test
    public void amount() {
        Car car = new Car();
        car.setBuyDate(LocalDate.now());
        Amount amount = new Amount();
        car.setPrice(amount);
        amount.setCurrency(Currency.getInstance("EUR"));
        amount.setValue(33500L);

        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(dto);
        Assert.assertEquals("33 500 EUR", dto.getPrice());
    }

    @Test
    public void passengers() {
        Car car = new Car();
        car.setBuyDate(LocalDate.now());
        Amount amount = new Amount();
        car.setPrice(amount);
        amount.setCurrency(Currency.getInstance("EUR"));
        amount.setValue(33500L);
        Person person = new Person();
        car.setPassengers(new ArrayList<>());
        car.getPassengers().add(person);
        person.setFirstName("first name");
        person.setLastName("last name");

        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(dto);
        Assert.assertEquals("33 500 EUR", dto.getPrice());
        Assert.assertEquals(1, dto.getPassengers().size());
    }
}
