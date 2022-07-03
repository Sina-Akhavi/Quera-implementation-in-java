package ir.ac.kntu.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quera {
    private static Quera quera = new Quera();

    private ArrayList<BaseMatch> matches;

    private ArrayList<User> users;

    private Map<String, User> registeredUsers;

    private ArrayList<Course> courses;

    private ArrayList<Admin> admins;

    private Quera() {
        users = new ArrayList<>();
        registeredUsers = new HashMap<>();
        courses = new ArrayList<>();
        matches = new ArrayList<>();
        admins = new ArrayList<>();
    }

    public boolean addAdmin(Admin admin) {
        if (admins.contains(admin)) {
            return false;
        } else {
            admins.add(admin);
            return true;
        }
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<BaseMatch> getMatches() {
        return matches;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setRegisteredUsers(Map<String, User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public static Quera getInstance() {
        return quera;
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourse(Course course) {
        if (courses.contains(course)) {
            return false;
        }
        courses.add(course);
        return true;
    }

    public boolean addUser(User user) {
        String userName = user.getUsername();
        String password = user.getPassword();

        if (users.contains(user)) {
            System.out.println("This user pass already exist!!");
            return false;
        }
        users.add(user);
        System.out.println("User successfully added!!");
        return true;
    }

    public boolean logIn(String password, String userName) {
        String userPass = userName + "-" + password;
        ArrayList<String> usersUserNamePass = new ArrayList<>(registeredUsers.keySet());
        if (usersUserNamePass.contains(userPass)) {
            return true;
        }
        return false;
    }

    public User getUser(String password, String userName) {
        User output = null;
        String userPass = userName + "-" + password;

        output = registeredUsers.get(userPass);
        if (output != null) {
            return output;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }

    public boolean addMatch(BaseMatch match) {
        if (matches.contains(match)) {
            return false;
        }
        matches.add(match);
        return true;
    }

    public boolean checkUsernamePasswordForAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Admin getAdminByUserPass(String username, String password) {
        for (Admin admin : admins) {
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    public boolean checkUsernamePasswordForUser(String username, String password) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User getUserByUserPass(String username, String password) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        for (Course course : getCourses()) {
            if (course.getUsers().contains(user)) {
                course.getUsers().remove(user);
            }
        }
        getUsers().remove(user);
    }
}
