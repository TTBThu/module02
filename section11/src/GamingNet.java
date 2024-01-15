import model.Computer;
import service.NetService;
import service.impl.NetServiceImpl;

import java.util.Scanner;

public class GamingNet {
    public static void main(String[] args) {
        NetService netService = new NetServiceImpl();
        netService.add(new Computer("1"));
        netService.add(new Computer("2"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Mở máy");
            System.out.println("2. Tính tiền");
            System.out.println("3. Danh sách máy");
            System.out.println("4. Thêm dịch vụ");
            System.out.println("5. Chuyển máy");
            System.out.println("6. Thoát");
            System.out.print("Chọn tính năng: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    System.out.print("Mở máy số: ");
                    String pcNo = sc.nextLine();
                    netService.onComputer(pcNo);
                    break;
                case 2:
                    System.out.print("Tính tiền cho máy số: ");
                    String pcNoForCost = sc.nextLine();
                    netService.calculateCost(pcNoForCost);
                    break;
                case 3:
                    netService.displayComputerList();
                    break;
                case 4:
                    System.out.print("Thêm dịch vụ cho máy số: ");
                    String pcNoForService = sc.nextLine();
                    System.out.print("Dịch vụ: ");
                    String service = sc.nextLine();
                    netService.addService(pcNoForService, service);
                    break;
                case 5:
                    System.out.print("Máy nguồn (đang bật): ");
                    String sourcePcNo = sc.nextLine();
                    System.out.print("Máy đích (đang tắt): ");
                    String destinationPcNo = sc.nextLine();
                    netService.transferComputer(sourcePcNo, destinationPcNo);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chọn sai chức năng");
            }
        }
    }
}

