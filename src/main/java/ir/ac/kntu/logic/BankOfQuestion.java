package ir.ac.kntu.logic;

import java.util.ArrayList;

public class BankOfQuestion {
    private ArrayList<Question> questions;

    private static BankOfQuestion bankOfQuestion = new BankOfQuestion();

    private BankOfQuestion() {
        questions = new ArrayList<>();
    }

    public static BankOfQuestion getInstance() {
        return bankOfQuestion;
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public boolean addQuestion(Question question) {
        if (questions.contains(question)) {
            System.out.println("Already exist this question!!");
            return false;
        }
        questions.add(question);
        return true;
    }

    public Question[] getQuestionsSortedBaseDifficulty() {
        Question[] questionsArray = listToArray(questions);
        int size = questionsArray.length;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = size - 1; j >= 1; j--) {
                if (isHarderQuestion(questionsArray[j], questionsArray[j - 1])) {
                    swap(questionsArray, j);
                }
            }
        }
        return questionsArray;
    }

    public Question[] getQuestionsSortedBaseDate() {
        Question[] questionsArray = listToArray(questions);
        int size = questionsArray.length;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = size - 1; j >= 1; j--) {
                Date date2 = questionsArray[j].getCreatedDate();
                Date date1 = questionsArray[j - 1].getCreatedDate();
                if (date2.compare(date1) == 1) {
                    swap(questionsArray, j);
                }
            }
        }
        return questionsArray;
    }

    private void swap(Question[] questionsArray, int index) {
        Question temp = questionsArray[index];
        questionsArray[index] = questionsArray[index - 1];
        questionsArray[index - 1] = temp;
    }

    private Question[] listToArray(ArrayList<Question> questions) {
        Question[] questionsArray = new Question[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            questionsArray[i] = questions.get(i);
        }
        return questionsArray;
    }

    private boolean isHarderQuestion(Question q1, Question q2) {
        return q1.getDifficulty().getCode() > q2.getDifficulty().getCode();
    }

    public void printBaseOnDifficulty() {
        Question[] sortedQuestions = getQuestionsSortedBaseDifficulty();
        System.out.println("*QUESTION NAME*" + "--------" + "*SCORE*" + "--------" + "*DIFFICULTY*");
        int counter = 1;
        for (Question q : sortedQuestions) {
            System.out.println(counter++ + ")" + q.getName() +
                    "--------" + q.getScore() + "--------" + q.getDifficulty());
        }
    }

    public void printBaseOnDate() {
        Question[] sortedQuestions = getQuestionsSortedBaseDate();
        System.out.println("*QUESTION NAME*" + "--------" + "*SCORE*" + "--------" + "*CREATED DATE*");
        int counter = 1;
        for (Question q : sortedQuestions) {
            System.out.println(counter++ + ")" + q.getName() + "--------"
                    + q.getScore() + "--------" + q.getCreatedDate());
        }
    }

    public Question getQuestionBaseOnDifficulty(int no) {
        if (no < 1 || no > questions.size()) {
            System.out.println("invalid number");
            return null;
        }
        Question[] sortedQuestions = getQuestionsSortedBaseDifficulty();
        return sortedQuestions[no - 1];
    }

    public Question getQuestionBaseOnDate(int no) {
        if (no < 1 || no > questions.size()) {
            System.out.println("invalid number");
            return null;
        }
        Question[] sortedQuestions = getQuestionsSortedBaseDate();
        return sortedQuestions[no - 1];
    }

}
