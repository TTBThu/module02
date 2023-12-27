import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagement {
    private static ArrayList<Class> classes = new ArrayList<>();
    private static ArrayList<Students> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("**********************QUẢN LÝ HỌC VIỆN*******************");
            System.out.println("1. Quản lý lớp");
            System.out.println("2. Quản lý sinh viên");
            System.out.println("3. Thoát");
            System.out.println("******************************************************");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageClasses(scanner);
                    break;
                case 2:
                    manageStudents(scanner);
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void manageClasses(Scanner scanner) {
        int choice;

        do {
            System.out.println("**********************QUẢN LÝ LỚP HỌC********************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp học đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addClass(scanner);
                    break;
                case 2:
                    updateClass(scanner);
                    break;
                case 3:
                    displayClasses();
                    break;
                case 4:
                    displayActiveClasses();
                    break;
                case 5:
                    searchClassByName(scanner);
                    break;
                case 6:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (choice != 6);
    }

    private static void manageStudents(Scanner scanner) {
        int choice;

        do {
            System.out.println("***********************QUẢN LÝ SINH VIÊN******************");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viên");
            System.out.println("3. Hiển thị thông tin sinh viên");
            System.out.println("4. Tính điểm trung bình");
            System.out.println("5. Xếp loại sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình và yếu");
            System.out.println("9. Thống kê các sinh viên Pass qua các môn học");
            System.out.println("10. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    calculateAverage(scanner);
                    break;
                case 5:
                    displayStudentClassification(scanner);
                    break;
                case 6:
                    sortStudentsByAverage();
                    break;
                case 7:
                    searchStudentByName(scanner);
                    break;
                case 8:
                    displayStudentStatistics();
                    break;
                case 9:
                    displayPassingStudents();
                    break;
                case 10:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        } while (choice != 10);
    }

    private static void addClass(Scanner scanner) {
        System.out.print("Nhập tên lớp học: ");
        String className = scanner.next();
        System.out.print("Lớp học đang hoạt động? (true/false): ");
        boolean classStatus = scanner.nextBoolean();

        Class newClass = new Class(className, classStatus);
        classes.add(newClass);

        System.out.println("Thêm lớp học thành công.");
    }

    private static void updateClass(Scanner scanner) {
        System.out.print("Nhập tên lớp học cần cập nhật thông tin: ");
        String className = scanner.next();
        Class foundClass = findClassByName(className);

        if (foundClass != null) {
            System.out.print("Nhập tên mới cho lớp học: ");
            String newClassName = scanner.next();
            System.out.print("Lớp học đang hoạt động? (true/false): ");
            boolean newClassStatus = scanner.nextBoolean();

            foundClass.className = newClassName;
            foundClass.classStatus = newClassStatus;

            System.out.println("Cập nhật thông tin lớp học thành công.");
        } else {
            System.out.println("Không tìm thấy lớp học.");
        }
    }

    private static void displayClasses() {
        System.out.println("Danh sách các lớp học:");
        for (Class aClass : classes) {
            System.out.println("Tên lớp học: " + aClass.className);
            System.out.println("Trạng thái: " + (aClass.classStatus ? "Đang hoạt động" : "Ngừng hoạt động"));
            System.out.println("------------------------------");
        }
    }

    private static void displayActiveClasses() {
        System.out.println("Các lớp học đang hoạt động:");
        for (Class aClass : classes) {
            if (aClass.classStatus) {
                System.out.println("Tên lớp học: " + aClass.className);
                System.out.println("------------------------------");
            }
        }
    }

    private static void searchClassByName(Scanner scanner) {
        System.out.print("Nhập tên lớp học cần tìm kiếm: ");
        String className = scanner.next();
        Class foundClass = findClassByName(className);

        if (foundClass != null) {
            System.out.println("Thông tin lớp học:");
            System.out.println("Tên lớp học: " + foundClass.className);
            System.out.println("Trạng thái: " + (foundClass.classStatus ? "Đang hoạt động" : "Ngừng hoạt động"));
        } else {
            System.out.println("Không tìm thấy lớp học.");
        }
    }

    private static Class findClassByName(String className) {
        for (Class aClass : classes) {
            if (aClass.className.equals(className)) {
                return aClass;
            }
        }
        return null;
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Nhập tên sinh viên: ");
        String studentName = scanner.next();
        System.out.print("Nhập số môn học: ");
        int numSubjects = scanner.nextInt();
        double[] grades = new double[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Nhập điểm môn học " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }

        Students newStudent = new Students(studentName, grades);
        students.add(newStudent);

        System.out.println("Thêm sinh viên thành công.");
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Nhập tên sinh viên cần cập nhật thông tin: ");
        String studentName = scanner.next();
        Students foundStudent = findStudentByName(studentName);

        if (foundStudent != null) {
            System.out.print("Nhập tên mới cho sinh viên: ");
            String newStudentName = scanner.next();
            System.out.print("Nhập số môn học: ");
            int numSubjects = scanner.nextInt();
            double[] newGrades = new double[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Nhập điểm môn học " + (i + 1) + ": ");
                newGrades[i] = scanner.nextDouble();
            }

            foundStudent.studentName = newStudentName;
            foundStudent.grades = newGrades;

            System.out.println("Cập nhật thông tin sinh viên thành công.");
        } else {
            System.out.println("Không tìm thấy sinh viên.");
        }
    }

    private static void displayStudents() {
        System.out.println("Danh sách sinh viên:");
        for (Students student : students) {
            System.out.println("Tên sinh viên: " + student.studentName);
            System.out.println("Điểm trung bình: " + student.calculateAverage());
            System.out.println("Xếp loại: " + student.getGradeCategory());
            System.out.println("------------------------------");
        }
    }

    private static void calculateAverage(Scanner scanner) {
        System.out.print("Nhập tên sinh viên cần tính điểm trung bình: ");
        String studentName = scanner.next();
        Students foundStudent = findStudentByName(studentName);

        if (foundStudent != null) {
            System.out.println("Điểm trung bình của sinh viên " + foundStudent.studentName + ": " + foundStudent.calculateAverage());
        } else {
            System.out.println("Không tìm thấy sinh viên.");
        }
    }

    private static void displayStudentClassification(Scanner scanner) {
        System.out.print("Nhập tên sinh viên cần xem xếp loại: ");
        String studentName = scanner.next();
        Students foundStudent = findStudentByName(studentName);

        if (foundStudent != null) {
            System.out.println("Xếp loại của sinh viên " + foundStudent.studentName + ": " + foundStudent.getGradeCategory());
        } else {
            System.out.println("Không tìm thấy sinh viên.");
        }
    }

    private static void sortStudentsByAverage() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).calculateAverage() > students.get(j + 1).calculateAverage()) {
                    Students temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sắp xếp sinh viên theo điểm trung bình tăng dần:");
        displayStudents();
    }

    private static void searchStudentByName(Scanner scanner) {
        System.out.print("Nhập tên sinh viên cần tìm kiếm: ");
        String studentName = scanner.next();
        Students foundStudent = findStudentByName(studentName);

        if (foundStudent != null) {
            System.out.println("Thông tin sinh viên:");
            System.out.println("Tên sinh viên: " + foundStudent.studentName);
            System.out.println("Điểm trung bình: " + foundStudent.calculateAverage());
            System.out.println("Xếp loại: " + foundStudent.getGradeCategory());
        } else {
            System.out.println("Không tìm thấy sinh viên.");
        }
    }

    private static Students findStudentByName(String studentName) {
        for (Students student : students) {
            if (student.studentName.equals(studentName)) {
                return student;
            }
        }
        return null;
    }

    private static void displayStudentStatistics() {
        int excellentCount = 0;
        int goodCount = 0;
        int averageCount = 0;
        int weakCount = 0;

        for (Students student : students) {
            String gradeCategory = student.getGradeCategory();
            switch (gradeCategory) {
                case "Giỏi":
                    excellentCount++;
                    break;
                case "Khá":
                    goodCount++;
                    break;
                case "Trung bình":
                    averageCount++;
                    break;
                case "Yếu":
                    weakCount++;
                    break;
            }
        }

        System.out.println("Thống kê số lượng sinh viên theo loại:");
        System.out.println("Giỏi: " + excellentCount);
        System.out.println("Khá: " + goodCount);
        System.out.println("Trung bình: " + averageCount);
        System.out.println("Yếu: " + weakCount);
    }

    private static void displayPassingStudents() {
        System.out.println("Danh sách sinh viên đạt môn học:");
        for (Students student : students) {
            if (isPass(student)) {
                System.out.println("Tên sinh viên: " + student.studentName);
                System.out.println("Điểm trung bình: " + student.calculateAverage());
                System.out.println("Xếp loại: " + student.getGradeCategory());
                System.out.println("------------------------------");
            }
        }
    }

    private static boolean isPass(Students student) {
        for (double grade : student.grades) {
            if (grade < 5.0) {
                return false;
            }
        }
        return true;
    }
}
