package ir.ac.kntu.logic;

public class Guest extends User{
    public Guest(String name, String password, String username, String email) {
        super(name, password, username, email);
    }

    public void seeBank() {
        BankOfQuestion bank = BankOfQuestion.getInstance();
        if (bank.getQuestions().size() == 0) {
            System.out.println("There aren't any questions in bank yet!!");
            return;
        }
        for (Question q : bank.getQuestions()) {
            System.out.println(q);
            System.out.println("\n-----------------\n");
        }
    }

    public void seeTitleOfMatches() {

    }

    public void seeMatch() {

    }
}
