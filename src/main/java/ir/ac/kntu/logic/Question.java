package ir.ac.kntu.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Question {
    private String name;

    private int score;

    private String correctAnswer;

    private QuestionType questionType;

    private Difficulty difficulty;

    private String explanation;

    private boolean automaticBeingCorrected;

    private ArrayList<Answer> finalAnswers;

    private Map<User, ArrayList<Answer>> allAnswersOfUser;

    private double delayCoefficient;

    private Date deadLine;

    private Date createdDate;

    public Question(int score, QuestionType questionType, Difficulty difficulty, String explanation,
            boolean automaticBeingCorrected, String name) {
        this.score = score;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.explanation = explanation;
        this.automaticBeingCorrected = automaticBeingCorrected;
        finalAnswers = new ArrayList<>();
        allAnswersOfUser = new HashMap<>();
        this.name = name;
        this.deadLine = new Date();
        this.createdDate = new Date();
    }

    public Question(String name, int score, String correctAnswer, String explanation) {
        this.name = name;
        this.score = score;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public Question(String name) {
        this.name = name;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Date getCreatedDate() {
        return createdDate.clone();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isAutomaticBeingCorrected() {
        return automaticBeingCorrected;
    }

    public void setAutomaticBeingCorrected(boolean automaticBeingCorrected) {
        this.automaticBeingCorrected = automaticBeingCorrected;
    }

    public ArrayList<Answer> getFinalAnswers() {
        return new ArrayList<>(finalAnswers);
    }

    public ArrayList<Answer> getAllAnswersOfUser(User user) {
        if (!allAnswersOfUser.containsKey(user)) {
            System.out.println("No such user found!!");
            return null;
        }
        return new ArrayList<>(allAnswersOfUser.get(user));
    }

    public double getDelayCoefficient() {
        return delayCoefficient;
    }

    public void setDelayCoefficient(double delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public boolean receiveAnswer(Answer answer) {
        if (deadlinePermission(answer)) {
            System.out.println("deadline does not allow you to send answer!!");
            return false;
        }
        if (isAutomaticBeingCorrected()) {
            handleAutomaticallyCorrectingQuestion(answer);
        } else {
            handleNonAutomaticallyCorrectingQuestion(answer);
        }
        return true;
    }

    private void handleNonAutomaticallyCorrectingQuestion(Answer answer) {
        User answerer = answer.getSender();
        updateAllAnswersOfUser(answer);
        Answer priviousAnswer = getFinalAnswerOfUser(answerer);
        if (priviousAnswer == null) {
            answer.setFinal(true);
            finalAnswers.add(answer);
        } else {
            priviousAnswer.setFinal(false);
            answer.setFinal(true);
            finalAnswers.remove(priviousAnswer);
            finalAnswers.add(answer);
        }
    }

    private void handleAutomaticallyCorrectingQuestion(Answer answer) {
        User answerer = answer.getSender();
        giveScore(answer);
        updateAllAnswersOfUser(answer);
        Answer priviousAnswer = getFinalAnswerOfUser(answerer);
        if (priviousAnswer == null) {
            answer.setFinal(true);
            finalAnswers.add(answer);
        } else {
            if (priviousAnswer.getScore() < answer.getScore()) {
                priviousAnswer.setFinal(false);
                answer.setFinal(true);
                finalAnswers.remove(priviousAnswer);
                finalAnswers.add(answer);
            }
        }
    }

    private boolean deadlinePermission(Answer answer) {
        return deadLine.compare(answer.getSendingTime()) != 1;
    }

    private void giveScore(Answer answer) {
        if (answer.getSolution().equals(correctAnswer)) {
            answer.setScore(score);
        } else {
            answer.setScore(0);
        }
    }

    public Answer getFinalAnswerOfUser(User user) {
        for (Answer ans : finalAnswers) {
            if (ans.getSender().equals(user)) {
                return ans;
            }
        }
        return null;
    }

    private void updateAllAnswersOfUser(Answer answer) {
        User sender = answer.getSender();
        if (allAnswersOfUser.containsKey(sender)) {
            allAnswersOfUser.get(sender).add(answer);
            return;
        }
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        allAnswersOfUser.put(sender, answers);
    }

    public void setDeadline(Date deadline) {
        this.deadLine = deadline;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    @Override
    public String toString() {
        return "Question: \n\n" + "explanation: " + explanation + "\nScore: " + score +
                "\nDifficulty: " + difficulty + "\ndeadline: " + deadLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Question question = (Question) o;

        if (score != question.score) {
            return false;
        }
        return name.equals(question.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + score;
        return result;
    }
}
