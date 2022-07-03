package ir.ac.kntu.logic;

import ir.ac.kntu.util.Menue;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Collections;

public class PersonalMatch extends BaseMatch {
    public PersonalMatch(String name, Date startDate, int minutesLength) {
        super(name, startDate, minutesLength);
    }

    @Override
    public boolean addParticipant(User participant) {
        if (getParticipants().contains(participant)) {
            System.out.println("You have already registered!!!");
            return false;
        }
        int size = getParticipants().size();

        int limitSize = 20;
        if (size < limitSize) {
            setNumberOfParticipants(getNumberOfParticipants() + 1);
            getParticipants().add(participant);
            System.out.println("successfully added!!!\n");
            return true;
        }
        return false;
    }

    @Override
    public void updateDegreeAndScoreOfTopUsers(ArrayList<User> topUsers) {
        for (User topUser : topUsers) {
            int previousScore = topUser.getScore();
            topUser.setScore(previousScore + 10);
            int newDegree = topUser.getScore() / 50;
            topUser.setDegree(newDegree);
        }
    }

    public static PersonalMatch castToPersonalMatch(BaseMatch baseMatch) {
        return new PersonalMatch(baseMatch.getName(), baseMatch.getStartDate(), baseMatch.getMinutesLength());
    }

    public String toString() {
        return "type: Personal Match,   " + "name: " + getName() + ",   starting date: "
                + getStartDate() + ",   time length: " + getMinutesLength() +
                ",   number of participants: " + getNumberOfParticipants();

    }

    public void pickUsers() {
        System.out.println("\n\n--Pick Users for " + getName() + ": \n");
        Menue.printUsersInQuera();
        System.out.print("(choices with one space) :");

        String[] choices = ScannerWrapper.getInstance().nextLine().split(" ");


        ArrayList<User> users = Quera.getInstance().getUsers();
        for (String choice : choices) {
            int index = Integer.parseInt(choice) - 1;
            User user = Quera.getInstance().getUsers().get(index);
            addParticipant(user);
        }
    }
}
