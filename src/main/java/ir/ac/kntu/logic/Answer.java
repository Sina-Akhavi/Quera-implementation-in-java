package ir.ac.kntu.logic;

import java.text.StringCharacterIterator;

public class Answer {

    private String solution;

    private Date sendingTime;

    private int score;

    private boolean isFinal;

    private User sender;

    private Character groupSender;

    public Answer(User sender, String solution, Date sendingTime) {
        this.solution = solution;
        this.sendingTime = sendingTime.clone();
        this.sender = sender;
    }

    public Answer(String solution, Character groupSender) {
        this.solution = solution;
        this.groupSender = groupSender;
    }

    public Answer(User user) {
        this.sender = user;
        this.solution = "Not answered yet!!";
        this.sendingTime = new Date();
    }

    public Answer(Character groupSender, int score) {
        this.groupSender = groupSender;
        this.score = score;
    }

    public Answer(User sender, int score) {
        this.score = score;
        this.sender = sender;
    }

    public Character getGroupSender() {
        return groupSender;
    }

    public void setGroupSender(Character groupSender) {
        this.groupSender = groupSender;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Answer: \n" + "-Solution: " + solution + "\n-Date of sending: " + sendingTime + "\n-Score: " + score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + score;
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + ((solution == null) ? 0 : solution.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Answer other = (Answer) obj;
        if (score != other.score) {
            return false;
        }
        if (sender == null) {
            if (other.sender != null) {
                return false;
            }
        } else if (!sender.equals(other.sender)) {
            return false;
        }
        if (solution == null) {
            if (other.solution != null) {
                return false;
            }
        } else if (!solution.equals(other.solution)) {
            return false;
        }
        return true;
    }
}
