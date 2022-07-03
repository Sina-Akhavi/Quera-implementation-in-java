package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.Objects;

public class User {
    private String name;

    private String password;

    private String username;

    private String email;

    private int score;

    private int degree;

    private String message;

    public User(String name, String password, String username, String email) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.message = null;
    }

    public User(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static BaseMatch searchMatchBaseOnName() {
        System.out.println("--Search Match By Name\n");
        System.out.print("-Enter name of match: ");
        String name = ScannerWrapper.getInstance().nextLine();

        for (BaseMatch match : Quera.getInstance().getMatches()) {
            if (match.getName().equals(name)) {
                return match;
            }
        }
        return null;
    }

    public static User searchUserBaseOnUserName() {
        System.out.println("\n--Search User By UserName\n");
        System.out.print("-Enter username of user: ");
        String userName = ScannerWrapper.getInstance().nextLine();

        for (User user : Quera.getInstance().getUsers()) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!password.equals(user.password)) {
            return false;
        }
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = password.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", degree=" + degree +
                '}';
    }
}
