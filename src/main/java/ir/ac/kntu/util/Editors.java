package ir.ac.kntu.util;

import ir.ac.kntu.logic.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Editors {

    public enum CourseEdit {
        EDIT_NAME, EDIT_PRESENTER, EDIT_PRACTICES, EDIT_USERS;
    }
    public enum PracticeEdit {
        ADD_QUESTION, REMOVE_QUESTION
    }

    public enum UsersEdit {
        REMOVE_USER, SEE_USERS
    }

    public static void editCourse(Course course) {
        Menue.printEditCourseMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        CourseEdit courseEdit = CourseEdit.values()[choice - 1];

        switch (courseEdit) {
            case EDIT_NAME:
                System.out.print("Enter new name:");
                course.setName(ScannerWrapper.getInstance().nextLine());
                WaitingMaker.getInstance().waiting();
                break;
            case EDIT_PRESENTER:
                System.out.print("Enter new presenter:");
                course.setPresenter(ScannerWrapper.getInstance().nextLine());
                WaitingMaker.getInstance().waiting();
                break;
            case EDIT_PRACTICES:
                editPractices(course);
                break;
            case EDIT_USERS:
                editUsers(course);
                break;
            default:
                editCourse(course);
                break;
        }
    }

    public static void editName(Course course) {
        System.out.print("Enter new name: ");
        String newName = ScannerWrapper.getInstance().nextLine();
        course.setName(newName);
        WaitingMaker.getInstance().waiting();
    }

    public static void editInstitute(Course course) {
        System.out.print("Enter new institute: ");
        String newInstitute = ScannerWrapper.getInstance().nextLine();
        course.setInstitute(newInstitute);
        WaitingMaker.getInstance().waiting();
    }

    public static void editStudyYear(Course course) {
        System.out.print("Enter new study year: ");
        String newStudyYear = ScannerWrapper.getInstance().nextLine();
        course.setStudyYear(newStudyYear);
        WaitingMaker.getInstance().waiting();
    }

    public static void editPresenter(Course course) {
        System.out.print("Enter new presenter: ");
        String presenter = ScannerWrapper.getInstance().nextLine();
        course.setPresenter(presenter);
        WaitingMaker.getInstance().waiting();
    }


    private static void editUsers(Course course) {
        Menue.printEditUsersMenue(course);
        int choice = ScannerWrapper.getInstance().nextInt();
        int index = choice - 1;
        UsersEdit usersEdit = UsersEdit.values()[index];

        switch (usersEdit) {
            case REMOVE_USER:
                handleRemoveUser(course);
                break;
            case SEE_USERS:
                handleSeeUsers(course);
                break;
        }
    }

    private static void handleSeeUsers(Course course) {
        System.out.println("*** Information Of Users In " + course.getName() + " ***\n");
        for (User user : course.getUsers()) {
            System.out.println("name: " + user.getName() + ",   email: " +
                    user.getEmail() + ",   degree in quera: " + user.getDegree());
        }
    }

    private static void handleRemoveUser(Course course) {
        ArrayList<User> usersInCourse = course.getUsers();
        for (int i = 0; i < usersInCourse.size(); i++) {
            System.out.println((i + 1) + ") " + usersInCourse.get(i).getName());
        }
        System.out.print("Which user do you want to remove from class?(Enter number)  ");
        int choice = ScannerWrapper.getInstance().nextInt();
        int index = choice - 1;
        usersInCourse.remove(index);
        System.out.println("Successfully removed!!");
        WaitingMaker.getInstance().waiting();
    }

    private static void editPractices(Course course) {
        Menue.printEditPracticesMenue(course.getPractices());
        int choice = ScannerWrapper.getInstance().nextInt();

        int numberOfPractices = course.getPractices().size();
        if (choice <= numberOfPractices) {
            int index = choice - 1;
            editPractice(course.getPractices().get(index));
        } else if (choice == numberOfPractices + 1) {
            Practice newPractice = Creator.createPractice();
            course.getPractices().add(newPractice);
            System.out.println("Practice successfully added to course!!");
            WaitingMaker.getInstance().waiting();
        }
    }

    private static void editPractice(Practice practice) {
        Menue.printPracticeMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        int numberOfOptions = 2;
        if (choice > numberOfOptions) {
            editPractice(practice);
            return;
        }

        PracticeEdit practiceEdit[] = PracticeEdit.values();
        switch (practiceEdit[choice - 1]) {
            case ADD_QUESTION:
                handleAddQuestion(practice);
                break;
            case REMOVE_QUESTION:
                handleRemoveQuestion(practice);
        }
    }

    private static void handleAddQuestion(Practice practice) {
        Question newQuestion = Creator.createQuestion();
        System.out.println("successfully created question.");
        WaitingMaker.getInstance().waiting();
        practice.getQuestions().add(newQuestion);
        System.out.println("question successfully added to practice!!");
        WaitingMaker.getInstance().waiting();
    }

    private static void handleRemoveQuestion(Practice practice) {
        Menue.printRemoveQuestionMenue(practice);
        int choice = ScannerWrapper.getInstance().nextInt();
        int index = choice - 1;
        if (practice.getQuestions().size() < choice) {
            handleRemoveQuestion(practice);
            return;
        }
        practice.getQuestions().remove(index);
        System.out.println("question successfully removed from practice!!");
        WaitingMaker.getInstance().waiting();
    }

    public static void ChangeName(BaseMatch match) {
        System.out.println("Previous name: " + match.getName());
        System.out.print("Enter new name: ");
        String newName = ScannerWrapper.getInstance().nextLine();
        match.setName(newName);
        System.out.println("name successfully changed!!!\n");
        WaitingMaker.getInstance().waiting();
    }

    public static void changeMinuteLength(BaseMatch match) {
        System.out.println("Previous length(in minutes): " + match.getMinutesLength());
        System.out.print("Enter new time length(minutes): ");
        int newLengthTime = ScannerWrapper.getInstance().nextInt();
        match.setMinutesLength(newLengthTime);
        System.out.println("time length successfully changed!!!\n");
        WaitingMaker.getInstance().waiting();
    }

    public static void changeStartDate(BaseMatch match) {
        System.out.println("Previous starting date: " + match.getStartDate());
        System.out.println("--new start date:");
        System.out.print("-Enter new day: ");
        int newDay = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        System.out.print("-Enter new month: ");
        int newMonth = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        System.out.print("-Enter new year: ");
        int newYear = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        match.setStartDate(new Date(newDay, newMonth, newYear));

        System.out.println("starting date successfully changed!!!\n");
        WaitingMaker.getInstance().waiting();
    }

    public static void deleteQuestionFromMatch(BaseMatch match) {
        System.out.println("--All Questions in " + match.getName() + "--\n");
        Menue.printQuestionsInMatch(match);

        System.out.print("Enter your choice (any other number to back): ");
        int choice = ScannerWrapper.getInstance().nextInt();
        ArrayList<Question> questions = new ArrayList<Question>(match.getQuestionMap().keySet());
        if (choice <= questions.size()) {
            int index = choice - 1;
            Question mustDelete = questions.get(index);
            match.getQuestionMap().remove(mustDelete);
            System.out.println("successfully deleted!!");
            WaitingMaker.getInstance().waiting();
        }
    }


}
