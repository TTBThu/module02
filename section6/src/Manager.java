import java.util.Arrays;
import java.util.Scanner;

public class Manager {
    private BaseEntity[] entities;
    private int defaultEl = 5;
    private int index = 0;

    public Manager() {
        this.entities = new BaseEntity[defaultEl];
    }

    public void add(BaseEntity item) {
        if (index == entities.length) {
            entities = Arrays.copyOf(entities, entities.length + defaultEl);
        }
        entities[index++] = item;
    }

    public void show() {
        for (int i = 0; i < index; i++) {
            entities[i].show();
        }
    }

    public BaseEntity findId(Object id) {
        for (int i = 0; i < index; i++) {
            if (entities[i].checkId(id)) {
                return entities[i];
            }
        }
        return null;
    }

    public BaseEntity findFirst() {
        if (index > 0) {
            return entities[0];
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();

        Student student = new Student();
        student.input();
        manager.add(student);

        Employee employee = new Employee();
        employee.input();
        manager.add(employee);

        manager.show();
    }
}
