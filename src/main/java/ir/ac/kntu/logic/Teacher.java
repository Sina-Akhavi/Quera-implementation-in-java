package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class Teacher extends TA {
    public Teacher(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public Teacher(User user) {
        super(user.getName(), user.getPassword(), user.getUsername(), user.getEmail());
    }

    public void correctAnswer(Answer answer) {
        System.out.print("Solution:  ");
        System.out.println(answer.getSolution());

        System.out.println("------------");
        System.out.print("give score: ");
        int score = ScannerWrapper.getInstance().nextInt();
        answer.setScore(score);
    }
}
