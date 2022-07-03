package ir.ac.kntu.util;

import ir.ac.kntu.logic.*;
import java.util.ArrayList;

public class Menue {
    public static void printEditCourseMenue() {
        System.out.println("**********************");
        System.out.println("--Course Edition--");
        System.out.println("Which one do you want to edit? ");
        System.out.println("1) name");
        System.out.println("2) presenter");
        System.out.println("3) practices");
        System.out.println("4) users in the course");
        System.out.print("choose your choice: ");
    }

    public static void printEditPracticesMenue(ArrayList<Practice> practices) {
        System.out.println("** Edit Practices **");
        int i;
        for (i = 0; i < practices.size(); i++) {
            System.out.println((i + 1) + ") " + practices.get(i).getName());
        }
        System.out.println((i + 1) + ") create a new Practice");
        System.out.println();
        System.out.print("choose your choice: ");
    }

    public static void printPracticeMenue() {
        System.out.println("** Edit Practice **");
        System.out.println("1) add Question");
        System.out.println("2) remove Question");
        System.out.print("choose your choice: ");
    }

    public static void printRemoveQuestionMenue(Practice practice) {
        ArrayList<Question> practiceQuestions = practice.getQuestions();
        for (int i = 0; i < practiceQuestions.size(); i++) {
            System.out.println((i + 1) + ") " + practiceQuestions.get(i).getName());
        }
        System.out.print("\nchoose your choice: ");
    }

    public static void printEditUsersMenue(Course course) {
        System.out.println("** Edit Users **");
        System.out.println("1) remove a user from " + course.getName());
        System.out.println("2) see users in " + course.getName());

        System.out.print("choose your choice: ");
    }

    public static void printQueraMenue() {
        System.out.println("--Welcome--\n");
        System.out.println("1) Enter as Admin");
        System.out.println("2) Enter as User");
        System.out.println("3) first time to enter");
        System.out.println("4) Enter as guest");
        System.out.print("--choose your choice: ");
    }

    public static String[] printUserLogInMenue() {
        String[] userPass = new String[2];
        System.out.println("--User Log in--\n");
        System.out.print("Enter username: ");
        String username = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        userPass[0] = username;
        userPass[1] = password;
        return userPass;
    }

    public static String[] printAdminLogInMenue() {
        String[] userPass = new String[2];

        System.out.println("--Admin Log in--\n");
        System.out.print("Enter username: ");
        String username = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        userPass[0] = username;
        userPass[1] = password;
        return userPass;
    }

    public static void printAdminMenue(Admin admin) {
        System.out.println("-- Admin Menue --");
        System.out.println("Admin Username: " + admin.getUsername() + "\n");
        System.out.println("1) courses");
        System.out.println("2) matches");
        System.out.println("3) users");
        System.out.println("4) make a user admin");
        System.out.println("5) search");
        System.out.println("6) Back\n");
        System.out.print("-choose your choice: ");
    }

    public static void printUserMenue(User user) {
        System.out.println("\n-- User Menue --\n");
        System.out.println("username: " + user.getUsername() + "\n");

        System.out.println("1) my profile");
        System.out.println("2) my courses");
        System.out.println("3) courses in quera");
        System.out.println("4) matches");
        System.out.println("5) BACK");

        System.out.print("-choose your choice: ");
    }

    public static void printAdminCoursesMenue() {
        System.out.println("-- Course Menue for Admin --\n");
        System.out.println("1) create a new course");
        System.out.println("2) edit courses in quera");
        System.out.println("3) Back\n");

        System.out.print("-choose your choice: ");
    }

    public static void printUsersInQuera() {
        System.out.println("All Users in Quera: \n");

        ArrayList<User> usersInQuera = Quera.getInstance().getUsers();
        int i;
        for (i = 0; i < usersInQuera.size(); i++) {
            User user = usersInQuera.get(i);
            System.out.print((i + 1) + ") name: " + user.getName());
            System.out.print(",  Username: " + user.getUsername());
            System.out.println(",  Password: " + user.getPassword());
        }
        System.out.println((i + 1) + ") " + "Back\n");
        System.out.print("-choose your choice: ");
    }

    public static void printEditUserMenue() {
        System.out.println("1) remove from courses he attends");
        System.out.println("2) Back");
        System.out.print("-choose your choice: ");
    }

    public static void printAdminMatchesMenue() {
        System.out.println("-- Matches Menue for Admin --\n");
        System.out.println("1) create new match");
        System.out.println("2) edit current matches");
        System.out.println("3) Back");

        System.out.print("-choose your choice: ");
    }

    public static void printMyProfile(User user) {
        System.out.println("-- Profile of " + user.getName() + " --\n");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email address: " + user.getEmail());
        System.out.println("degree: " + user.getDegree());
        System.out.println("score: " + user.getScore());
        WaitingMaker.getInstance().waiting();
    }

    public static void printCurrentMatches(User user) {
        ArrayList<BaseMatch> matches = Quera.getInstance().getMatches();

        System.out.println("-- Current Matches in Quera --\n");

        int i;
        for (i = 0; i < matches.size(); i++) {
            if (matches.get(i) instanceof PersonalMatch) {
                ArrayList<User> participants = matches.get(i).getParticipants();
                if (participants.contains(user)) {
                    System.out.println((i + 1) + ") " + matches.get(i));
                }
            } else {
                System.out.println((i + 1) + ") " + matches.get(i));
            }
        }
        System.out.println("\n" + (i + 1) + ") Back\n");
        System.out.print("-choose the match you want to join:  ");
    }

    public static void printRemoveUserFromCoursesMenue(ArrayList<Course> coursesUserAttends) {
        int i;
        for (i = 0; i < coursesUserAttends.size(); i++) {
            System.out.println((i + 1) + ") " + coursesUserAttends.get(i).getName());
        }
        System.out.println((i + 1) + ") Back\n");
        System.out.print("-choose your choice: ");
    }

    public static void printEditCoursesMenue() {
        System.out.println("-- Edit Courses --\n");
        System.out.println("1) remove courses");
        System.out.println("2) edit courses general information");
        System.out.println("3) Back");
        System.out.print("-choose your choice: ");
    }

    public static void printCoursesInQuera() {
        ArrayList<Course> coursesInQuera = Quera.getInstance().getCourses();
        int i;
        for (i = 0; i < coursesInQuera.size(); i++) {
            System.out.println((i + 1) + ") " + coursesInQuera.get(i).getName());
        }
        System.out.println((i + 1) + ") Back");
        System.out.print("-choose your choice: ");
    }

    public static void printCourseForAdmin() {
        System.out.println("-- Edit Course --\n");
        System.out.println("1) edit name");
        System.out.println("2) edit institute");
        System.out.println("3) edit study year");
        System.out.println("4) edit presenter");
        System.out.println("5) Back\n");
        System.out.print("--choose your choice: ");
    }

    public static void printGuestMenue() {
        System.out.println("-- Guest Menue --");
        System.out.println("1) Bank of questions");
        System.out.println("2) see matches");
        System.out.println("3) register as a normal user\n");
        System.out.println("4) Back");

        System.out.print("-choose your choice: ");
    }

    public static void printAllMatches() {
        ArrayList<BaseMatch> matches = Quera.getInstance().getMatches();

        int count = 1;
        for (BaseMatch baseMatch : matches) {
            System.out.println(count + ") " + baseMatch.getName() + ",   start date: " + baseMatch.getStartDate());
            count++;
        }
        System.out.println(count + ") Back");
    }

    public static void printMakeUserAdminMenue() {
        printUsersInQuera();
    }

    public static void printCreateMatchMenue() {
        System.out.println("--Create Match--\n");
        System.out.println("What kind of match do you want to create?");
        System.out.println("1) Normal match");
        System.out.println("2) Personal match");
        System.out.println("3) Special match");
        System.out.println("4) Back\n");
        System.out.print("-Enter your choice:");
    }

    public static void printEditMatchMenue() {
        System.out.println("\n\n--Edit Match Menue--\n");
        System.out.println("1) remove match");
        System.out.println("2) add question");
        System.out.println("3) delete a question");
        System.out.println("4) change start date");
        System.out.println("5) change name of match");
        System.out.println("6) change minutes length");
        System.out.println("7) Back\n");

        System.out.print("--choose your choice: ");
    }

    public static void printSearchMenue() {
        System.out.println("--SEARCH\n");
        System.out.println("1) User");
        System.out.println("2) Match");
        System.out.println("3) Back\n");
        System.out.print("choose your choice: ");
    }

    public static void printQuestionsInMatch(BaseMatch match) {
        ArrayList<Question> questionsInMatch = new ArrayList<Question>(match.getQuestionMap().keySet());

        int i = 0;
        for (Question q : questionsInMatch) {
            System.out.print((i + 1) + ") name: " + q.getName() + ",   score: "
                    + q.getScore() + ",   type: " + q.getQuestionType()
                    + ",   Difficulty: " + q.getDifficulty());
        }
    }
}
