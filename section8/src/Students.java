public class Students {
    String studentName;
    double[] grades;

    public Students(String studentName, double[] grades) {
        this.studentName = studentName;
        this.grades = grades;
    }

    public double calculateAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public String getGradeCategory() {
        double average = calculateAverage();
        if (average >= 8.0) {
            return "Giỏi";
        } else if (average >= 6.5) {
            return "Khá";
        } else if (average >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }
}
