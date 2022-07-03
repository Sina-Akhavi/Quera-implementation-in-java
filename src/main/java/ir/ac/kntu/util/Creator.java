package ir.ac.kntu.util;

import ir.ac.kntu.logic.*;

public class Creator {

    public static NormalMatch createNormalMatch() {
        System.out.print("Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();

        System.out.println("Enter start date: ");
        System.out.print("--Enter day: ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("--Enter month: ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("--Enter year: ");
        int year = ScannerWrapper.getInstance().nextInt();
        Date startDate = new Date(day, month, year);


        System.out.print("Enter time length(in minute): ");
        int length = ScannerWrapper.getInstance().nextInt();
        return new NormalMatch(name, startDate, length);
    }

    public static SpecialMatch createSpecialMatch() {
        System.out.print("Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();

        System.out.println("Enter start date: ");
        System.out.print("Enter day: ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter month: ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter year: ");
        int year = ScannerWrapper.getInstance().nextInt();
        Date startDate = new Date(day, month, year);

        System.out.print("Enter time length(in minute): ");
        int length = ScannerWrapper.getInstance().nextInt();
        return new SpecialMatch(name, startDate, length);
    }

    public static PersonalMatch createPersonalMatch() {
        System.out.println("--Create Personal Match:\n");
        System.out.print("-Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();

        System.out.println("-Enter start date: ");
        System.out.print("Enter day: ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter month: ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter year: ");
        int year = ScannerWrapper.getInstance().nextInt();
        Date startDate = new Date(day, month, year);

        System.out.print("-Enter time length(in minute): ");
        int length = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        PersonalMatch personalMatch = new PersonalMatch(name, startDate, length);
        personalMatch.pickUsers();
        return personalMatch;
    }


    public static Course createCourse() {
        System.out.print("Enter name of course: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter name of institute: ");
        String institute = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter study year: ");
        String studyYear = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter presenter name: ");
        String presenter = ScannerWrapper.getInstance().nextLine();

        return new Course(name, institute, studyYear, presenter);
    }

    public static Answer createAnswer(User user) {
        System.out.print("-Enter solution: ");
        String solution = ScannerWrapper.getInstance().nextLine();
        System.out.print("-Enter day: ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("-Enter month: ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("-Enter year: ");
        int year = ScannerWrapper.getInstance().nextInt();
        Answer ans = new Answer(user, solution, new Date(day, month, year));
        return ans;
    }

    public static Answer createAnswer() {
        Answer output = null;
        System.out.print("-Enter name of group: ");
        String groupName = ScannerWrapper.getInstance().nextLine();
        System.out.print("-Enter solution: ");
        String solution = ScannerWrapper.getInstance().nextLine();
        //output = new Answer(solution, groupName); correct it!!!
        System.out.println("successfully created!!");
        WaitingMaker.getInstance().waiting();
        return output;
    }

    public static Question createQuestion() {
        System.out.print("Enter name of question:");
        String name = ScannerWrapper.getInstance().nextLine();

        System.out.print("Enter score of question: ");
        int score = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        System.out.print("Enter explanation: ");
        String explanation = ScannerWrapper.getInstance().nextLine();

        System.out.print("Enter correct answer of question: ");
        String correctAnswer = ScannerWrapper.getInstance().nextLine();

        return new Question(name, score, correctAnswer, explanation);
    }

    public static Practice createPractice() {
        System.out.print("Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter description: ");
        String description = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter deadline: ");
        System.out.print("--Enter day: ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("--Enter month: ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("--Enter year: ");
        int year = ScannerWrapper.getInstance().nextInt();

        Date deadline = new Date(day, month, year);
        System.out.print("Enter delay coefficient: ");
        double delayCoefficient = ScannerWrapper.getInstance().nextDouble();

        return new Practice(name, description, deadline, delayCoefficient);
    }

    public static Admin createAdmin() {
        System.out.print("Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter username: ");
        String username = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter email address: ");
        String emailAddress = ScannerWrapper.getInstance().nextLine();
        return new Admin(name, password, username, emailAddress);
    }

    public static void createFirstAdmins() {
        Quera.getInstance().getAdmins().add(new Admin("ali", "111", "ALI", "ali@gmail.com"));
        Quera.getInstance().getAdmins().add(new Admin("baran", "222", "BARAN", "baran@gmail.com"));
        Quera.getInstance().getAdmins().add(new Admin("camran", "333", "CAMRAN", "camran@gmail.com"));
        Quera.getInstance().getAdmins().add(new Admin("dana", "444", "DANA", "dana@gmail.com"));
    }

    public static User createUser() {
        System.out.print("Enter name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter email: ");
        String email = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter username: ");
        String username = ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine();

        return new User(name, password, username, email);
    }

    public static void createFirstUsers() {
        Quera.getInstance().getUsers().add(new User("amir", "1111", "AMIR","amir@gmail.com"));
        Quera.getInstance().getUsers().add(new User("bizhan", "2222", "BIZHAN","bizhan@gmail.com"));
        Quera.getInstance().getUsers().add(new User("caren", "3333", "CARAN","caren@gmail.com"));
        Quera.getInstance().getUsers().add(new User("dorsa", "4444", "DORSA","dorsa@gmail.com"));
        Quera.getInstance().getUsers().add(new User("erfan", "5555", "ERFAN","erfan@gmail.com"));
    }

    public static void createFirstCourses() {
        Quera.getInstance().getCourses().add(new Course("MATH2", "KNTU", "1400-1401", "Khasi"));
        Quera.getInstance().getCourses().add(new Course("MATH1", "KNTU", "1400-1401", "Khaste"));
        Quera.getInstance().getCourses().add(new Course("ADABIAT", "KNTU", "1401-1402", "Saqarnezhad"));
        Quera.getInstance().getCourses().add(new Course("signals and systems", "KNTU", "1400-1401", "dalir"));
        Quera.getInstance().getCourses().add(new Course("logic circut", "KNTU", "1399-1400", "Khanmirza"));

    }

    public static void addFirstQuestionsToBank() {
        BankOfQuestion.getInstance().addQuestion(new Question(100, QuestionType.FOUR_OPTION ,
                Difficulty.HARD, "solve q1", true, "Q1!"));
        BankOfQuestion.getInstance().addQuestion(new Question(90, QuestionType.FOUR_OPTION ,
                Difficulty.HARD, "solve q2", true, "Q2!"));
        BankOfQuestion.getInstance().addQuestion(new Question(80, QuestionType.DESCRIPTIVE ,
                Difficulty.MEDIUM, "solve q3", true, "Q3!"));
        BankOfQuestion.getInstance().addQuestion(new Question(70, QuestionType.GAP ,
                Difficulty.EASY, "solve q4", true, "Q4!"));
        BankOfQuestion.getInstance().addQuestion(new Question(60, QuestionType.FOUR_OPTION ,
                Difficulty.MEDIUM, "solve q5", true, "Q5!"));
    }

    public static void createFirstMatches() {
        NormalMatch normalMatch = new NormalMatch("normal match1"
                , new Date(18, 4, 1401), 120);
        normalMatch.addQuestion(new Question(30, QuestionType.GAP,
                Difficulty.EASY, "Do it!!", true, "question1"));
        Quera.getInstance().addMatch(normalMatch);


        PersonalMatch personalMatch = new PersonalMatch("personal match1"
                , new Date(11, 4, 1401), 120);
        personalMatch.addQuestion(new Question(30, QuestionType.GAP,
                Difficulty.EASY, "Do it!!", true, "question2"));
        Quera.getInstance().addMatch(personalMatch);
    }


}
