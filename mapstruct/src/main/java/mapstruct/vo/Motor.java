package mapstruct.vo;

public class Motor {

    private long cubicCapacity;
    private int cubicCount;
    private Power power;

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

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }
}
