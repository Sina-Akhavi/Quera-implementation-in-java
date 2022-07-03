package ir.ac.kntu.util;

public class EnumUtil {


    public enum WelcomeOptions {
        ENTER_AS_ADMIN, ENTER_AS_USER, FIRST_TIME, ENTER_AS_GUEST
    }

    public enum AdminMenueOptions {
        COURSES, MATCHES, USERS, MAKE_A_USER_ADMIN, SEARCH, BACK
    }

    public enum UserMenueOptions {
        PROFILE, MY_COURSES, COURSES_IN_QUERA, MATCHES, BACK
    }

    public enum AdminCoursesMenueOption {
        CREATE_COURSE, EDIT_COURSES, BACK
    }

    public enum EditUserOption {
        REMOVE_FROM_COURSES, BACK
    }

    public enum MatchesAdminOption {
        CREATE, EDIT, BACK
    }

    public enum EditCoursesMenue {
        REMOVE_COURSES, EDIT_COURSES_GENERAL_INFO
    }

    public enum EditCourseOption {
        EDIT_NAME, EDIT_INSTITUTE, EDIT_STUDY_YEAR, EDIT_PRESENTER
    }

    public enum GuestMenueOption {
        BANK_OF_QUESTION, SEE_MATCHES, REGISTER_AS_NORMAL_USER,BACK
    }

    public enum CreateMatchMenue {
        CREATE_NORMAL, CREATE_PERSONAL, CREATE_SPECIAL
    }

    public enum EditMatchOption {
        REMOVE_MATCH, ADD_QUESTION, DELETE_QUESTION, CHANGE_START_DATE,
        CHANGE_NAME_OF_MATCH, CHANGE_MINUTES_LENGTH
    }

    public enum SearchOption {
        SEARCH_USER, SEARCH_MATCH
    }

    public static WelcomeOptions handleWelcomeChoice(int choie) {
        WelcomeOptions welcomeOptions = WelcomeOptions.values()[choie - 1];
        return welcomeOptions;
    }

    public static UserMenueOptions handleUserMenueChoice(int choice) {
        UserMenueOptions userMenueOptions = UserMenueOptions.values()[choice - 1];
        return userMenueOptions;
    }

    public static AdminCoursesMenueOption handleAdminCoursesOption(int choice) {
        AdminCoursesMenueOption adminCoursesMenueOption = AdminCoursesMenueOption.values()[choice - 1];
        return adminCoursesMenueOption;
    }

    public static EditUserOption handleEditUserOption(int choice) {
        EditUserOption editUserOption = EditUserOption.values()[choice - 1];
        return editUserOption;
    }

    public static MatchesAdminOption handleMatchesAdminOption(int choice) {
        MatchesAdminOption matchesAdminOption = MatchesAdminOption.values()[choice - 1];
        return matchesAdminOption;
    }

    public static EditCoursesMenue handleEditCoursesMenueOption(int choice) {
        EditCoursesMenue editCoursesMenue = EditCoursesMenue.values()[choice - 1];
        return editCoursesMenue;
    }

    public static EditCourseOption handleEditCourseOption(int choice) {
        EditCourseOption editCourseOption = EditCourseOption.values()[choice - 1];
        return editCourseOption;
    }

    public static GuestMenueOption handleGeustMenueOption(int choice) {
        GuestMenueOption guestMenueOption = GuestMenueOption.values()[choice - 1];
        return guestMenueOption;
    }

    public static CreateMatchMenue handleCreateMatchMenueOption(int choice) {
        CreateMatchMenue createMatchMenue = CreateMatchMenue.values()[choice - 1];
        return createMatchMenue;
    }

    public static EditMatchOption handlEditMatchOption(int choice) {
        EditMatchOption editMatchOption = EditMatchOption.values()[choice - 1];
        return editMatchOption;
    }

    public static SearchOption handleSearchOption(int choice) {
        SearchOption searchOption = SearchOption.values()[choice - 1];
        return searchOption;
    }
}
