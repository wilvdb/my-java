package mapstruct.dto;

import java.util.Date;
import java.util.List;

public class CarDto {

    private Date buy;
    private String power;
    private long cubicCapacity;
    private int cubicCount;
    private String price;
    private List<PersonDto> passengers;

    public Date getBuy() {
        return buy;
    }

    public void setBuy(Date buy) {
        this.buy = buy;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public long getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(long cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public int getCubicCount() {
        return cubicCount;
    }

    public void setCubicCount(int cubicCount) {
        this.cubicCount = cubicCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<PersonDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PersonDto> passengers) {
        this.passengers = passengers;
    }
}
