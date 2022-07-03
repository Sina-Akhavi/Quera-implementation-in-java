package ir.ac.kntu.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private ArrayList<User> users;

    private String name;

    private String presenter;

    private String institute;

    private String studyYear;

    private String description;

    private boolean isAllowedToRegister;

    private boolean isPersonalCourse;

    private String password;

    private Teacher teacher;

    private ArrayList<Practice> practices;

    private Map<String, User> registeredUsers;

    public Course(String name, String institute, String studyYear, String presenter) {
        this.name = name;
        this.institute = institute;
        this.studyYear = studyYear;

        this.users = new ArrayList<>();
        this.practices = new ArrayList<Practice>();
        this.registeredUsers = new HashMap<String, User>();
        this.presenter = presenter;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = new Teacher(teacher);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllowedToRegister() {
        return isAllowedToRegister;
    }

    public void setAllowedToRegister(boolean isAllowedToRegister) {
        this.isAllowedToRegister = isAllowedToRegister;
    }

    public boolean isPersonalCourse() {
        return isPersonalCourse;
    }

    public void setPersonalCourse(boolean isPersonalCourse) {
        this.isPersonalCourse = isPersonalCourse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Practice> getPractices() {
        return new ArrayList<Practice>(practices);
    }

    public void setPractices(ArrayList<Practice> practices) {
        this.practices = new ArrayList<>(practices);
    }

    public Map<String, User> getRegisteredUsers() {
        return new HashMap<>(registeredUsers);
    }

    public boolean addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            return true;
        } else {
            System.out.println("Already exist this User !!!");
            return false;
        }
    }

    public boolean containsPasswordUserName(String password, String userName) {
        return registeredUsers.containsKey(userName + "-" + password);
    }

    public boolean addPractice(Practice practice) {
        practice.setCourseUsers(users);

        if (!practices.contains(practice)) {
            practices.add(practice);
            return true;
        }
        return false;
    }

    public Practice getPractice(int seri) {
        if (seri < 1 || seri > practices.size()) {
            System.out.println("invalid input!!");
            return null;
        }
        return practices.get(seri - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course course = (Course) o;

        if (!name.equals(course.name)) {
            return false;
        }
        if (!presenter.equals(course.presenter)) {
            return false;
        }
        if (!institute.equals(course.institute)) {
            return false;
        }
        return studyYear.equals(course.studyYear);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + presenter.hashCode();
        result = 31 * result + institute.hashCode();
        result = 31 * result + studyYear.hashCode();
        return result;
    }
}
