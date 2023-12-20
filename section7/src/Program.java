import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<GiftConfig> giftConfigs = initializeGiftConfigs();
        List<Player> players = initializePlayers();
        List<GiftHistory> giftHistories = new ArrayList<>();

        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Nhận quà");
            System.out.println("2. Xem lịch sử nhận quà");
            System.out.println("3. Thoát");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Nhập mã người chơi (playerCode): ");
                    String playerCode = scanner.next();
                    Player player = findPlayerByCode(players, playerCode);

                    if (player != null) {
                        GiftConfig gift = getGift(giftConfigs);
                        if (gift == null) {
                            System.out.println("Hết quà, nhận quà không giá trị!");
                            gift = getFreeGift(giftConfigs);
                        }

                        if (gift != null) {
                            System.out.println("Đã nhận quà: " + gift.getGiftName());
                            GiftHistory giftHistory = new GiftHistory(gift.getGiftCode(), playerCode);
                            giftHistories.add(giftHistory);
                            updateGiftConfig(giftConfigs, gift);
                        }
                    } else {
                        System.out.println("Người chơi không tồn tại!");
                    }
                    break;

                case 2:
                    System.out.println("Nhập mã người chơi (playerCode): ");
                    String viewPlayerCode = scanner.next();
                    viewGiftHistory(giftHistories, viewPlayerCode);
                    break;

                case 3:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static List<GiftConfig> initializeGiftConfigs() {
        List<GiftConfig> giftConfigs = new ArrayList<>();
        giftConfigs.add(new GiftConfig("G001", "Quà 1", 0.5, false, 10, 2));
        giftConfigs.add(new GiftConfig("G002", "Quà 2", 0.3, false, 5, 1));
        return giftConfigs;
    }

    private static List<Player> initializePlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("P001", "Người chơi 1"));
        players.add(new Player("P002", "Người chơi 2"));
        return players;
    }

    private static Player findPlayerByCode(List<Player> players, String playerCode) {
        for (Player player : players) {
            if (player.getPlayerCode().equals(playerCode)) {
                return player;
            }
        }
        return null;
    }

    private static GiftConfig getGift(List<GiftConfig> giftConfigs) {
        Random random = new Random();
        int index = random.nextInt(giftConfigs.size());
        GiftConfig gift = giftConfigs.get(index);

        if (gift.getLimitTotal() > 0 && gift.getLimitPlayer() > 0) {
            return gift;
        }
        return null;
    }

    private static GiftConfig getFreeGift(List<GiftConfig> giftConfigs) {
        for (GiftConfig gift : giftConfigs) {
            if (gift.isFree()) {
                return gift;
            }
        }
        return null;
    }

    private static void updateGiftConfig(List<GiftConfig> giftConfigs, GiftConfig gift) {
        for (GiftConfig config : giftConfigs) {
            if (config.getGiftCode().equals(gift.getGiftCode())) {
                config.setLimitTotal(config.getLimitTotal() - 1);
                config.setLimitPlayer(config.getLimitPlayer() - 1);
                break;
            }
        }
    }

    private static void viewGiftHistory(List<GiftHistory> giftHistories, String playerCode) {
        System.out.println("Danh sách quà đã nhận:");
        for (GiftHistory history : giftHistories) {
            if (history.getPlayerCode().equals(playerCode)) {
                System.out.println(history.getGiftCode());
            }
        }
    }
}

class GiftConfig {
    private String giftCode;
    private String giftName;
    private double rate;
    private boolean free;
    private int limitTotal;
    private int limitPlayer;

    public GiftConfig(String giftCode, String giftName, double rate, boolean free, int limitTotal, int limitPlayer) {
        this.giftCode = giftCode;
        this.giftName = giftName;
        this.rate = rate;
        this.free = free;
        this.limitTotal = limitTotal;
        this.limitPlayer = limitPlayer;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public String getGiftName() {
        return giftName;
    }

    public double getRate() {
        return rate;
    }

    public boolean isFree() {
        return free;
    }

    public int getLimitTotal() {
        return limitTotal;
    }

    public void setLimitTotal(int limitTotal) {
        this.limitTotal = limitTotal;
    }

    public int getLimitPlayer() {
        return limitPlayer;
    }

    public void setLimitPlayer(int limitPlayer) {
        this.limitPlayer = limitPlayer;
    }
}

class Player {
    private String playerCode;
    private String playerName;

    public Player(String playerCode, String playerName) {
        this.playerCode = playerCode;
        this.playerName = playerName;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public String getPlayerName() {
        return playerName;
    }
}

class GiftHistory {
    private String giftCode;
    private String playerCode;

    public GiftHistory(String giftCode, String playerCode) {
        this.giftCode = giftCode;
        this.playerCode = playerCode;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public String getPlayerCode() {
        return playerCode;
    }
}
