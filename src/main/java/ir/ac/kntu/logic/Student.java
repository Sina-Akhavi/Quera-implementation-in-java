package ir.ac.kntu.logic;

import ir.ac.kntu.util.Creator;

public class Student extends NormalUser {
    public Student(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public void sendAnswer(Question question) {
        Answer ans = Creator.createAnswer(this);
        question.receiveAnswer(ans);
    }
}
