package service.impl;

import model.Computer;
import service.NetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetServiceImpl implements NetService {
    private List<Computer> computers;

    public NetServiceImpl() {
        this.computers = new ArrayList<>();
    }

    @Override
    public void add(Computer computer) {
        computers.add(computer);
    }

    @Override
    public void onComputer(String pcNo) {
        for (Computer computer : computers) {
            if (computer.getPcNo().equals(pcNo) && !computer.isOn()) {
                computer.turnOn();
                System.out.println("Máy " + pcNo + " đã được mở.");
                return;
            }
        }
        System.out.println("Máy không tồn tại hoặc đang có người sử dụng.");
    }

    @Override
    public void calculateCost(String pcNo) {
        double costPerHour = 15000;
        for (Computer computer : computers) {
            if (computer.getPcNo().equals(pcNo) && computer.isOn()) {
                computer.turnOff();
                double cost = costPerHour * (System.currentTimeMillis() - computer.getTotalCost()) / 3600000;
                computer.addCost(cost);
                System.out.println("Máy " + pcNo + " đã được tắt. Tổng tiền là: " + cost + " đồng.");
                return;
            }
        }
        System.out.println("Máy không tồn tại hoặc đang tắt.");
    }

    @Override
    public void displayComputerList() {
        System.out.println("Danh sách máy trong tiệm:");
        for (Computer computer : computers) {
            System.out.println("Máy " + computer.getPcNo() + ": " + (computer.isOn() ? "Đang bật" : "Đang tắt"));
        }
    }

    @Override
    public void addService(String pcNo, String service) {
        // Thêm dịch vụ cho máy
        for (Computer computer : computers) {
            if (computer.getPcNo().equals(pcNo) && computer.isOn()) {
                System.out.println("Máy " + pcNo + " đã được thêm dịch vụ: " + service);
                return;
            }
        }
        System.out.println("Máy không tồn tại hoặc đang tắt.");
    }

    @Override
    public void transferComputer(String sourcePcNo, String destinationPcNo) {
        // Chuyển máy từ máy nguồn sang máy đích
        for (Computer sourceComputer : computers) {
            if (sourceComputer.getPcNo().equals(sourcePcNo) && sourceComputer.isOn()) {
                for (Computer destinationComputer : computers) {
                    if (destinationComputer.getPcNo().equals(destinationPcNo) && !destinationComputer.isOn()) {
                        sourceComputer.turnOff();
                        destinationComputer.turnOn();
                        System.out.println("Máy " + sourcePcNo + " đã được chuyển sang máy " + destinationPcNo);
                        return;
                    }
                }
                System.out.println("Máy đích không tồn tại hoặc đang có người sử dụng.");
                return;
            }
        }
        System.out.println("Máy nguồn không tồn tại hoặc đang tắt.");
    }
}
