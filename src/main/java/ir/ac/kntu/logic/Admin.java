package ir.ac.kntu.logic;

import ir.ac.kntu.util.*;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

public class Admin extends User {
    public Admin(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public static void search() {
        Menue.printSearchMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        EnumUtil.SearchOption searchOption = EnumUtil.handleSearchOption(choice);
        switch (searchOption) {
            case SEARCH_USER:
                User user = searchUserBaseOnUserName();
                System.out.println(user);
                WaitingMaker.getInstance().waiting();
                break;
            case SEARCH_MATCH:
                BaseMatch match = searchMatchBaseOnName();
                System.out.println(match);
                WaitingMaker.getInstance().waiting();
                break;
        }
    }

    public NormalMatch createMatchNormal() {
        NormalMatch normalMatch = Creator.createNormalMatch();
        return normalMatch;
    }

    public SpecialMatch createMatchSpecial() {
        SpecialMatch specialMatch = Creator.createSpecialMatch();
        return specialMatch;
    }

    public PersonalMatch createPersonalMatch() {
        PersonalMatch personalMatch = Creator.createPersonalMatch();
        return personalMatch;
    }

    public User findUserByName(String name) {
        Quera quera = Quera.getInstance();
        for (User user : quera.getUsers()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByEmail(String email) {
        Quera quera = Quera.getInstance();
        for (User user : quera.getUsers()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByUserPass(String username, String password) {
        Quera quera = Quera.getInstance();
        String id = username + "-" + password;
        if (quera.getRegisteredUsers().containsKey(id)) {
            return quera.getRegisteredUsers().get(id);
        }
        return null;
    }


    public void sendMessage(User user) {
        System.out.print("Write message to " + user.getName() + ": ");
        String message = ScannerWrapper.getInstance().nextLine();
        user.setMessage(message);
        WaitingMaker.getInstance().waiting();
    }

    private void addToQuera(BaseMatch match) {
        Quera.getInstance().addMatch(match);
        System.out.println("Successfully created match!!");
        WaitingMaker.getInstance().waiting();
    }

    public void seeInfoOfUsers() {
        System.out.println("******************");

        ArrayList<User> users = Quera.getInstance().getUsers();
        int count = 1;
        for (User user : users) {
            System.out.println(count++ + ") " + user);
        }
        System.out.println();
        WaitingMaker.getInstance().waiting();
    }

    public Course createCourse() {
        Course course = Creator.createCourse();
        Quera.getInstance().addCourse(course);
        return course;
    }

    public void deleteCourse(Course course) {
        Quera.getInstance().getCourses().remove(course);
    }


    public void EditCourse(Course course) {
        Editors.editCourse(course);
    }

    public void seeAllCoursesInQuera() {
        ArrayList<Course> queraCourses = Quera.getInstance().getCourses();

        int count = 1;
        for (Course course : queraCourses) {
            System.out.println(count++ + "course name: " + course.getName() + ", Professor: "
                    + course.getPresenter() + ", Institute: " + course.getInstitute());
        }
    }

    public void seeAllUsersInQuera() {
        ArrayList<User> allUsers = Quera.getInstance().getUsers();
        int count = 1;
        for (User user : allUsers) {
            System.out.println(count++ + ") " + user.getName() +
                    ",  email= " + user.getEmail() + ", degree in quera: " + user.getDegree());
        }
    }


    public void editMatch(BaseMatch match) {
        Menue.printEditMatchMenue();

        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        EnumUtil.EditMatchOption editMatchOption = EnumUtil.handlEditMatchOption(choice);
        switch (editMatchOption) {
            case REMOVE_MATCH:
                Quera.getInstance().getMatches().remove(match);
                System.out.println("removed successfully!!!");
                WaitingMaker.getInstance().waiting();
                break;
            case ADD_QUESTION:
                Question question = Creator.createQuestion();
                match.addQuestion(question);
                System.out.println("successfully added Question!!!");
                WaitingMaker.getInstance().waiting();
                break;
            case DELETE_QUESTION:
                Editors.deleteQuestionFromMatch(match);
                editMatch(match);
                break;
            case CHANGE_START_DATE:
                Editors.changeStartDate(match);
                editMatch(match);
                break;
            case CHANGE_NAME_OF_MATCH:
                Editors.ChangeName(match);
                editMatch(match);
                break;
            case CHANGE_MINUTES_LENGTH:
                Editors.changeMinuteLength(match);
                editMatch(match);
                break;
        }
    }
}