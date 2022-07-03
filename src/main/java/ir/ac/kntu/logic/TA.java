package ir.ac.kntu.logic;

public class TA extends NormalUser {
    public TA(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public boolean addQuestion(Practice practice, Question question) {
        return practice.addQuestion(question);
    }
}
