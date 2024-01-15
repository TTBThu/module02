package model;

public class Computer {
    private String pcNo;
    private boolean isOn;
    private double totalCost;

    public Computer(String pcNo) {
        this.pcNo = pcNo;
        this.isOn = false;
        this.totalCost = 0.0;
    }

    public String getPcNo() {
        return pcNo;
    }

    public boolean isOn() {
        return isOn;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public void addCost(double cost) {
        this.totalCost += cost;
    }
}
