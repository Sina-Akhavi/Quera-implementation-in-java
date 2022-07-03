package ir.ac.kntu.logic;

import java.util.ArrayList;

public class NormalMatch extends BaseMatch {
    public NormalMatch(String name, Date startDate, int minutesLength) {
        super(name, startDate, minutesLength);
    }
    @Override
    public boolean addParticipant(User participant) {
        if (getParticipants().contains(participant)) {
            System.out.println("You have already registered!!!");
            return false;
        }
        int size = getParticipants().size();

        int limitSize = 50;
        if (size < limitSize) {
            setNumberOfParticipants(getNumberOfParticipants() + 1);
            getParticipants().add(participant);
            System.out.println("Successfully added!!!\n");
            return true;
        }
        return false;
    }

    @Override
    public void updateDegreeAndScoreOfTopUsers(ArrayList<User> topUsers) {
        for (User topUser : topUsers) {
            int previousScore = topUser.getScore();
            topUser.setScore(previousScore + 20);
            int newDegree = topUser.getScore() / 50;
            topUser.setDegree(newDegree);
        }
    }

    public static NormalMatch castToNormalMatch(BaseMatch baseMatch) {
        return new NormalMatch(baseMatch.getName(), baseMatch.getStartDate(), baseMatch.getMinutesLength());
    }

    @Override
    public String toString() {
        return "type: Normal Match,   " + "name: " + getName() + ",   starting date: "
                + getStartDate() + ",   time length: " + getMinutesLength() +
                ",   number of participants: " + getNumberOfParticipants();
    }
}
