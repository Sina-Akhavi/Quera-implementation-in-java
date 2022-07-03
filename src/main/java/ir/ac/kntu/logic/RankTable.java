package ir.ac.kntu.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankTable {
    private ArrayList<Question> questions;

    private ArrayList<User> users;

    private boolean isVisible;

    public RankTable() {
        questions = new ArrayList<>();
        users = new ArrayList<>();
        isVisible = true;
    }

    public RankTable(ArrayList<Question> questions) {
        this.questions = questions;
        users = new ArrayList<>();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Map<User, Integer> calculateTotalScores() {
        Map<User, Integer> totalScoresOfUsers = new HashMap<>();
        for (User user : users) {
            int totalScore = 0;
            for (Question question : questions) {
                Answer ans = question.getFinalAnswerOfUser(user);
                int userScore = (ans == null) ? (0) : ans.getScore();
                totalScore += userScore;
            }
            totalScoresOfUsers.put(user, totalScore);
        }
        return totalScoresOfUsers;
    }

    public void print() {
        Map<User, Integer> totalScoresOfUsers = calculateTotalScores();
        User[] sortedUsers = sortUsersBaseOnTotalScore(totalScoresOfUsers);

        System.out.println("                    *********** RANK TABLE ***********\n");
        System.out.print("        ");
        for (Question question : questions) {
            System.out.print(question.getName() + "-----");
        }
        System.out.println("total");

        int counter = 1;
        for (User user : sortedUsers) {
            System.out.print(counter++ + ") " + user.getName() + "    ");
            for (Question question : questions) {
                int userScore = findFinalScoreOfUser(user, question);
                System.out.print(userScore + "/" + question.getScore() + "-----");
            }
            System.out.println(totalScoresOfUsers.get(user));
        }
    }

    public User[] sortUsersBaseOnTotalScore(Map<User, Integer> totalScoresOfUsers) {
        int size = totalScoresOfUsers.size();
        User[] usersArray = new ArrayList<>(totalScoresOfUsers.keySet()).toArray(new User[size]);
        Integer[] totalScoresArray = new ArrayList<>(totalScoresOfUsers.values()).toArray(new Integer[size]);

        for (int i = totalScoresArray.length - 1; i >= 0; i--) {
            for (int j = totalScoresArray.length - 1; j > 0; j--) {
                if (totalScoresArray[j - 1] < totalScoresArray[j]) {
                    swap(totalScoresArray, j);
                    swap(usersArray, j);
                }
            }
        }
        return usersArray;
    }

    private void swap(Object[] objs, int index) {
        Object temp = objs[index];
        objs[index] = objs[index - 1];
        objs[index - 1] = temp;
    }

    private int findFinalScoreOfUser(User user, Question question) {
        Answer ans = question.getFinalAnswerOfUser(user);
        int userScore = (ans == null) ? (0) : ans.getScore();
        return userScore;
    }
}
