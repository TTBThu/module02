package service;

import model.Computer;

public interface NetService {
    void add(Computer computer);
    void onComputer(String pcNo);
    void calculateCost(String pcNo);
    void displayComputerList();
    void addService(String pcNo, String service);
    void transferComputer(String sourcePcNo, String destinationPcNo);
}
