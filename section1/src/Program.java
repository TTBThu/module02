import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.print("Nhập số nguyên không âm (tối đa 3 chữ số): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên.");
                scanner.next(); // xóa dữ liệu nhập sai
            }
            number = scanner.nextInt();

            if (number < 0 || number > 999) {
                System.out.println("Vui lòng nhập lại.");
            }
        } while (number < 0 || number > 999);

        String words = convert(number);
        System.out.println("Kết quả: " + words);
    }

    private static String convert(int number) {
        String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        StringBuilder words = new StringBuilder();

        int hundreds = number / 100;
        int tensDigit = (number % 100) / 10;
        int unitsDigit = number % 10;

        if (hundreds > 0) {
            words.append(units[hundreds]).append(" hundred");
        }

        if (tensDigit > 1) {
            if (hundreds > 0) {
                words.append(" and ");
            }
            words.append(tens[tensDigit]);
            if (unitsDigit > 0) {
                words.append(" ").append(units[unitsDigit]);
            }
        } else if (tensDigit == 1) {
            if (hundreds > 0) {
                words.append(" and ");
            }
            words.append(teens[unitsDigit]);
        } else if (unitsDigit > 0) {
            if (hundreds > 0) {
                words.append(" and ");
            }
            words.append(units[unitsDigit]);
        }

        return words.toString();
    }
}
