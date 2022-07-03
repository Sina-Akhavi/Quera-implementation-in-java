package ir.ac.kntu.util;

public class WaitingMaker {

    private static WaitingMaker instance = new WaitingMaker();

    private WaitingMaker() {
    }

    public static WaitingMaker getInstance() {
        return instance;
    }

    public void waiting() {
        System.out.println("Enter any key to continue...");
        ScannerWrapper.getInstance().nextLine();
    }
}
