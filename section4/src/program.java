public class program {
    private Student[] students;
    private int numberOfStudents;

    public program(int maxStudents) {
        students = new Student[maxStudents];
        numberOfStudents = 0;
    }

    public boolean add(Student st) {
        if (findIndex(st.getId()) == -1) {
            if (numberOfStudents < students.length) {
                students[numberOfStudents++] = st;
                return true;
            } else {
                System.out.println("Class is full. Cannot add more students.");
                return false;
            }
        } else {
            System.out.println("Student with ID " + st.getId() + " already exists in the class.");
            return false;
        }
    }

    public boolean edit(Student st) {
        int index = findIndex(st.getId());
        if (index != -1) {
            students[index].setFirstName(st.getFirstName());
            students[index].setLastName(st.getLastName());
            students[index].setMarkCss(st.getMarkCss());
            students[index].setMarkHtml(st.getMarkHtml());
            students[index].setMarkJs(st.getMarkJs());
            return true;
        } else {
            System.out.println("Student with ID " + st.getId() + " not found in the class.");
            return false;
        }
    }

    public boolean remove(Student st) {
        int index = findIndex(st.getId());
        if (index != -1) {
            for (int i = index; i < numberOfStudents - 1; i++) {
                students[i] = students[i + 1];
            }
            students[--numberOfStudents] = null;
            return true;
        } else {
            System.out.println("Student with ID " + st.getId() + " not found in the class.");
            return false;
        }
    }

    public int findIndex(String id) {
        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
