package ir.ac.kntu.logic;

import ir.ac.kntu.util.Creator;
import ir.ac.kntu.util.ScannerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseMatch {

    private int numberOfParticipants;
    private Map<Question, ArrayList<Answer>> questionMap;
    private String name;

    private Date startDate;

    private int minutesLength;

    private ArrayList<User> participants;


    public BaseMatch(String name, Date startDate, int minutesLength) {
        this.name = name;
        this.startDate = startDate;
        this.minutesLength = minutesLength;
        this.participants = new ArrayList<>();
        this.questionMap = new HashMap<>();
        this.numberOfParticipants = 0;
    }

    public void addQuestion(Question question) {
        this.questionMap.put(question, new ArrayList<Answer>());
    }

    public boolean addParticipant(User participant) {
        System.out.println("Hello!! in base add participant!!");
        numberOfParticipants++;
        return participants.add(participant);
    }

    public ArrayList<User> getTopUsers(int numOfTopUsers) {
        Map<User, Integer> userTotalScoreMap = makeUserScoreMap();

        ArrayList<User> users = new ArrayList<>(userTotalScoreMap.keySet());
        ArrayList<Integer> scores = new ArrayList<>(userTotalScoreMap.values());

        sortUsersBaseOnScores(users, scores);

        ArrayList<User> topFive = getTopsUtil(users, numOfTopUsers);
        updateDegreeAndScoreOfTopUsers(topFive);
        return topFive;
    }

    private ArrayList<User> getTopsUtil(ArrayList<User> users, int numOfTops) {
        ArrayList<User> topFiveUsers = new ArrayList<>();
        int numberOfTopGroups = numOfTops;
        for (int i = 0; i < numberOfTopGroups; i++) {
            int index = users.size() - 1 - i;
            try {
                topFiveUsers.add(users.get(index));
            } catch (Exception e) {
                break;
            }
        }
        return topFiveUsers;
    }

    private Map<User, Integer> makeUserScoreMap() {
        Map<User, Integer> userTotalScoreMap = new HashMap<>();
        for (User user : getParticipants()) {
            int totalScore = 0;
            for (Question q : this.questionMap.keySet()) {
                Answer ans = findAnswer(q, user);
                try {
                    totalScore += ans.getScore();
                } catch (NullPointerException e) {
                    totalScore += 0;
                } catch (Exception e) {
                    totalScore += 0;
                }
            }
            userTotalScoreMap.put(user, totalScore);
        }
        return userTotalScoreMap;
    }

    private Answer findAnswer(Question question, User user) {
        ArrayList<Answer> questionAnswers = questionMap.get(question);
        for (Answer ans : questionAnswers) {
            if (user.equals(ans.getSender())) {
                return ans;
            }
        }
        return null;
    }

    public void sendAnswer(Question question, Answer answer) {
        if (!questionMap.containsKey(question)) {
            System.out.println("Match does not have this question!!");
            return;
        }
        Answer previousAnswer = findAnswer(question, answer.getSender());
        if (previousAnswer == null) {
            questionMap.get(question).add(answer);
        } else {
            questionMap.get(question).remove(previousAnswer);
            questionMap.get(question).add(answer);
        }
    }


    private void sortUsersBaseOnScores(ArrayList<User> users, ArrayList<Integer> scores) {
        for (int i = 0; i < scores.size(); i++) {
            for (int j = 0; j < scores.size() - 1 - i; j++) {
                if (scores.get(j) > scores.get(j + 1)) {
                    swapUser(users, j);
                    swapInteger(scores, j);
                }
            }
        }
    }


    private void swapInteger(ArrayList<Integer> integers, int index) {
        Integer temp = integers.get(index + 1);
        integers.set(index + 1, integers.get(index));
        integers.set(index, temp);
    }

    private void swapUser(ArrayList<User> users, int index) {
        User temp = users.get(index + 1);
        users.set(index + 1, users.get(index));
        users.set(index, temp);
    }

    public abstract void updateDegreeAndScoreOfTopUsers(ArrayList<User> topUsers);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseMatch baseMatch = (BaseMatch) o;

        if (minutesLength != baseMatch.minutesLength) {
            return false;
        }
        if (!Objects.equals(name, baseMatch.name)) {
            return false;
        }
        return Objects.equals(startDate, baseMatch.startDate);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + minutesLength;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getMinutesLength() {
        return minutesLength;
    }

    public void setMinutesLength(int minutesLength) {
        if (minutesLength > 0) {
            this.minutesLength = minutesLength;
        } else {
            System.out.println("Invalid minutes");
        }
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public Map<Question, ArrayList<Answer>> getQuestionMap() {
        return questionMap;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void addQuestions() {
        System.out.println("\n--Create Questions for " + getName() + "--");
        System.out.print("Enter number of questions: ");

        int numberOfQuestions = ScannerWrapper.getInstance().nextInt();
        ScannerWrapper.getInstance().nextLine();

        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println("Q" + (i + 1) + ": ");
            Question question = Creator.createQuestion();
            questionMap.put(question, new ArrayList<Answer>());
            System.out.println("question successfully added!!");
        }
    }
}
