package ir.ac.kntu.logic;

public enum Difficulty {
    EASY(0), MEDIUM(1), HARD(2), VERY_HARD(3);

    private final int code;

    private Difficulty(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
