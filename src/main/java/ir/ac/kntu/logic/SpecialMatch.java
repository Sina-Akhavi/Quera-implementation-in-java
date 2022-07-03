package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.WaitingMaker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpecialMatch extends BaseMatch {

    private Map<Character, ArrayList<User>> groups;

    private int numOfGroup;

    public SpecialMatch(String name, Date startDate, int minutesLength) {
        super(name, startDate, minutesLength);
        this.groups = new HashMap<>();
        this.groups.put('A', new ArrayList<>());
        this.numOfGroup = 0;
    }

    public void setNumOfGroup(int numOfGroup) {
        this.numOfGroup = numOfGroup;
    }

    public int getNumOfGroup() {
        return numOfGroup;
    }

    @Override
    public boolean addParticipant(User participant) {
        System.out.println("*********************");
        System.out.println(this);
        System.out.println("*********************");
        System.out.print("Choose the group you want to join for match" +
                "(or any other letter to create a new group):  ");

        Character input = ScannerWrapper.getInstance().nextLine().charAt(0);
        handleInput(input, participant);

        return true;
    }

    private void handleInput(Character input, User participant) {
        if (groups.containsKey(input)) {
            groups.get(input).add(participant);
            System.out.println("added successfully!!");
            WaitingMaker.getInstance().waiting();
        } else {
            char newName = (char) ('A' + groups.size());
            ArrayList<User> newGroup = new ArrayList<>();
            newGroup.add(participant);
            groups.put(newName, newGroup);
            setNumOfGroup(getNumOfGroup() + 1);
            System.out.println("Group " + newName + " created and participant added to it.");
            WaitingMaker.getInstance().waiting();
        }
    }

    public void sendAnswer(Question question, Answer ans) {
        Character groupName = ans.getGroupSender();
        Answer previousAnswer = findAnswerOfGroup(question, groupName);
        if (previousAnswer == null) {
            getQuestionMap().get(question).add(ans);
        } else {
            getQuestionMap().get(question).remove(previousAnswer);
            getQuestionMap().get(question).add(ans);
        }
        System.out.println("Answer successfully added.");
    }

    public static SpecialMatch castToSpecialMatch(BaseMatch baseMatch) {
        return new SpecialMatch(baseMatch.getName(), baseMatch.getStartDate(), baseMatch.getMinutesLength());
    }

    private Answer findAnswerOfGroup(Question question, Character groupName) {
        ArrayList<Answer> questionAnswers;
        try {
            questionAnswers = getQuestionMap().get(question);
        } catch (NullPointerException ex) {
            System.out.println("The question does not exist!!");
            return null;
        } catch (Exception ex) {
            System.out.println("sth goes wrong!!");
            return null;
        }

        Answer answerOfGroup = null;
        for (Answer ans : questionAnswers) {
            if (ans.getGroupSender().equals(groupName)) {
                answerOfGroup = ans;
                break;
            }
        }
        return answerOfGroup;
    }

    public ArrayList<Character> getTopGroups() {
        Map<Character, Integer> mapScores = calTotalScoresOfEachGroup();

        ArrayList<Character> groupNames = new ArrayList<>(mapScores.keySet());
        ArrayList<Integer> scores = new ArrayList<>(mapScores.values());

        sortBaseOnScoreAscendingly(groupNames, scores);
        System.out.println(scores);
        System.out.println(groupNames);

        int numberOfTopGroups = 2;
        ArrayList<Character> topGroups = new ArrayList<>(numberOfTopGroups);
        for (int i = 0; i < numberOfTopGroups; i++) {
            int index = groupNames.size() - 1 - i;
            updateDegreeAndScoreOfTopUsers(getMembersOfGroup(groupNames.get(index)));
            topGroups.add(groupNames.get(index));
        }
        return topGroups;
    }

    private ArrayList<User> getMembersOfGroup(Character groupName) {
        return groups.get(groupName);
    }

    private void sortBaseOnScoreAscendingly(ArrayList<Character> groupNames, ArrayList<Integer> scores) {
        for (int i = 0; i < scores.size(); i++) {
            for (int j = 0; j < scores.size() - 1 - i; j++) {
                if (scores.get(j) > scores.get(j + 1)) {
                    swapCharacter(groupNames, j);
                    swapInteger(scores, j);
                }
            }
        }
    }

    private void swapCharacter(ArrayList<Character> names, int index) {
        Character temp = names.get(index + 1);
        names.set(index + 1, names.get(index));
        names.set(index, temp);
    }

    private void swapInteger(ArrayList<Integer> integers, int index) {
        Integer temp = integers.get(index + 1);
        integers.set(index + 1, integers.get(index));
        integers.set(index, temp);
    }

    @Override
    public void updateDegreeAndScoreOfTopUsers(ArrayList<User> topUsers) {
        for (User topUser : topUsers) {
            int previousScore = topUser.getScore();
            topUser.setScore(previousScore + 25);
            int newDegree = topUser.getScore() / 50;
            topUser.setDegree(newDegree);
        }
    }


    private Map<Character, Integer> calTotalScoresOfEachGroup() {
        Map<Character, Integer> mapScores = new HashMap<>();
        for (Character groupName : groups.keySet()) {
            int totalScore = 0;
            for (Question q : getQuestionMap().keySet()) {
                Answer groupAnswer = findAnswerOfGroup(q, groupName);
                totalScore += groupAnswer.getScore();
            }
            mapScores.put(groupName, totalScore);
        }
        return mapScores;
    }

    public void printMembers(Character group) {
        ArrayList<User> members = groups.get(group);
        for (User user : members) {
            System.out.println(user.getName() + "   ");
        }
    }

    @Override
    public String toString() {
        return "type: Special Match,   " + "name: " + getName() + ",   starting date: "
                + getStartDate() + ",   time length: " + getMinutesLength() +
                ",   number of groups: " + getNumOfGroup();
    }

    public String print() {
        String output = "";
        for (Character name : groups.keySet()) {
            output = output + "-group " + name + ": ";
            ArrayList<User> groupMembers = groups.get(name);
            for (int i = 0; i < groupMembers.size(); i++) {
                output = output + (groupMembers.get(i).getName() + "   ");
            }
            output += "\n";
        }
        return output;
    }

}
