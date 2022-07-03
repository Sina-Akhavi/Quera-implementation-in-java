package ir.ac.kntu.logic;

import ir.ac.kntu.util.Creator;
import ir.ac.kntu.util.ScannerWrapper;

public class NormalUser extends User {

    public NormalUser(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public boolean joinCourse(Course course) {
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter username: ");
        String username = ScannerWrapper.getInstance().nextLine();

        return course.addUser(this);
    }

    public Course createCourse() {
        Course course = Creator.createCourse();
        Quera.getInstance().addCourse(course);
        return course;
    }

    public boolean joinMatch() {
        return false;
    }
}
