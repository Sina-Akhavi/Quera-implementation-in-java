package ir.ac.kntu.Driver;

import ir.ac.kntu.logic.*;
import ir.ac.kntu.util.*;
import java.util.ArrayList;


public class Driver {

    public Driver() {
        Creator.createFirstUsers();
        Creator.createFirstCourses();
        Creator.createFirstAdmins();
        Creator.addFirstQuestionsToBank();
        Creator.createFirstMatches();
    }

    public void start() {
        Menue.printQueraMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();


        WaitingMaker.getInstance().waiting();
        EnumUtil.WelcomeOptions option = EnumUtil.handleWelcomeChoice(choice);
        switch (option) {
            case ENTER_AS_ADMIN:
                adminLogIn();
                break;
            case ENTER_AS_USER:
                userLogIn();
                break;
            case FIRST_TIME:
                newUserLogIn();
                break;
            case ENTER_AS_GUEST:
                guestMenue();
                break;
        }
    }

    private void guestMenue() {
        Menue.printGuestMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        EnumUtil.GuestMenueOption guestMenueOption = EnumUtil.handleGeustMenueOption(choice);

        switch (guestMenueOption) {
            case BANK_OF_QUESTION:
                BankOfQuestion.getInstance().printBaseOnDate();
                WaitingMaker.getInstance().waiting();
                guestMenue();
                break;
            case SEE_MATCHES:
                Menue.printAllMatches();
                WaitingMaker.getInstance().waiting();
                guestMenue();
                break;
            case REGISTER_AS_NORMAL_USER:
                registerAsUser();
            default:
                start();
        }
    }

    private void registerAsUser() {
         User user = Creator.createUser();

         if (!Quera.getInstance().checkUsernamePasswordForUser(user.getUsername(), user.getPassword())) {
             Quera.getInstance().getUsers().add(user);
             System.out.println("added successfully!!");
             goUserProfile(user);
         } else {
             System.out.println("Already exist!!!");
             guestMenue();
         }
    }

    private void newUserLogIn() {
        System.out.println("-- join Quera --\n");
        System.out.println("fill the information below: \n");
        User user = Creator.createUser();

        boolean isExistUserPass = Quera.getInstance().checkUsernamePasswordForUser(user.getUsername()
                , user.getPassword());
        if (!isExistUserPass) {
            Quera.getInstance().addUser(user);
            WaitingMaker.getInstance().waiting();
            goUserProfile(user);
        } else {
            System.out.println("Sorry!! This user pass already exist!!");
            WaitingMaker.getInstance().waiting();
            newUserLogIn();
        }
    }

    private void userLogIn() {
         String[] userPass = Menue.printUserLogInMenue();

        if (Quera.getInstance().checkUsernamePasswordForUser(userPass[0], userPass[1])) {
            User curUser = Quera.getInstance().getUserByUserPass(userPass[0], userPass[1]);
            goUserProfile(curUser);
        } else {
            System.out.println("This user pass does not exist!!!\n");
            WaitingMaker.getInstance().waiting();
            start();
        }
    }

    private void goUserProfile(User user) {
        Menue.printUserMenue(user);
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        WaitingMaker.getInstance().waiting();

        EnumUtil.UserMenueOptions userMenueOptions = EnumUtil.handleUserMenueChoice(choice);
        switch (userMenueOptions) {
            case PROFILE:
                Menue.printMyProfile(user);
                goUserProfile(user);
                break;
            case MY_COURSES:
                //goToMyCourses(user);
                break;
            case COURSES_IN_QUERA:
                registersInACoursesInQuera(user);
                break;
            case MATCHES:
                seeMatchesForUser(user);
                break;
            default:
                start();
        }
    }

    private void registersInACoursesInQuera(User user) {
        System.out.println("---Courses in Quera---\n");
        Menue.printCoursesInQuera();

        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        if (Quera.getInstance().getCourses().size() >= choice) {
            Course course = Quera.getInstance().getCourses().get(choice - 1);
            course.addUser(user);
            System.out.println("Successfully added");
            WaitingMaker.getInstance().waiting();
            goUserProfile(user);
        } else {
            goUserProfile(user);
        }
    }

    private void seeMatchesForUser(User user) {
        Menue.printCurrentMatches(user);
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        ArrayList<BaseMatch> matches = Quera.getInstance().getMatches();
        if (choice <= matches.size()) {
            matches.get(choice - 1).addParticipant(user);
            WaitingMaker.getInstance().waiting();
            goUserProfile(user);
        } else {
            goUserProfile(user);
        }
    }

    private void adminLogIn() {
        String[] userPass = Menue.printAdminLogInMenue();

        if (Quera.getInstance().checkUsernamePasswordForAdmin(userPass[0], userPass[1])) {
            Admin curAdmin = Quera.getInstance().getAdminByUserPass(userPass[0], userPass[1]);
            goAdminProfile(curAdmin);
        } else {
            System.out.println("This user pass does not exist!!!\n");
            WaitingMaker.getInstance().waiting();
            start();
        }
    }

    private void goAdminProfile(Admin curAdmin) {
        Menue.printAdminMenue(curAdmin);
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        WaitingMaker.getInstance().waiting();
        EnumUtil.AdminMenueOptions adminMenueOptions = EnumUtil.AdminMenueOptions.values()[choice - 1];
        switch (adminMenueOptions) {
            case COURSES:
                goToAdminCoursesMenue(curAdmin);
                break;
            case MATCHES:
                goToMatchesAdminMenue(curAdmin);
                break;
            case USERS:
                editUsers(curAdmin);
                break;
            case MAKE_A_USER_ADMIN:
                makeUserAdmin(curAdmin);
                break;
            case SEARCH:
                Admin.search();
                goAdminProfile(curAdmin);
            default:
                start();
                break;
        }
    }

    private void makeUserAdmin(Admin admin) {
        System.out.println("-- Make a User Admin --\n");
        Menue.printUsersInQuera();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        ArrayList<User> users = Quera.getInstance().getUsers();
        if (choice <= users.size()) {
            User user = users.get(choice - 1);
            turnUserToAdmin(user);
            System.out.println(user.getName() + " became an admin successfully !!");
            WaitingMaker.getInstance().waiting();
            goAdminProfile(admin);
        } else {
            goAdminProfile(admin);
        }
    }

    private void turnUserToAdmin(User user) {
        Admin newAdmin = new Admin(user.getName(), user.getPassword(), user.getUsername(), user.getEmail());
        Quera.getInstance().addAdmin(newAdmin);
        Quera.getInstance().removeUser(user);
    }

    private void goToMatchesAdminMenue(Admin admin) {
        Menue.printAdminMatchesMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        EnumUtil.MatchesAdminOption matchesAdminOption = EnumUtil.handleMatchesAdminOption(choice);
        switch (matchesAdminOption) {
            case CREATE:
                createMatch(admin);
                goToMatchesAdminMenue(admin);
                break;
            case EDIT:
                editMatches(admin);
                break;
            default:
                goAdminProfile(admin);
        }
    }

    private void editMatches(Admin admin) {
        System.out.println("\n\n--All Matches in Quera-- \n");
        Menue.printAllMatches();

        System.out.print("--choose a match to edit: ");
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        if (choice <= Quera.getInstance().getMatches().size()) {
            BaseMatch match = Quera.getInstance().getMatches().get(choice - 1);
            admin.editMatch(match);
            goToMatchesAdminMenue(admin);
        } else {
            goToMatchesAdminMenue(admin);
        }
    }

    private void createMatch(Admin admin) {
        Menue.printCreateMatchMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        EnumUtil.CreateMatchMenue createMatchMenue = EnumUtil.handleCreateMatchMenueOption(choice);
        switch (createMatchMenue) {
            case CREATE_NORMAL:
                NormalMatch normalMatch = Creator.createNormalMatch();
                Quera.getInstance().addMatch(normalMatch);
                normalMatch.addQuestions();
                break;
            case CREATE_PERSONAL:
                PersonalMatch personalMatch = Creator.createPersonalMatch();
                Quera.getInstance().addMatch(personalMatch);
                personalMatch.addQuestions();
                break;
            case CREATE_SPECIAL:
                SpecialMatch specialMatch = Creator.createSpecialMatch();
                Quera.getInstance().addMatch(specialMatch);
                specialMatch.addQuestions();
                break;
            default:
                goToMatchesAdminMenue(admin);
        }
        System.out.println("Match successfully created!!!");
        goToMatchesAdminMenue(admin);
    }

    private void editUsers(Admin admin) {
        System.out.println("\n-- Edit Users --");
        Menue.printUsersInQuera();
        int choice = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();
        WaitingMaker.getInstance().waiting();

        if (choice <= Quera.getInstance().getUsers().size()) {
            User myUser = Quera.getInstance().getUsers().get(choice - 1);
            editUser(myUser, admin);
        } else {
            goAdminProfile(admin);
        }
    }

    private void editUser(User myUser, Admin admin) {
        System.out.println("--Edit " + myUser.getName() + "--\n");
        Menue.printEditUserMenue();

        int choice = ScannerWrapper.getInstance().nextInt();
        EnumUtil.EditUserOption editUserOption = EnumUtil.handleEditUserOption(choice);
        WaitingMaker.getInstance().waiting();
        switch (editUserOption) {
            case REMOVE_FROM_COURSES:
                removeUserFromCourses(myUser, admin);
                break;
            default:
                editUsers(admin);
        }
    }

    private void removeUserFromCourses(User myUser, Admin admin) {
        ArrayList<Course> coursesUserAttends = new ArrayList<>();

        System.out.println("User courses: \n");
        for (Course course : Quera.getInstance().getCourses()) {
            if (course.getUsers().contains(myUser)) {
                coursesUserAttends.add(course);
            }
        }

        Menue.printRemoveUserFromCoursesMenue(coursesUserAttends);
        int choice = ScannerWrapper.getInstance().nextInt();
        int index = choice - 1;


        if (choice > coursesUserAttends.size()) {
            editUser(myUser, admin);
        } else {
            Course mustDeleteCourse = coursesUserAttends.get(index);
            mustDeleteCourse.getUsers().remove(myUser);
            System.out.println("Successfully deleted!!!");
            WaitingMaker.getInstance().waiting();
        }
    }

    private void goToAdminCoursesMenue(Admin admin) {
        Menue.printAdminCoursesMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        WaitingMaker.getInstance().waiting();
        EnumUtil.AdminCoursesMenueOption adminCoursesMenueOption = EnumUtil.handleAdminCoursesOption(choice);
        switch (adminCoursesMenueOption) {
            case CREATE_COURSE:
                createNewCourse(admin);
                break;
            case EDIT_COURSES:
                editCourses(admin);
                break;
            default:
                goAdminProfile(admin);
        }
    }

    private void editCourses(Admin admin) {
        Menue.printEditCoursesMenue();
        int choice = ScannerWrapper.getInstance().nextInt();
        EnumUtil.EditCoursesMenue editCoursesMenue = EnumUtil.handleEditCoursesMenueOption(choice);
        switch (editCoursesMenue) {
            case REMOVE_COURSES:
                removeCourses(admin);
                break;
            case EDIT_COURSES_GENERAL_INFO:
                editCoursesGeneralInfo(admin);
                break;
            default:
                goToAdminCoursesMenue(admin);
        }
    }

    private void editCoursesGeneralInfo(Admin admin) {
        System.out.println("Which courses do you want to edit general info?\n");
        Menue.printCoursesInQuera();
        int choice = ScannerWrapper.getInstance().nextInt();
        ArrayList<Course> coursesInQuera = Quera.getInstance().getCourses();
        if (choice <= coursesInQuera.size()) {
            int index = choice - 1;
            editCourse(coursesInQuera.get(index), admin);
        } else {
            editCourses(admin);
        }
    }

    private void editCourse(Course course, Admin admin) {
        Menue.printCourseForAdmin();
        int choice = ScannerWrapper.getInstance().nextInt();
        EnumUtil.EditCourseOption editCourseOption = EnumUtil.handleEditCourseOption(choice);
        switch (editCourseOption) {
            case EDIT_NAME:
                Editors.editName(course);
                break;
            case EDIT_INSTITUTE:
                Editors.editInstitute(course);
                break;
            case EDIT_STUDY_YEAR:
                Editors.editStudyYear(course);
                break;
            case EDIT_PRESENTER:
                Editors.editPresenter(course);
                break;
            default:
                editCoursesGeneralInfo(admin);
        }
    }

    private void removeCourses(Admin admin) {
        System.out.println("Which courses do you want to remove?");
        Menue.printCoursesInQuera();

        int choice = ScannerWrapper.getInstance().nextInt();
        ArrayList<Course> queraCourses = Quera.getInstance().getCourses();
        if (choice <= queraCourses.size()) {
            int index = choice - 1;
            queraCourses.remove(index);
            WaitingMaker.getInstance().waiting();
        } else {
            editCourses(admin);
        }
    }

    private void createNewCourse(Admin admin) {
        Course createdCourse = Creator.createCourse();
        System.out.println("\n\nChoose the teacher: \n");
        Menue.printUsersInQuera();

        int choice = ScannerWrapper.getInstance().nextInt();
        int index = choice - 1;

        ArrayList<User> users = Quera.getInstance().getUsers();

        createdCourse.setTeacher(users.get(index));

        Quera.getInstance().addCourse(createdCourse);
        System.out.println("Course successfully created!!!");
        WaitingMaker.getInstance().waiting();
        goToAdminCoursesMenue(admin);
    }
}
